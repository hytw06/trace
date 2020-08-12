package temp.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

// 农事操作
public class FarmOperation extends BaseEntity {

    @ApiModelProperty(value="操作名称", name="operateAction")
    private String operateAction;

    @ApiModelProperty(value="操作描述", name="operateRemark")
    private String operateRemark;

    @ApiModelProperty(value="操作描述", name="operateRemark")
    private Date operatedDate;

    @ApiModelProperty(value="操作员", name="operateOperator")
    private String operateOperator;

    public String getOperateAction() {
        return operateAction;
    }

    public void setOperateAction(String operateAction) {
        this.operateAction = operateAction;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
    }

    public Date getOperatedDate() {
        return operatedDate;
    }

    public void setOperatedDate(Date operatedDate) {
        this.operatedDate = operatedDate;
    }

    public String getOperateOperator() {
        return operateOperator;
    }

    public void setOperateOperator(String operateOperator) {
        this.operateOperator = operateOperator;
    }

}
