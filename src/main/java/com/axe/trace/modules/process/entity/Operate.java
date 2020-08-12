package com.axe.trace.modules.process.entity;

import com.axe.trace.sys.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 农事操作Entity
 * @author liyang
 * @version 2020-08-06
 */
public class Operate extends BaseEntity {

    @ApiModelProperty(value="原产地id", name="sourceAreaId")
    private String sourceAreaId;

    @ApiModelProperty(value="原产地名称", name="sourceAreaName")
    private String sourceAreaName;

    @ApiModelProperty(value="产品id", name="productId")
    private String productId;

    @ApiModelProperty(value="产品批次", name="productBatch")
    private String productBatch;

    @ApiModelProperty(value="产品名称", name="productName")
    private String productName;

    @ApiModelProperty(value="农事操作名称", name="name")
    private String name;

    @ApiModelProperty(value="农事操作备注", name="remark")
    private String remark;

    @ApiModelProperty(value="操作人员", name="operator")
    private String operator;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @ApiModelProperty(value="操作时间", name="time")
    private Date time;

    @ApiModelProperty(value="起始操作时间（用于查询）", name="beginTime")
    private Date beginTime;

    @ApiModelProperty(value="结束操作时间（用于查询）", name="endTime")
    private Date endTime;

    public String getSourceAreaId() {
        return sourceAreaId;
    }

    public void setSourceAreaId(String sourceAreaId) {
        this.sourceAreaId = sourceAreaId;
    }

    public String getSourceAreaName() {
        return sourceAreaName;
    }

    public void setSourceAreaName(String sourceAreaName) {
        this.sourceAreaName = sourceAreaName;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
