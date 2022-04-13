package cloudPlatform.com.neu_edu.beans.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DeviceTypeTableData {
    private StringProperty deviceType;

    public DeviceTypeTableData(String deviceType) {
        this.deviceType = new SimpleStringProperty(deviceType);
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
}
