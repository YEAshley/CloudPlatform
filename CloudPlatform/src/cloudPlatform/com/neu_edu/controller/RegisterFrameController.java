package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.MainApp;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 注册界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class RegisterFrameController {

    @FXML
    private Stage registerStage;

    public Stage getRegisterStage() {
        return registerStage;
    }

    public void setRegisterStage(Stage registerStage) {
        this.registerStage = registerStage;
    }

    @FXML
    private ToggleGroup actor;

    @FXML
    private RadioButton bt_cloudFactory;

    @FXML
    private Button bt_ok;

    @FXML
    private Button bt_close;

    @FXML
    private TextField tf_factoryName;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_id;

    @FXML
    private PasswordField pf_password;

    @FXML
    private TextField tf_phone;

    @FXML
    private TextField tf_factoryProfile;

    @FXML
    private Label lb_factoryName;

    @FXML
    private Label lb_factoryProfile;

    @FXML
    private RadioButton bt_franchiser;

    @FXML
    void bt_franchiserEvent(ActionEvent event) {
        lb_factoryName.setVisible(false);
        lb_factoryProfile.setVisible(false);
        tf_factoryName.setVisible(false);
        tf_factoryProfile.setVisible(false);

    }

    @FXML
    void bt_cloudFactoryEvent(ActionEvent event) {
        lb_factoryName.setVisible(true);
        lb_factoryProfile.setVisible(true);
        tf_factoryName.setVisible(true);
        tf_factoryProfile.setVisible(true);
    }

    private UserService userService = new UserService();

    @FXML
    void bt_okEvent(ActionEvent event) throws IOException, DataLoadException {

        boolean isOK = userService.isAdd(tf_id, pf_password, tf_name, tf_phone, bt_cloudFactory, actor,
                tf_factoryName, tf_factoryProfile);
        if (isOK) {
            registerStage.close();
            new MainApp().initLoginFrame();
        }

    }

    @FXML
    void bt_closeEvent(ActionEvent event) {
        new MainApp().initLoginFrame();
    }

}
