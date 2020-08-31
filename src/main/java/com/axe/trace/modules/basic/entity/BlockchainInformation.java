package com.axe.trace.modules.basic.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 区块链信息Entity
 * @author liyang
 * @version 2020-08-30
 */
public class BlockchainInformation {

    @ApiModelProperty(value="区块高度", name="height")
    private long height;

    @ApiModelProperty(value="当前区块哈希", name="currentBlockHash")
    private byte[] currentBlockHash;

    @ApiModelProperty(value="上个区块哈希", name="previousBlockHash")
    private byte[] previousBlockHash;

    @ApiModelProperty(value="所有区块总交易数", name="totalTransactionCount")
    private long totalTransactionCount;

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public byte[] getCurrentBlockHash() {
        return currentBlockHash;
    }

    public void setCurrentBlockHash(byte[] currentBlockHash) {
        this.currentBlockHash = currentBlockHash;
    }

    public byte[] getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(byte[] previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public long getTotalTransactionCount() {
        return totalTransactionCount;
    }

    public void setTotalTransactionCount(long totalTransactionCount) {
        this.totalTransactionCount = totalTransactionCount;
    }

}
