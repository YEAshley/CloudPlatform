package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.beans.DeviceInfo;
import cloudPlatform.com.neu_edu.beans.property.DeviceInfoTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.DevInfoService;
import cloudPlatform.com.neu_edu.service.impl.MyDeviceService;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.SocketImpl;

/**
 * 云工厂配置产品界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class ConfigureFrameController {

    @FXML
    private Stage configureStage;

    public ConfigureFrameController() throws IOException {
    }

    public Stage getConfigureStage() {
        return configureStage;
    }

    public void setConfigureStage(Stage configureStage) {
        this.configureStage = configureStage;
    }

    @FXML
    private Button bt_delete1;

    @FXML
    private TextField tf_capacity3;

    @FXML
    private Button bt_delete3;

    @FXML
    private Button bt_ok;

    @FXML
    private ComboBox<String> comboBox1;

    @FXML
    private ComboBox<String> comboBox2;

    @FXML
    private ComboBox<String> comboBox3;

    @FXML
    private Button bt_delete2;

    @FXML
    private Button bt_close;

    @FXML
    private TextField tf_devName;

    @FXML
    private TextField tf_capacity1;

    @FXML
    private TextField tf_devCode;

    @FXML
    private TextField tf_capacity2;

    @FXML
    private Button bt_addProduct;

    private DeviceInfoTableData diTD ;

    public DeviceInfoTableData getDiTD() {
        return diTD;
    }

    public void setDiTD(DeviceInfoTableData diTD) {
        this.diTD = diTD;
    }

    private final MyDeviceService myDeviceService = new MyDeviceService();
    private final DevInfoService devInfoService = new DevInfoService();
    private final SimpleUtil simpleUtil = new SimpleUtil();

    public void initialize()  {

        if(diTD!=null){
            tf_devCode.setText(diTD.getDeviceCode());
            tf_devName.setText(diTD.getDeviceName());
        }

    }

    @FXML
    void bt_addProductEvent(ActionEvent event) throws DataLoadException {
        myDeviceService.addComboBoxItems(comboBox1);
        myDeviceService.addComboBoxItems(comboBox2);
        myDeviceService.addComboBoxItems(comboBox3);
    }

    @FXML
    void bt_delete1Event(ActionEvent event) {
        tf_capacity1.setText("");
    }

    @FXML
    void bt_delete2Event(ActionEvent event) {
        tf_capacity2.setText("");
    }

    @FXML
    void bt_delete3Event(ActionEvent event) {
        tf_capacity2.setText("");
    }

    @FXML
    void bt_okEvent(ActionEvent event) {
        tf_capacity1.setText("");
        tf_capacity2.setText("");
        tf_capacity3.setText("");
        simpleUtil.informationDialog(Alert.AlertType.INFORMATION,"提示","产品产能","配置成功！");
    }

    @FXML
    void bt_closeEvent(ActionEvent event) {
        configureStage.close();
    }

}
