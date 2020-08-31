package com.axe.trace.modules.basic.controller;

import com.axe.trace.modules.basic.entity.BlockchainInformation;
import com.axe.trace.modules.basic.service.ProductService;
import com.axe.trace.modules.fabric.FabricClient;
import com.axe.trace.modules.fabric.FabricConfig;
import com.axe.trace.sys.controller.BaseController;
import com.axe.trace.sys.util.AjaxJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.BlockchainInfo;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/blockChain")
@Api(value = "区块链信息Controller", tags = {"区块链信息接口"})
public class BlockChainController extends BaseController {

    @Autowired
    private ProductService service;

    @PostMapping("/blockchainInfo")
    @ResponseBody
    @ApiOperation(value = "获取区块链信息")
    public AjaxJson blockchainInfo() {
        AjaxJson ajaxJson = new AjaxJson();
        BlockchainInformation blockchainInformation = new BlockchainInformation();

        try {
            FabricClient fabricClient = service.getFabricClient();
            Channel channel = fabricClient.getChannel("mychannel");
            channel.addOrderer(fabricClient.getOrderer(FabricConfig.ORDERER_NAME, FabricConfig.ORDERER_URL, FabricConfig.ORDER_FILE_PATH));
            channel.addPeer(fabricClient.getPeer(FabricConfig.ORG1_PEER0, FabricConfig.ORG1_PEER0_URL, FabricConfig.PEER_FILE_PATH1));
            channel.initialize();

            // 获取区块链信息
            BlockchainInfo blockchainInfo = channel.queryBlockchainInfo();
            long height = blockchainInfo.getHeight();
            blockchainInformation.setHeight(height);
            blockchainInformation.setCurrentBlockHash(blockchainInfo.getCurrentBlockHash());
            blockchainInformation.setPreviousBlockHash(blockchainInfo.getPreviousBlockHash());
            long totalTransactionCount = 0;

            // 遍历每个区块的交易数，计算总交易数
            for (int i = 0; i < height; i++) {
                // 通过区块号获取区块信息
                BlockInfo blockInfo = channel.queryBlockByNumber(i);
                // 累加每个区块的交易数
                totalTransactionCount += blockInfo.getTransactionCount();
            }
            blockchainInformation.setTotalTransactionCount(totalTransactionCount);

        } catch (IOException | NoSuchAlgorithmException | InstantiationException | NoSuchMethodException | InvalidArgumentException | IllegalAccessException | InvocationTargetException | CryptoException | ClassNotFoundException | InvalidKeySpecException | ProposalException | TransactionException e) {
            e.printStackTrace();
        }
        ajaxJson.getBody().put("result", blockchainInformation);
        return ajaxJson;
    }

}
