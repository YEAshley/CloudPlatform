package cloudPlatform.com.neu_edu.beans.property;

import javafx.beans.property.SimpleStringProperty;

public class UserTableData {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty actor;

   /* public UserTableData(){

    }*/

    public UserTableData(String id, String name, String phone, String actor) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.actor = new SimpleStringProperty(actor);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getActor() {
        return actor.get();
    }

    public SimpleStringProperty actorProperty() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor.set(actor);
    }

    @Override
    public String toString() {
        return "UserTableData{" +
                "id=" + id +
                ", name=" + name +
                ", phone=" + phone +
                ", actor=" + actor +
                '}';
    }
}
