package temp.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

// 产地
public class AgriculturePlot extends BaseEntity {

    @ApiModelProperty(value="地块名称", name="plotName")
    private String plotName;

    @ApiModelProperty(value="产地类型", name="plotType")
    private String plotType;

    @ApiModelProperty(value="产地环境介绍", name="plotEnviromentArr")
    private String plotEnviromentArr;

    @ApiModelProperty(value="企业或贫困户信息", name="plotOrigin")
    private PlotOrigin plotOrigin;

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public String getPlotType() {
        return plotType;
    }

    public void setPlotType(String plotType) {
        this.plotType = plotType;
    }

    public String getPlotEnviromentArr() {
        return plotEnviromentArr;
    }

    public void setPlotEnviromentArr(String plotEnviromentArr) {
        this.plotEnviromentArr = plotEnviromentArr;
    }

    public PlotOrigin getPlotOrigin() {
        return plotOrigin;
    }

    public void setPlotOrigin(PlotOrigin plotOrigin) {
        this.plotOrigin = plotOrigin;
    }

}
