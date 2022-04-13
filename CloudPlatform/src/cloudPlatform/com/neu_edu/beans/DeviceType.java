package cloudPlatform.com.neu_edu.beans;

import java.util.List;

/**
 * 设备类型类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class DeviceType {
    private String deviceType;
    private List<DeviceInfo> devInfos;

    public DeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
