package temp.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

// 质检
public class QualityControl extends BaseEntity {

    @ApiModelProperty(value = "质检指标", name = "checkTarget")
    private String checkTarget;

    @ApiModelProperty(value = "质检结果", name = "checkResult")
    private String checkResult;

    @ApiModelProperty(value = "检测时间", name = "checkTime")
    private Date checkTime;

    @ApiModelProperty(value = "质检机构", name = "checkOrganization")
    private String checkOrganization;

    public String getCheckTarget() {
        return checkTarget;
    }

    public void setCheckTarget(String checkTarget) {
        this.checkTarget = checkTarget;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckOrganization() {
        return checkOrganization;
    }

    public void setCheckOrganization(String checkOrganization) {
        this.checkOrganization = checkOrganization;
    }

}
