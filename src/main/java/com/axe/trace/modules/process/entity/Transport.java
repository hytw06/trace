package com.axe.trace.modules.process.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 运输Entity
 * @author liyang
 * @version 2020-08-06
 */
public class Transport extends BaseEntity {

    @ApiModelProperty(value="产品id", name="productId")
    private String productId;

    @ApiModelProperty(value="产品批次", name="productBatch")
    private String productBatch;

    @ApiModelProperty(value="产品名称", name="productName")
    private String productName;

    @ApiModelProperty(value="运输批次", name="transportBatch")
    private String transportBatch;

    @ApiModelProperty(value="运输车牌号", name="plateNumber")
    private String plateNumber;

    @ApiModelProperty(value="驾驶员", name="driver")
    private String driver;

    @ApiModelProperty(value="联系方式", name="phone")
    private String phone;

    @ApiModelProperty(value="出发地", name="depart")
    private String depart;

    @ApiModelProperty(value="出发时间", name="departTime")
    private Date departTime;

    @ApiModelProperty(value="到达地", name="arrive")
    private String arrive;

    @ApiModelProperty(value="到达时间", name="arriveTime")
    private Date arriveTime;

    @ApiModelProperty(value="起始出发时间（用于查询）", name="beginDepartTime")
    private Date beginDepartTime;

    @ApiModelProperty(value="结束出发时间（用于查询）", name="endDepartTime")
    private Date endDepartTime;

    @ApiModelProperty(value="起始到达时间（用于查询）", name="beginArriveTime")
    private Date beginArriveTime;

    @ApiModelProperty(value="结束到达时间（用于查询）", name="endArriveTime")
    private Date endArriveTime;

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

    public String getTransportBatch() {
        return transportBatch;
    }

    public void setTransportBatch(String transportBatch) {
        this.transportBatch = transportBatch;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Date getBeginDepartTime() {
        return beginDepartTime;
    }

    public void setBeginDepartTime(Date beginDepartTime) {
        this.beginDepartTime = beginDepartTime;
    }

    public Date getEndDepartTime() {
        return endDepartTime;
    }

    public void setEndDepartTime(Date endDepartTime) {
        this.endDepartTime = endDepartTime;
    }

    public Date getBeginArriveTime() {
        return beginArriveTime;
    }

    public void setBeginArriveTime(Date beginArriveTime) {
        this.beginArriveTime = beginArriveTime;
    }

    public Date getEndArriveTime() {
        return endArriveTime;
    }

    public void setEndArriveTime(Date endArriveTime) {
        this.endArriveTime = endArriveTime;
    }

}
