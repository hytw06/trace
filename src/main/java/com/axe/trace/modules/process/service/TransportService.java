package com.axe.trace.modules.process.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.axe.trace.modules.fabric.FabricClient;
import com.axe.trace.modules.fabric.FabricConfig;
import com.axe.trace.modules.process.entity.Transport;
import com.axe.trace.modules.process.mapper.TransportMapper;
import com.axe.trace.sys.service.BaseService;
import com.axe.trace.sys.util.DateUtil;
import com.axe.trace.sys.util.StringUtils;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.TransactionRequest;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 运输Service
 * @author liyang
 * @version 2020-08-06
 */
@Service
@Transactional(readOnly = true)
public class TransportService extends BaseService<TransportMapper, Transport> {

    @Override
    public void save(Transport vo) {
        if (StringUtils.isBlank(vo.getId())) {
            if (StringUtils.isBlank(vo.getTransportBatch())) {
                vo.setTransportBatch(generateProductBatch());
            }
            mapper.insert(vo);
        } else {
            mapper.update(vo);
        }
        vo = get(vo.getId());
        try {
            saveChain(vo);
        } catch (IOException | NoSuchAlgorithmException | InstantiationException | NoSuchMethodException | InvalidArgumentException | IllegalAccessException | InvocationTargetException | CryptoException | ClassNotFoundException | InvalidKeySpecException | ProposalException | TransactionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Transport vo) {
        mapper.delete(vo);
        try {
            deleteChain(vo);
        } catch (IOException | NoSuchAlgorithmException | InstantiationException | NoSuchMethodException | InvalidArgumentException | IllegalAccessException | InvocationTargetException | CryptoException | ClassNotFoundException | InvalidKeySpecException | ProposalException | TransactionException e) {
            e.printStackTrace();
        }
    }

    // 保存到区块链
    public void saveChain(Transport vo) throws IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException, TransactionException {
        FabricClient fabricClient = getFabricClient();
        Peer peer0 = fabricClient.getPeer(FabricConfig.ORG1_PEER0, FabricConfig.ORG1_PEER0_URL, FabricConfig.PEER_FILE_PATH1);
        List<Peer> peers = new ArrayList<>();
        peers.add(peer0);
        Orderer order = fabricClient.getOrderer(FabricConfig.ORDERER_NAME, FabricConfig.ORDERER_URL, FabricConfig.ORDER_FILE_PATH);

        String[] initArgs = {vo.getId(), JSON.toJSONString(vo)};
        fabricClient.invoke(FabricConfig.CHANNEL_NAME, TransactionRequest.Type.JAVA, "transport", order, peers, "save", initArgs);
    }

    // 通过key值查询区块链
    public Transport queryChain(Transport vo) throws IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException, TransactionException {
        FabricClient fabricClient = getFabricClient();
        Peer peer0 = fabricClient.getPeer(FabricConfig.ORG1_PEER0, FabricConfig.ORG1_PEER0_URL, FabricConfig.PEER_FILE_PATH1);
        List<Peer> peers = new ArrayList<>();
        peers.add(peer0);

        String[] initArgs = {vo.getId()};
        Map map = fabricClient.queryChaincode(peers, FabricConfig.CHANNEL_NAME, TransactionRequest.Type.JAVA, "transport", "query", initArgs);
        String result = (String) map.get(200);
        Transport transport = JSON.parseObject(result, Transport.class);
        return transport;
    }

    // 通过产品批次查询区块链
    public List<Transport> queryChainByProductBatch(String productBatch) throws TransactionException, ProposalException, InvalidArgumentException, IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException {
        FabricClient fabricClient = getFabricClient();
        Peer peer0 = fabricClient.getPeer(FabricConfig.ORG1_PEER0, FabricConfig.ORG1_PEER0_URL, FabricConfig.PEER_FILE_PATH1);
        List<Peer> peers = new ArrayList<>();
        peers.add(peer0);
        String[] initArgs = {productBatch};

        Map map = fabricClient.queryChaincode(peers, FabricConfig.CHANNEL_NAME, TransactionRequest.Type.JAVA, "transport", "queryByProductBatch", initArgs);
        String result = (String) map.get(200);
        // 如果没有查询到则返回null
        if (StringUtils.isBlank(result)) {
            return null;
        }
        // 解析json数组
        JSONArray jsonArray = JSON.parseArray(result);
        List<Transport> transportList = new ArrayList<>();
        for (int i=0; i<jsonArray.size(); i++) {
            String s = jsonArray.getString(i);
            transportList.add(JSON.parseObject(s, Transport.class));
        }
        return transportList;
    }

    // 删除区块链数据
    public void deleteChain(Transport vo) throws IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException, TransactionException {
        FabricClient fabricClient = getFabricClient();
        Peer peer0 = fabricClient.getPeer(FabricConfig.ORG1_PEER0, FabricConfig.ORG1_PEER0_URL, FabricConfig.PEER_FILE_PATH1);
        List<Peer> peers = new ArrayList<>();
        peers.add(peer0);
        Orderer order = fabricClient.getOrderer(FabricConfig.ORDERER_NAME, FabricConfig.ORDERER_URL, FabricConfig.ORDER_FILE_PATH);

        String[] initArgs = {vo.getId()};

        fabricClient.invoke(FabricConfig.CHANNEL_NAME, TransactionRequest.Type.JAVA, "transport", order, peers, "delete", initArgs);
    }

    /**
     * 生成运输批次号
     * @return string
     */
    public String generateProductBatch() {
        String s = DateUtil.date2Str(new Date(), DateUtil.FORMAT_FULL_SN);
        return "YS" + s;
    }

}
