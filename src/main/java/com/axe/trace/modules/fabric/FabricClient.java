package com.axe.trace.modules.fabric;

import org.hyperledger.fabric.sdk.*;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class FabricClient {
    private static final Logger log = LoggerFactory.getLogger(FabricClient.class);

    private HFClient hfClient;

    public FabricClient(FabricUserContext fabricUserContext) throws IllegalAccessException, InvocationTargetException, InvalidArgumentException, InstantiationException, NoSuchMethodException, CryptoException, ClassNotFoundException {
        hfClient = HFClient.createNewInstance();
        CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        hfClient.setCryptoSuite(cryptoSuite);
        hfClient.setUserContext(fabricUserContext);
    }

    /**
     * @description 合约的调用
     * @param channelName
     * @param lang
     * @param chaincodeName
     * @param order
     * @param peers
     * @param funcName 合约调用执行的函数名称
     * @param args 合约调用执行的参数
     * @throws TransactionException
     * @throws ProposalException
     * @throws InvalidArgumentException
     */
    public void invoke(String channelName, TransactionRequest.Type lang, String chaincodeName, Orderer order, List<Peer> peers, String funcName, String args[]) throws TransactionException, ProposalException, InvalidArgumentException {
        Channel channel = getChannel(channelName);
        channel.addOrderer(order);
        for(Peer p : peers) {
            channel.addPeer(p);
        }
        channel.initialize();
        TransactionProposalRequest transactionProposalRequest = hfClient.newTransactionProposalRequest();
        transactionProposalRequest.setChaincodeLanguage(lang);
        transactionProposalRequest.setArgs(args);
        transactionProposalRequest.setFcn(funcName);
        ChaincodeID.Builder builder = ChaincodeID.newBuilder().setName(chaincodeName);
        transactionProposalRequest.setChaincodeID(builder.build());
        Collection<ProposalResponse> responses = channel.sendTransactionProposal(transactionProposalRequest, peers);
        for(ProposalResponse response:responses){
            if(response.getStatus().getStatus()==200){
                log.info("{} invoke proposal {} success", response.getPeer().getName(), funcName);
            }else{
                String logArgs[] = {response.getMessage(), funcName, response.getPeer().getName()};
                log.error("{} invoke proposal {} fail on {}", logArgs);
            }
        }
        channel.sendTransaction(responses);
    }

    /**
     * @description 合约的查询
     * @param peers
     * @param channelName
     * @param lang
     * @param chaincodeName
     * @param funcName
     * @param args
     * @return
     * @throws TransactionException
     * @throws InvalidArgumentException
     * @throws ProposalException
     */
    public Map queryChaincode(List<Peer> peers, String channelName, TransactionRequest.Type lang, String chaincodeName, String funcName, String args[]) throws TransactionException, InvalidArgumentException, ProposalException {
        Channel channel = getChannel(channelName);
        for(Peer p : peers) {
            channel.addPeer(p);
        }
        channel.initialize();
        HashMap map = new HashMap();
        QueryByChaincodeRequest queryByChaincodeRequest = hfClient.newQueryProposalRequest();
        ChaincodeID.Builder builder = ChaincodeID.newBuilder().setName(chaincodeName);
        queryByChaincodeRequest.setChaincodeID(builder.build());
        queryByChaincodeRequest.setArgs(args);
        queryByChaincodeRequest.setFcn(funcName);
        queryByChaincodeRequest.setChaincodeLanguage(lang);
        Collection<ProposalResponse> responses = channel.queryByChaincode(queryByChaincodeRequest);
        for (ProposalResponse response : responses) {
            if (response.getStatus().getStatus() == 200) {
                log.info("data is {}", response.getProposalResponse().getResponse().getPayload());
                map.put(response.getStatus().getStatus(), new String(response.getProposalResponse().getResponse().getPayload().toByteArray()));
                return map;
            } else {
                log.error("data get error {}", response.getMessage());
                map.put(response.getStatus().getStatus(), response.getMessage());
                return map;
            }
        }
        map.put("code","404");
        return map;
    }

    /**
     * @description 获取orderer节点
     * @param name
     * @param grpcUrl
     * @param tlsFilePath
     * @return
     * @throws InvalidArgumentException
     */
    public Orderer getOrderer(String name, String grpcUrl, String tlsFilePath) throws InvalidArgumentException {
        Properties properties = new Properties();
        properties.setProperty("pemFile", tlsFilePath);
        Orderer orderer = hfClient.newOrderer(name, grpcUrl, properties);
        return orderer;
    }

    /**
     * @description 获取peer节点
     * @param name
     * @param grpcUrl
     * @param tlsFilePath
     * @return
     * @throws InvalidArgumentException
     */
    public Peer getPeer(String name, String grpcUrl, String tlsFilePath) throws InvalidArgumentException {
        Properties properties = new Properties();
        properties.setProperty("pemFile",tlsFilePath);
        Peer peer = hfClient.newPeer(name, grpcUrl, properties);
        return peer;
    }

    /**
     * @description 获取已有的channel
     * @param channelName
     * @return
     * @throws InvalidArgumentException
     */
    public Channel getChannel(String channelName) throws InvalidArgumentException {
        Channel channel =  hfClient.newChannel(channelName);
        return channel;
    }

}
