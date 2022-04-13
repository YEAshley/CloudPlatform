package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * 云工厂管理员主界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class CFAdminFrameController {

    @FXML
    private Button bt_oderInfo;

    @FXML
    private Button bt_device;

    @FXML
    private Button bt_orderPro;

    @FXML
    private Button bt_return;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane CFAdminPane;


    @FXML
    void bt_deviceEvent(ActionEvent event) {
        Pane pane = new MainApp().iniCFDeviceInfoFrame();
        rootPane.getChildren().clear();
        rootPane.getChildren().add(pane);
    }

    @FXML
    void bt_oderInfoEvent(ActionEvent event) {

    }


    @FXML
    void bt_orderProEvent(ActionEvent event) {

    }

    @FXML
    void bt_returnEvent(ActionEvent event) {
             new MainApp().initLoginFrame();
    }

}


