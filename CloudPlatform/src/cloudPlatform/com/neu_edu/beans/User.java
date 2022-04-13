package cloudPlatform.com.neu_edu.beans;

/**
 * 用户类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class User {
    private String id;
    private String name;
    private String phone;
    private String actor;
    private String password;

    public User(String id, String name, String phone, String actor,String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.actor = actor;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String toString(){
        return id + " " + name + " " + phone + " " + actor;
    }

}
