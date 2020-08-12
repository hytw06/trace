package com.axe.trace.modules.process.service;

import com.alibaba.fastjson.JSON;
import com.axe.trace.modules.fabric.FabricClient;
import com.axe.trace.modules.fabric.FabricConfig;
import com.axe.trace.modules.process.entity.Storage;
import com.axe.trace.modules.process.mapper.StorageMapper;
import com.axe.trace.sys.service.BaseService;
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
import java.util.List;
import java.util.Map;

/**
 * 入库Service
 * @author liyang
 * @version 2020-08-06
 */
@Service
@Transactional(readOnly = true)
public class StorageService extends BaseService<StorageMapper, Storage> {

    @Override
    public void save(Storage vo) {
        if (StringUtils.isBlank(vo.getId())) {
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
    public void delete(Storage vo) {
        mapper.delete(vo);
        try {
            deleteChain(vo);
        } catch (IOException | NoSuchAlgorithmException | InstantiationException | NoSuchMethodException | InvalidArgumentException | IllegalAccessException | InvocationTargetException | CryptoException | ClassNotFoundException | InvalidKeySpecException | ProposalException | TransactionException e) {
            e.printStackTrace();
        }
    }

    // 保存到区块链
    public void saveChain(Storage vo) throws IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException, TransactionException {
        FabricClient fabricClient = getFabricClient();
        Peer peer0 = fabricClient.getPeer(FabricConfig.ORG1_PEER0, FabricConfig.ORG1_PEER0_URL, FabricConfig.PEER_FILE_PATH1);
        List<Peer> peers = new ArrayList<>();
        peers.add(peer0);
        Orderer order = fabricClient.getOrderer(FabricConfig.ORDERER_NAME, FabricConfig.ORDERER_URL, FabricConfig.ORDER_FILE_PATH);

        String[] initArgs = {vo.getId(), JSON.toJSONString(vo)};
        fabricClient.invoke(FabricConfig.CHANNEL_NAME, TransactionRequest.Type.JAVA, "storage", order, peers, "save", initArgs);
    }

    // 通过key值查询区块链
    public Storage queryChain(Storage vo) throws IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException, TransactionException {
        FabricClient fabricClient = getFabricClient();
        Peer peer0 = fabricClient.getPeer(FabricConfig.ORG1_PEER0, FabricConfig.ORG1_PEER0_URL, FabricConfig.PEER_FILE_PATH1);
        List<Peer> peers = new ArrayList<>();
        peers.add(peer0);

        String[] initArgs = {vo.getId()};
        Map map = fabricClient.queryChaincode(peers, FabricConfig.CHANNEL_NAME, TransactionRequest.Type.JAVA, "storage", "query", initArgs);
        String result = (String) map.get(200);
        Storage storage = JSON.parseObject(result, Storage.class);
        return storage;
    }

    // 删除区块链数据
    public void deleteChain(Storage vo) throws IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException, TransactionException {
        FabricClient fabricClient = getFabricClient();
        Peer peer0 = fabricClient.getPeer(FabricConfig.ORG1_PEER0, FabricConfig.ORG1_PEER0_URL, FabricConfig.PEER_FILE_PATH1);
        List<Peer> peers = new ArrayList<>();
        peers.add(peer0);
        Orderer order = fabricClient.getOrderer(FabricConfig.ORDERER_NAME, FabricConfig.ORDERER_URL, FabricConfig.ORDER_FILE_PATH);

        String[] initArgs = {vo.getId()};

        fabricClient.invoke(FabricConfig.CHANNEL_NAME, TransactionRequest.Type.JAVA, "storage", order, peers, "delete", initArgs);
    }

}
