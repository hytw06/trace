package temp.service;

import com.alibaba.fastjson.JSON;
import com.axe.trace.modules.fabric.FabricClient;
import com.axe.trace.modules.fabric.FabricConfig;
import com.axe.trace.modules.fabric.FabricUserContext;
import com.axe.trace.modules.fabric.FabricUserUtils;
import temp.entity.TraceInfo;
import temp.mapper.TraceInfoMapper;
import com.axe.trace.sys.service.BaseService;
import org.hyperledger.fabric.sdk.Enrollment;
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

@Service
@Transactional(readOnly = true)
public class TraceInfoService extends BaseService<TraceInfoMapper, TraceInfo> {

    @Transactional(readOnly = false)
    public void saveBlockChain(TraceInfo vo) throws IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException, TransactionException {
        FabricClient fabricClient = getFabricClient();
        Peer peer0 = fabricClient.getPeer(FabricConfig.ORG1_PEER0, FabricConfig.ORG1_PEER0_URL, FabricConfig.PEER_FILE_PATH1);
        List<Peer> peers = new ArrayList<>();
        peers.add(peer0);
        Orderer order = fabricClient.getOrderer(FabricConfig.ORDERER_NAME, FabricConfig.ORDERER_URL, FabricConfig.ORDER_FILE_PATH);

        String[] initArgs = {vo.getTraceSourceCode(), JSON.toJSONString(vo)};

        fabricClient.invoke(FabricConfig.CHANNEL_NAME, TransactionRequest.Type.JAVA, "Test", order, peers, "save", initArgs);
    }

    public TraceInfo findBlockChain(TraceInfo vo) throws IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException, TransactionException {
        FabricClient fabricClient = getFabricClient();
        Peer peer0 = fabricClient.getPeer(FabricConfig.ORG1_PEER0, FabricConfig.ORG1_PEER0_URL, FabricConfig.PEER_FILE_PATH1);
        List<Peer> peers = new ArrayList<>();
        peers.add(peer0);

        String[] initArgs = {vo.getTraceSourceCode()};
        Map map = fabricClient.queryChaincode(peers, FabricConfig.CHANNEL_NAME, TransactionRequest.Type.JAVA, "Test", "query", initArgs);
        String result = (String) map.get(200);
        TraceInfo traceInfo = JSON.parseObject(result, TraceInfo.class);
        return traceInfo;
    }

    @Transactional(readOnly = false)
    public void deleteBlockChain(TraceInfo vo) throws IOException, NoSuchAlgorithmException, InstantiationException, NoSuchMethodException, IllegalAccessException, InvalidArgumentException, InvocationTargetException, CryptoException, InvalidKeySpecException, ClassNotFoundException, ProposalException, TransactionException {
        FabricClient fabricClient = getFabricClient();
        Peer peer0 = fabricClient.getPeer(FabricConfig.ORG1_PEER0, FabricConfig.ORG1_PEER0_URL, FabricConfig.PEER_FILE_PATH1);
        List<Peer> peers = new ArrayList<>();
        peers.add(peer0);
        Orderer order = fabricClient.getOrderer(FabricConfig.ORDERER_NAME, FabricConfig.ORDERER_URL, FabricConfig.ORDER_FILE_PATH);

        String[] initArgs = {vo.getTraceSourceCode()};

        fabricClient.invoke(FabricConfig.CHANNEL_NAME, TransactionRequest.Type.JAVA, "Test", order, peers, "delete", initArgs);
    }

}
