package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.MainApp;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 添加用户信息界面控制类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class AddUserFrameController {

    @FXML
    private Stage addUserStage;

    public Stage getAddUserStage() {
        return addUserStage;
    }

    public void setAddUserStage(Stage addUserStage) {
        this.addUserStage = addUserStage;
    }

    @FXML
    private RadioButton bt_cloudFactory;

    @FXML
    private Label lb_factoryProfile;

    @FXML
    private Button bt_close;

    @FXML
    private PasswordField pf_password;

    @FXML
    private TextField tf_factoryProfile;

    @FXML
    private Label lb_factoryName;

    @FXML
    private ToggleGroup actor;

    @FXML
    private Button bt_ok;

    @FXML
    private TextField tf_factoryName;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_phone;

    @FXML
    private RadioButton bt_franchiser;

    @FXML
    void bt_cloudFactoryEvent(ActionEvent event) {
        lb_factoryName.setVisible(true);
        lb_factoryProfile.setVisible(true);
        tf_factoryName.setVisible(true);
        tf_factoryProfile.setVisible(true);
    }

    @FXML
    void bt_franchiserEvent(ActionEvent event) {
        lb_factoryName.setVisible(false);
        lb_factoryProfile.setVisible(false);
        tf_factoryName.setVisible(false);
        tf_factoryProfile.setVisible(false);
    }

    private UserService userService= new UserService();
    @FXML
    void bt_okEvent(ActionEvent event) throws IOException, DataLoadException {
        boolean isOK = userService.isAdd(tf_id,pf_password,tf_name,tf_phone,bt_cloudFactory,actor,
                tf_factoryName,tf_factoryProfile);
        if(isOK) {
           addUserStage.close();
        }
    }

    @FXML
    void bt_closeEvent(ActionEvent event) {
        addUserStage.close();
    }


}
