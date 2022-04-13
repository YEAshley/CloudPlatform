package cloudPlatform.com.neu_edu.beans.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DeviceInfoTableData {
    private final StringProperty deviceCode;
    private final StringProperty deviceName;
    private final StringProperty deviceType;
    private final StringProperty deviceSize;
    private final StringProperty deviceDescription;
    private final StringProperty deviceStatus;
    private final StringProperty leaseStatus;
    private final StringProperty factory;
    private final StringProperty deviceSource;

    public DeviceInfoTableData(String deviceCode, String deviceName, String deviceType, String deviceSize, String deviceDescription,
                               String deviceStatus,String leaseStatus, String factory,String deviceSource) {
        this.deviceCode = new SimpleStringProperty(deviceCode);
        this.deviceName = new SimpleStringProperty(deviceName);
        this.deviceType = new SimpleStringProperty(deviceType);
        this.deviceSize = new SimpleStringProperty(deviceSize);
        this.deviceDescription = new SimpleStringProperty(deviceDescription);
        this.deviceStatus = new SimpleStringProperty(deviceStatus);
        this.leaseStatus = new SimpleStringProperty(leaseStatus);
        this.factory = new SimpleStringProperty(factory);
        this.deviceSource = new SimpleStringProperty(deviceSource);
    }

    public String getDeviceCode() {
        return deviceCode.get();
    }

    public StringProperty deviceCodeProperty() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode.set(deviceCode);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public StringProperty deviceNameProperty() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName.set(deviceName);
    }

    public String getDeviceType() {
        return deviceType.get();
    }

    public StringProperty deviceTypeProperty() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType.set(deviceType);
    }

    public String getDeviceSize() {
        return deviceSize.get();
    }

    public StringProperty deviceSizeProperty() {
        return deviceSize;
    }

    public void setDeviceSize(String deviceSize) {
        this.deviceSize.set(deviceSize);
    }

    public String getDeviceDescription() {
        return deviceDescription.get();
    }

    public StringProperty deviceDescriptionProperty() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription.set(deviceDescription);
    }

    public String getDeviceStatus() {
        return deviceStatus.get();
    }

    public StringProperty deviceStatusProperty() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus.set(deviceStatus);
    }

    public String getLeaseStatus() {
        return leaseStatus.get();
    }

    public StringProperty leaseStatusProperty() {
        return leaseStatus;
    }

    public void setLeaseStatus(String leaseStatus) {
        this.leaseStatus.set(leaseStatus);
    }

    public String getFactory() {
        return factory.get();
    }

    public StringProperty factoryProperty() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory.set(factory);
    }

    public String getDeviceSource() {
        return deviceSource.get();
    }

    public StringProperty deviceSourceProperty() {
        return deviceSource;
    }

    public void setDeviceSource(String deviceSource) {
        this.deviceSource.set(deviceSource);
    }
}
