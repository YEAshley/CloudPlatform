package cloudPlatform.com.neu_edu;

import cloudPlatform.com.neu_edu.beans.property.DeviceInfoTableData;
import cloudPlatform.com.neu_edu.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


/**
 * 系统主类，主要用于加载界面
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private final Stage superAdminStage = new Stage();
    private final Stage cFAdminStage = new Stage();

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("东软智能制造云平台");
        //启动界面即为登录界面
        initWelcomeFrame();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void initWelcomeFrame() {
        try {
            // 加载登录界面的fxml文件
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/welcomeFrame.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("东软智能制造云平台");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            //去掉边框
            primaryStage.initStyle(StageStyle.UNDECORATED);
            // primaryStage.initStyle(StageStyle.UNIFIED);

            //获取首界面的控制器类
            WelcomeFrameController controller = loader.getController();
            //将stage作为参数传递到控制器中
            controller.setStage(primaryStage);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 登录界面
     */
    public void initLoginFrame() {
        //加载登录界面的fxml文件
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/loginFrame.fxml"));
            AnchorPane root = loader.load();
            //设置stage舞台属性
            Stage loginStage = new Stage();
            Scene scene = new Scene(root);
            loginStage.setTitle("用户登录");
            loginStage.setScene(scene);
            loginStage.setResizable(false);
            loginStage.getIcons().add(new Image("cloudPlatform\\com\\neu_edu\\view\\images\\cloud.png"));
            LoginFrameController controller = loader.getController();
            controller.setStage(loginStage);
            //展示舞台
            loginStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 注册界面
     */
    public void initRegisterFrame() {
        try {
            // 加载登录界面的fxml文件
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/registerFrame.fxml"));
            Pane root = loader.load();

            //设置stage舞台属性
            Stage registerStage = new Stage();
            Scene scene = new Scene(root);
            registerStage.setTitle("用户注册");
            registerStage.setScene(scene);
            registerStage.setResizable(false);
            registerStage.getIcons().add(new Image("cloudPlatform\\com\\neu_edu\\view\\images\\register2.png"));
            RegisterFrameController controller = loader.getController();
            controller.setRegisterStage(registerStage);
            //展示舞台
            registerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户管理界面
     */
    public void initSuperAdminFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/superAdminFrame.fxml"));
            AnchorPane rootLayout = loader.load();


            Scene scene = new Scene(rootLayout);
            superAdminStage.setTitle("超级管理员界面");
            superAdminStage.setResizable(false);
            superAdminStage.setAlwaysOnTop(false);
            //superAdminStage.initModality(Modality.APPLICATION_MODAL);
            //superAdminStage.initOwner(primaryStage);
            superAdminStage.setScene(scene);
            superAdminStage.getIcons().add(new Image("cloudPlatform\\com\\neu_edu\\view\\images\\Admin.png"));
            superAdminStage.show();
            //superAdminStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initCFAdminFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/cFAdminFrame.fxml"));
            AnchorPane cloudRoot = loader.load();

            Scene scene = new Scene(cloudRoot);
            cFAdminStage.setTitle("云工厂管理员界面");
            cFAdminStage.setResizable(false);
            cFAdminStage.setAlwaysOnTop(false);
            cFAdminStage.setScene(scene);
            // cFAdminStage.getIcons().add(new Image("cloudPlatform\\com\\neu_edu\\view\\images\\Admin.png"));

            cFAdminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane initUserManageFrame() {
        return loadFrame("view/userManageFrame.fxml");
    }

    public void initAddUserFrame() {
        //加载登录界面的fxml文件
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/addUserFrame.fxml"));
            AnchorPane root = loader.load();
            //设置stage舞台属性
            Stage addUserStage = new Stage();
            Scene scene = new Scene(root);
            addUserStage.setTitle("新建用户信息");
            addUserStage.setScene(scene);
            addUserStage.setResizable(false);
            addUserStage.getIcons().add(new Image("cloudPlatform\\com\\neu_edu\\view\\images\\add.png"));
            AddUserFrameController controller = loader.getController();
            //将stage作为参数传递到控制器中
            controller.setAddUserStage(addUserStage);
            //展示舞台
            addUserStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initAddProductInfoFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/addProductInfoFrame.fxml"));
            AnchorPane pane = loader.load();

            Stage addProductStage = new Stage();
            Scene scene = new Scene(pane);
            addProductStage.setTitle("新建产品信息");
            addProductStage.setScene(scene);
            addProductStage.setResizable(false);
            addProductStage.getIcons().add(new Image("cloudPlatform\\com\\neu_edu\\view\\images\\add.png"));
            AddProductInfoFrameController controller = loader.getController();
            controller.setAddProInfoStage(addProductStage);
            addProductStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initAddDeviceInfoFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/addDeviceInfoFrame.fxml"));
            AnchorPane pane = loader.load();

            Stage addDevInfoStage = new Stage();
            Scene scene = new Scene(pane);
            addDevInfoStage.setTitle("新建设备信息");
            addDevInfoStage.setScene(scene);
            addDevInfoStage.setResizable(false);
            addDevInfoStage.getIcons().add(new Image("cloudPlatform\\com\\neu_edu\\view\\images\\add.png"));
            AddDeviceInfoFrameController controller = loader.getController();
            controller.setAddDevInfoStage(addDevInfoStage);
            addDevInfoStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initConfigureFrame(DeviceInfoTableData deviceInfoTableData) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/configureFrame.fxml"));
            Pane pane = loader.load();

            Stage configureStage = new Stage();
            Scene scene = new Scene(pane);
            configureStage.setTitle("添加产品产能信息");
            configureStage.setScene(scene);
            configureStage.setResizable(false);

            ConfigureFrameController controller = loader.getController();
            controller.setConfigureStage(configureStage);
            controller.setDiTD(deviceInfoTableData);
            configureStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initLeaseDeviceFrame() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/leaseDeviceFrame.fxml"));
            Pane pane = loader.load();

            Stage leaseStage = new Stage();
            Scene scene = new Scene(pane);
            leaseStage.setTitle("设备信息列表");
            leaseStage.setScene(scene);
            leaseStage.setResizable(false);

            LeaseDeviceFrameController controller = loader.getController();
            controller.setLeaseStage(leaseStage);
            leaseStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane initCloudFactoryInfoFrame() {
        return loadFrame("view/cloudFactoryInfoFrame.fxml");
    }

    public Pane initProductTypeFrame() {
        return loadFrame("view/productTypeFrame.fxml");
    }

    public Pane initProductInfoFrame() {
        return loadFrame("view/productInfoFrame.fxml");
    }

    public Pane initDeviceTypeFrame() {
        return loadFrame("view/deviceTypeFrame.fxml");
    }

    public Pane initDeviceInfoFrame() {
        return loadFrame("view/deviceInfoFrame.fxml");
    }

    public Pane iniCFDeviceInfoFrame() {
        return loadFrame("view/cFDeviceInfoFrame.fxml");
    }

    public Pane loadFrame(String name) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(name));
            Pane pane = loader.load();
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
