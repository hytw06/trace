package com.axe.trace.modules.basic.entity;

import com.axe.trace.sys.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 农产品Entity
 * @author liyang
 * @version 2020-07-16
 */
public class Product extends BaseEntity {

    @ApiModelProperty(value="原产地id", name="sourceAreaId")
    private String sourceAreaId;

    @ApiModelProperty(value="原产地名称", name="sourceAreaName")
    private String sourceAreaName;

    @ApiModelProperty(value="产品名称", name="name")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @ApiModelProperty(value="生产日期", name="productDate")
    private Date productDate;

    @ApiModelProperty(value="保质期", name="saveDate")
    private String saveDate;

    @ApiModelProperty(value="农产品详情", name="detail")
    private String detail;

    @ApiModelProperty(value="产品批次", name="productBatch")
    private String productBatch;

    @ApiModelProperty(value="视频", name="video")
    private String video;

    @ApiModelProperty(value="二维码", name="qrcode")
    private String qrcode;

    @ApiModelProperty(value="溯源次数", name="traceTime")
    private int traceTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getProductBatch() {
        return productBatch;
    }

    public void setProductBatch(String productBatch) {
        this.productBatch = productBatch;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public int getTraceTime() {
        return traceTime;
    }

    public void setTraceTime(int traceTime) {
        this.traceTime = traceTime;
    }
    
}
