package com.axe.trace.modules.basic.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 原产地Entity
 * @author liyang
 * @version 2020-07-16
 */
public class SourceArea extends BaseEntity {

    @ApiModelProperty(value="产地名称", name="name")
    private String name;

    @ApiModelProperty(value="所属行业", name="industry")
    private String industry;

    @ApiModelProperty(value="地址(省市区)", name="address")
    private String address;

    @ApiModelProperty(value="所属企业或个人", name="belong")
    private String belong;

    @ApiModelProperty(value="联系人", name="contract")
    private String contract;

    @ApiModelProperty(value="联系方式", name="phone")
    private String phone;

    @ApiModelProperty(value="经度", name="longitude")
    private float longitude;

    @ApiModelProperty(value="纬度", name="latitude")
    private float latitude;

    @ApiModelProperty(value="土壤质量", name="soilQuality")
    private String soilQuality;

    @ApiModelProperty(value="空气质量", name="airQuality")
    private String airQuality;

    @ApiModelProperty(value="水质量", name="waterQuality")
    private String waterQuality;

    @ApiModelProperty(value="土壤检测报告", name="soilImage")
    private String soilImage;

    @ApiModelProperty(value="空气检测报告", name="airImage")
    private String airImage;

    @ApiModelProperty(value="水检测报告", name="waterImage")
    private String waterImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getSoilQuality() {
        return soilQuality;
    }

    public void setSoilQuality(String soilQuality) {
        this.soilQuality = soilQuality;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality;
    }

    public String getWaterQuality() {
        return waterQuality;
    }

    public void setWaterQuality(String waterQuality) {
        this.waterQuality = waterQuality;
    }

    public String getSoilImage() {
        return soilImage;
    }

    public void setSoilImage(String soilImage) {
        this.soilImage = soilImage;
    }

    public String getAirImage() {
        return airImage;
    }

    public void setAirImage(String airImage) {
        this.airImage = airImage;
    }

    public String getWaterImage() {
        return waterImage;
    }

    public void setWaterImage(String waterImage) {
        this.waterImage = waterImage;
    }

}
