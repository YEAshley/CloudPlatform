package cloudPlatform.com.neu_edu.beans;

import java.util.List;

/**
 * 云工厂类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class CloudFactoryAdmin extends User {
    private String factoryName;
    private String factoryProfile;
    private String status;
    private List<DeviceInfo> deviceInfos;

    public CloudFactoryAdmin(String id, String name, String phone, String actor, String password, String factoryName,
                             String factoryProfile, String status) {
        super(id, name, phone, actor, password);
        this.factoryName = factoryName;
        this.factoryProfile = factoryProfile;
        this.status = status;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryProfile() {
        return factoryProfile;
    }

    public void setFactoryProfile(String factoryProfile) {
        this.factoryProfile = factoryProfile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addDevice(DeviceInfo device) {
        deviceInfos.add(device);
    }

    public void removeDevice(DeviceInfo device) {
        deviceInfos.remove(device);
    }

}


