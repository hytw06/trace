package temp.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

// 捐赠对象
public class ProductDonateTo extends BaseEntity {

    @ApiModelProperty(value = "捐赠对象名", name = "poorName")
    private String poorName;

    @ApiModelProperty(value = "捐赠对象身份证号", name = "poorIDCard")
    private String poorIDCard;

    @ApiModelProperty(value = "捐赠对象贫困原因", name = "poorReason")
    private String poorReason;

    @ApiModelProperty(value = "捐赠对象照片", name = "poorIcon")
    private String poorIcon;

    public String getPoorName() {
        return poorName;
    }

    public void setPoorName(String poorName) {
        this.poorName = poorName;
    }

    public String getPoorIDCard() {
        return poorIDCard;
    }

    public void setPoorIDCard(String poorIDCard) {
        this.poorIDCard = poorIDCard;
    }

    public String getPoorReason() {
        return poorReason;
    }

    public void setPoorReason(String poorReason) {
        this.poorReason = poorReason;
    }

    public String getPoorIcon() {
        return poorIcon;
    }

    public void setPoorIcon(String poorIcon) {
        this.poorIcon = poorIcon;
    }

}
