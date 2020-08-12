package temp.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TraceInfo extends BaseEntity {

    @ApiModelProperty(value="产品批次号", name="traceSourceCode")
    private String traceSourceCode;

    @ApiModelProperty(value="产品视频", name="videoUrl")
    private String videoUrl;

    @ApiModelProperty(value="经度", name="longitude")
    private String longitude;

    @ApiModelProperty(value="纬度", name="latitude")
    private String latitude;

    @ApiModelProperty(value="溯源次数", name="traceSourceTimes")
    private int traceSourceTimes;

    @ApiModelProperty(value="商品捐赠", name="product")
    private Product product;

    @ApiModelProperty(value="产地", name="agriculturePlot")
    private AgriculturePlot agriculturePlot;

    @ApiModelProperty(value="种子", name="agriculture")
    private Agriculture agriculture;

    @ApiModelProperty(value="农事操作", name="farmOperation")
    private List<FarmOperation> farmOperation;

    @ApiModelProperty(value="仓储", name="storage")
    private Storage storage;

    @ApiModelProperty(value="质检", name="qualityControl")
    private List<QualityControl> qualityControl;

    @ApiModelProperty(value="运输", name="transport")
    private Transport transport;

    public String getTraceSourceCode() {
        return traceSourceCode;
    }

    public void setTraceSourceCode(String traceSourceCode) {
        this.traceSourceCode = traceSourceCode;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getTraceSourceTimes() {
        return traceSourceTimes;
    }

    public void setTraceSourceTimes(int traceSourceTimes) {
        this.traceSourceTimes = traceSourceTimes;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AgriculturePlot getAgriculturePlot() {
        return agriculturePlot;
    }

    public void setAgriculturePlot(AgriculturePlot agriculturePlot) {
        this.agriculturePlot = agriculturePlot;
    }

    public Agriculture getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(Agriculture agriculture) {
        this.agriculture = agriculture;
    }

    public List<FarmOperation> getFarmOperation() {
        return farmOperation;
    }

    public void setFarmOperation(List<FarmOperation> farmOperation) {
        this.farmOperation = farmOperation;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<QualityControl> getQualityControl() {
        return qualityControl;
    }

    public void setQualityControl(List<QualityControl> qualityControl) {
        this.qualityControl = qualityControl;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

}
