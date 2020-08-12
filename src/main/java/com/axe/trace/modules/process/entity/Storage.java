package com.axe.trace.modules.process.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 入库Entity
 * @author liyang
 * @version 2020-08-06
 */
public class Storage extends BaseEntity {

    @ApiModelProperty(value="产品id", name="productId")
    private String productId;

    @ApiModelProperty(value="产品批次", name="productBatch")
    private String productBatch;

    @ApiModelProperty(value="产品名称", name="productName")
    private String productName;

    @ApiModelProperty(value="仓库地址", name="address")
    private String address;

    @ApiModelProperty(value="数量", name="count")
    private String count;

    @ApiModelProperty(value="入库时间", name="time")
    private Date time;

    @ApiModelProperty(value="操作员", name="operator")
    private String operator;

    @ApiModelProperty(value="起始入库时间（用于查询）", name="beginTime")
    private Date beginTime;

    @ApiModelProperty(value="结束入库时间（用于查询）", name="endTime")
    private Date endTime;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductBatch() {
        return productBatch;
    }

    public void setProductBatch(String productBatch) {
        this.productBatch = productBatch;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
