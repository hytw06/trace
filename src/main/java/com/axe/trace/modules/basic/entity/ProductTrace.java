package com.axe.trace.modules.basic.entity;

import com.axe.trace.modules.process.entity.Operate;
import com.axe.trace.modules.process.entity.QualityCheck;
import com.axe.trace.modules.process.entity.Storage;
import com.axe.trace.modules.process.entity.Transport;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 * 农产品流程溯源Entity
 * @author liyang
 * @version 2020-08-18
 */
public class ProductTrace implements Serializable {

    @ApiModelProperty(value="原产地", name="sourceArea")
    private SourceArea sourceArea;

    @ApiModelProperty(value="产品", name="product")
    private Product product;

    @ApiModelProperty(value="农事操作", name="operateList")
    private List<Operate> operateList;

    @ApiModelProperty(value="质量检测", name="qualityCheckList")
    private List<QualityCheck> qualityCheckList;

    @ApiModelProperty(value="入库", name="storageList")
    private List<Storage> storageList;

    @ApiModelProperty(value="运输", name="transportList")
    private List<Transport> transportList;

    public SourceArea getSourceArea() {
        return sourceArea;
    }

    public void setSourceArea(SourceArea sourceArea) {
        this.sourceArea = sourceArea;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Operate> getOperateList() {
        return operateList;
    }

    public void setOperateList(List<Operate> operateList) {
        this.operateList = operateList;
    }

    public List<QualityCheck> getQualityCheckList() {
        return qualityCheckList;
    }

    public void setQualityCheckList(List<QualityCheck> qualityCheckList) {
        this.qualityCheckList = qualityCheckList;
    }

    public List<Storage> getStorageList() {
        return storageList;
    }

    public void setStorageList(List<Storage> storageList) {
        this.storageList = storageList;
    }

    public List<Transport> getTransportList() {
        return transportList;
    }

    public void setTransportList(List<Transport> transportList) {
        this.transportList = transportList;
    }

}
