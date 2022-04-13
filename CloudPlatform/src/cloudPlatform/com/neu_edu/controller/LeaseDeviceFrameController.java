package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.beans.property.DeviceInfoTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.MyDeviceService;
import cloudPlatform.com.neu_edu.utils.SetNumUtil;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 租借设备界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class LeaseDeviceFrameController {

    @FXML
    private Stage leaseStage;

    public LeaseDeviceFrameController() throws IOException {
    }

    public Stage getLeaseStage() {
        return leaseStage;
    }

    public void setLeaseStage(Stage leaseStage) {
        this.leaseStage = leaseStage;
    }

    @FXML
    private Button bt_search;

    @FXML
    private TextField tf_search;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devType;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devStatus;

    @FXML
    private Button bt_ok;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devDescription;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_number;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devCode;

    @FXML
    private TableView<DeviceInfoTableData> tv_devInfo;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devName;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devSize;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_action;

    @FXML
    private Button bt_reset;

    private final SimpleUtil simpleUtil = new SimpleUtil();
    private final MyDeviceService myDeviceService = new MyDeviceService();

    public void initialize() {
        //将数据添加到表格控件中
        myDeviceService.setLeaseTableViewData(tv_devInfo,myDeviceService.getLeaseTableViewData(),tc_devCode,tc_devName,
                tc_devType,tc_devSize,tc_devDescription,tc_devStatus);
        //自动添加序号列
        tc_number.setCellFactory(new SetNumUtil<>());
        myDeviceService.autoSetCol5(tc_action);
    }

    @FXML
    void bt_searchEvent(ActionEvent event) {
          tf_search.setVisible(true);
          bt_ok.setVisible(true);
          bt_reset.setVisible(true);
    }

    @FXML
    void bt_okEvent(ActionEvent event) throws DataLoadException {
        String result = myDeviceService.isSearch2(tf_search.getText());
        if (result != null) {
            simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "查询结果", result);
        }
    }

    @FXML
    void bt_resetEvent(ActionEvent event) {
           tf_search.setText("");
    }

}
