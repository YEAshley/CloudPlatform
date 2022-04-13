package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.MainApp;
import cloudPlatform.com.neu_edu.beans.property.DeviceInfoTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.DevInfoService;
import cloudPlatform.com.neu_edu.utils.SetNumUtil;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * 设备信息管理界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class DeviceInfoFrameController {

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devType;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devStatus;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_factory;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_number;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devCode;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_remote;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devName;

    @FXML
    private Button bt_refresh;

    @FXML
    private Button bt_search;

    @FXML
    private TextField tf_search;

    @FXML
    private Button bt_ok;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devDescription;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_leaseStatus;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_delete;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_modify;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devSize;

    @FXML
    private TableView<DeviceInfoTableData> tv_devInfo;

    @FXML
    private Button bt_reset;

    @FXML
    private Button bt_add;

    private final DevInfoService devInfoService = new DevInfoService();
    private final SimpleUtil simpleUtil = new SimpleUtil();

    public void initialize() {
        //将数据添加到表格控件中
        devInfoService.setDevInfoTableViewData(tv_devInfo, devInfoService.getDevInfoTableViewData(), tc_devCode, tc_devName, tc_devType,
                tc_devSize, tc_devDescription, tc_devStatus, tc_leaseStatus, tc_factory);
        //自动添加序号列
        tc_number.setCellFactory(new SetNumUtil<>());
        //向操作列添加"远程控制"按钮、“修改”按钮、“删除”按钮
        devInfoService.autoSetCol1(tc_remote, tv_devInfo);
        devInfoService.autoSetCol2(tc_modify, tc_devCode, tc_devSize, tc_devDescription, tv_devInfo);
        devInfoService.autoSetCol3(tc_delete, tv_devInfo);
    }

    @FXML
    void bt_addEvent(ActionEvent event) {
        new MainApp().initAddDeviceInfoFrame();
    }

    @FXML
    void bt_searchEvent(ActionEvent event) {
        tf_search.setVisible(true);
        bt_ok.setVisible(true);
        bt_reset.setVisible(true);
    }

    @FXML
    void bt_okEvent(ActionEvent event) throws DataLoadException {
        String result = devInfoService.isSearch(tf_search.getText());
        if (result != null) {
            simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "查询结果", result);
        }
    }

    @FXML
    void bt_resetEvent(ActionEvent event) {
        tf_search.setText("");
    }


    @FXML
    void bt_refreshEvent(ActionEvent event) {
        tv_devInfo.setItems(null);
        tv_devInfo.setItems(devInfoService.getDevInfoTableViewData());
    }

}
