package temp.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

// 企业或贫困户信息
public class PlotOrigin extends BaseEntity {

    @ApiModelProperty(value = "名称", name = "OriginName")
    private String OriginName;

    @ApiModelProperty(value = "地址", name = "OriginAddress")
    private String OriginAddress;

    public String getOriginName() {
        return OriginName;
    }

    public void setOriginName(String originName) {
        OriginName = originName;
    }

    public String getOriginAddress() {
        return OriginAddress;
    }

    public void setOriginAddress(String originAddress) {
        OriginAddress = originAddress;
    }

}
