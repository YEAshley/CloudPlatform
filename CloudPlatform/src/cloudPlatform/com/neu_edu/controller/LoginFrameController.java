package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.MainApp;
import cloudPlatform.com.neu_edu.beans.CloudFactoryAdmin;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.FactoryService;
import cloudPlatform.com.neu_edu.service.impl.MyDeviceService;
import cloudPlatform.com.neu_edu.service.impl.UserService;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 登录界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class LoginFrameController {

    @FXML
    private Stage loginStage;


    public Stage getStage() {
        return loginStage;
    }

    public void setStage(Stage stage) {
        this.loginStage = stage;
    }

    @FXML
    private Label lb_password;

    @FXML
    private Button bt_login;

    @FXML
    private Button bt_register;

    @FXML
    private TextField tf_id;

    @FXML
    private PasswordField pf_password;

    @FXML
    private Label lb_id;

    private UserService userService = new UserService();
    private SimpleUtil simpleUtil = new SimpleUtil();

    public void initialize(){}

    @FXML
    void bt_loginEvent(ActionEvent event) throws IOException {
        //关闭登录界面

        //判断用户名和密码是否可用及匹配
        boolean isOK = userService.isLogin(tf_id,pf_password);
        if (isOK) {
            loginStage.close();
            if (userService.actor(tf_id.getText()).equals("超级管理员")){
                new MainApp().initSuperAdminFrame();
            } else if(userService.actor(tf_id.getText()).equals("云工厂")) {
               String factory = userService.getFactory(tf_id.getText());
                FileUtils.write(new File("C:\\Users\\Ashley\\IdeaProjects\\JavaFX\\CloudPlatform\\data\\myFactory.txt"),factory,false);
                new MainApp().initCFAdminFrame();
            }
        }

    }

    @FXML
    void bt_registerEvent(ActionEvent event) {
           loginStage.close();
           new MainApp().initRegisterFrame();
           simpleUtil.informationDialog(Alert.AlertType.INFORMATION,"提示","账号注册请注意：","云工厂管理员 c 开头\n经销商 f 开头");

    }


}
