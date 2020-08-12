package com.axe.trace.modules.process.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 质量检测Entity
 * @author liyang
 * @version 2020-08-06
 */
public class QualityCheck extends BaseEntity {

    @ApiModelProperty(value="产品id", name="productId")
    private String productId;

    @ApiModelProperty(value="产品批次", name="productBatch")
    private String productBatch;

    @ApiModelProperty(value="产品名称", name="productName")
    private String productName;

    @ApiModelProperty(value="质检指标", name="target")
    private String target;

    @ApiModelProperty(value="质检结果", name="result")
    private String result;

    @ApiModelProperty(value="质检时间", name="time")
    private Date time;

    @ApiModelProperty(value="质检机构", name="organization")
    private String organization;

    @ApiModelProperty(value="起始质检时间（用于查询）", name="beginTime")
    private Date beginTime;

    @ApiModelProperty(value="结束质检时间（用于查询）", name="endTime")
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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
