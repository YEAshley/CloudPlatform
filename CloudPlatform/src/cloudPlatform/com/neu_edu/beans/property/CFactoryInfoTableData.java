package cloudPlatform.com.neu_edu.beans.property;

import javafx.beans.property.SimpleStringProperty;

public class CFactoryInfoTableData {
    private final SimpleStringProperty factoryName;
    private final SimpleStringProperty factoryProfile;
    private final SimpleStringProperty name;
    private final SimpleStringProperty id;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty status;

    public CFactoryInfoTableData(String factoryName, String factoryProfile, String name, String id, String phone,String status) {
        this.factoryName = new SimpleStringProperty(factoryName);
        this.factoryProfile = new SimpleStringProperty(factoryProfile);
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
        this.phone = new SimpleStringProperty(phone);
        this.status = new SimpleStringProperty(status);
    }

    public String getFactoryName() {
        return factoryName.get();
    }

    public SimpleStringProperty factoryNameProperty() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName.set(factoryName);
    }

    public String getFactoryProfile() {
        return factoryProfile.get();
    }

    public SimpleStringProperty factoryProfileProperty() {
        return factoryProfile;
    }

    public void setFactoryProfile(String factoryProfile) {
        this.factoryProfile.set(factoryProfile);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
