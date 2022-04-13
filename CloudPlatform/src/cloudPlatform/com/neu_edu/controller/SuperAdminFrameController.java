package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * 超级管理员界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class SuperAdminFrameController {

    @FXML
    private AnchorPane superAdminPane;

    @FXML
    private Button bt_productInfo;

    @FXML
    private Button bt_productType;

    @FXML
    private Button bt_cloudFactoryInfo;

    @FXML
    private Button bt_userManage;

    @FXML
    private Button bt_deviceType;

    @FXML
    private Button bt_deviceInfo;

    @FXML
    private Button bt_return;

    @FXML
    private AnchorPane rootPane;

    private void initialize() {
        Image image = new Image("images/background1.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        rootPane.getChildren().add(imageView);
    }

    @FXML
    void bt_userManageEvent(ActionEvent event) {
        Pane pane = new MainApp().initUserManageFrame();
        rootPane.getChildren().clear();
        rootPane.getChildren().add(pane);
    }

    @FXML
    void bt_cloudFactoryInfoEvent(ActionEvent event) {
        Pane pane = new MainApp().initCloudFactoryInfoFrame();
        rootPane.getChildren().clear();
        rootPane.getChildren().add(pane);
    }

    @FXML
    void bt_productInfoEvent(ActionEvent event) {
        Pane pane = new MainApp().initProductInfoFrame();
        rootPane.getChildren().clear();
        rootPane.getChildren().add(pane);
    }

    @FXML
    void bt_productTypeEvent(ActionEvent event) {
        Pane pane = new MainApp().initProductTypeFrame();
        rootPane.getChildren().clear();
        rootPane.getChildren().add(pane);

    }

    @FXML
    void bt_deviceInfoEvent(ActionEvent event) {
        Pane pane = new MainApp().initDeviceInfoFrame();
        rootPane.getChildren().clear();
        rootPane.getChildren().add(pane);
    }

    @FXML
    void bt_deviceTypeEvent(ActionEvent event) {
        Pane pane = new MainApp().initDeviceTypeFrame();
        rootPane.getChildren().clear();
        rootPane.getChildren().add(pane);
    }

    @FXML
    void bt_returnEvent(ActionEvent event) {
        new MainApp().initLoginFrame();
    }


}







