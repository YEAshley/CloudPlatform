package cloudPlatform.com.neu_edu.beans;

import javafx.beans.property.StringProperty;

/**
 * 设备信息类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class DeviceInfo {
    private String deviceCode;
    private String deviceName;
    private String deviceType;
    private String deviceSize;
    private String deviceDescription;
    private String deviceStatus;
    private String leaseStatus;
    private String factory;
    private String deviceSource;

    public DeviceInfo(String deviceCode, String deviceName, String deviceType, String deviceSize, String deviceDescription,
                      String deviceStatus, String leaseStatus, String factory,String deviceSource) {
        this.deviceCode = deviceCode;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.deviceSize = deviceSize;
        this.deviceDescription = deviceDescription;
        this.deviceStatus = deviceStatus;
        this.leaseStatus = leaseStatus;
        this.factory = factory;
        this.deviceSource = deviceSource;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceSize() {
        return deviceSize;
    }

    public void setDeviceSize(String deviceSize) {
        this.deviceSize = deviceSize;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getLeaseStatus() {
        return leaseStatus;
    }

    public void setLeaseStatus(String leaseStatus) {
        this.leaseStatus = leaseStatus;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getDeviceSource() {
        return deviceSource;
    }

    public void setDeviceSource(String deviceSource) {
        this.deviceSource = deviceSource;
    }
}
