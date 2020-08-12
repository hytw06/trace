package temp.entity;

import com.axe.trace.sys.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

// 运输
public class Transport extends BaseEntity {

    @ApiModelProperty(value="运输车牌号", name="transportLicenseNumber")
    private String transportLicenseNumber;

    @ApiModelProperty(value="驾驶员", name="transportDriver")
    private String transportDriver;

    @ApiModelProperty(value="车辆类型", name="transportInformationOfCar")
    private String transportInformationOfCar;

    @ApiModelProperty(value="出发时间", name="transportDepartTime")
    private Date transportDepartTime;

    @ApiModelProperty(value="抵达时间", name="transportArriveTime")
    private Date transportArriveTime;

    @ApiModelProperty(value="起始地", name="transportDeparture")
    private String transportDeparture;

    @ApiModelProperty(value="目的地", name="transportDestination")
    private String transportDestination;

    public String getTransportLicenseNumber() {
        return transportLicenseNumber;
    }

    public void setTransportLicenseNumber(String transportLicenseNumber) {
        this.transportLicenseNumber = transportLicenseNumber;
    }

    public String getTransportDriver() {
        return transportDriver;
    }

    public void setTransportDriver(String transportDriver) {
        this.transportDriver = transportDriver;
    }

    public String getTransportInformationOfCar() {
        return transportInformationOfCar;
    }

    public void setTransportInformationOfCar(String transportInformationOfCar) {
        this.transportInformationOfCar = transportInformationOfCar;
    }

    public Date getTransportDepartTime() {
        return transportDepartTime;
    }

    public void setTransportDepartTime(Date transportDepartTime) {
        this.transportDepartTime = transportDepartTime;
    }

    public Date getTransportArriveTime() {
        return transportArriveTime;
    }

    public void setTransportArriveTime(Date transportArriveTime) {
        this.transportArriveTime = transportArriveTime;
    }

    public String getTransportDeparture() {
        return transportDeparture;
    }

    public void setTransportDeparture(String transportDeparture) {
        this.transportDeparture = transportDeparture;
    }

    public String getTransportDestination() {
        return transportDestination;
    }

    public void setTransportDestination(String transportDestination) {
        this.transportDestination = transportDestination;
    }

}
