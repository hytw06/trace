package temp.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

// 仓储
public class Storage extends BaseEntity {

    @ApiModelProperty(value="仓库地址", name="storageAddress")
    private String storageAddress;

    @ApiModelProperty(value="入库数量", name="storageNumber")
    private String storageNumber;

    @ApiModelProperty(value="入库时间", name="storageTime")
    private Date storageTime;

    @ApiModelProperty(value="操作员", name="storageOperator")
    private String storageOperator;

    public String getStorageAddress() {
        return storageAddress;
    }

    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }

    public String getStorageNumber() {
        return storageNumber;
    }

    public void setStorageNumber(String storageNumber) {
        this.storageNumber = storageNumber;
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public String getStorageOperator() {
        return storageOperator;
    }

    public void setStorageOperator(String storageOperator) {
        this.storageOperator = storageOperator;
    }

}
