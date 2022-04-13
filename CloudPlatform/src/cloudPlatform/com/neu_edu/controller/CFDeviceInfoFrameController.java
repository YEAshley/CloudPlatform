package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.MainApp;
import cloudPlatform.com.neu_edu.beans.property.DeviceInfoTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.MyDeviceService;
import cloudPlatform.com.neu_edu.utils.SetNumUtil;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * 我的工厂界面控制类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class CFDeviceInfoFrameController {

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
    private TableColumn<DeviceInfoTableData, String>  tc_configure;

    @FXML
    private TableView<DeviceInfoTableData> tv_devInfo;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devName;

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
    private Button bt_reset;

    @FXML
    private TableColumn<DeviceInfoTableData, String> tc_devSize;

    @FXML
    private Button bt_add;

    @FXML
    private Button bt_lease;

    private final SimpleUtil simpleUtil = new SimpleUtil();
    private final MyDeviceService myDeviceService = new MyDeviceService();

    public CFDeviceInfoFrameController() throws IOException {
    }

    public void initialize(){
        //将数据添加到表格控件中
        myDeviceService.setMyDevTableViewData(tv_devInfo, myDeviceService.getMyDevTableViewData(), tc_devCode, tc_devName, tc_devType,
                tc_devSize, tc_devDescription, tc_devStatus, tc_leaseStatus, tc_factory);

        //自动添加序号列
        tc_number.setCellFactory(new SetNumUtil<>());
        //向操作列添加"远程控制"按钮、“修改”按钮、“删除”按钮
       myDeviceService.autoSetCol1(tc_remote, tv_devInfo);
        myDeviceService.autoSetCol2(tc_modify, tc_devCode, tc_devSize, tc_devDescription, tv_devInfo);
        myDeviceService.autoSetCol3(tc_delete, tv_devInfo);
        myDeviceService.autoSetCol4(tc_configure);
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
        String result = myDeviceService.isSearch(tf_search.getText());
        if (result != null) {
            simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "查询结果", result);
        }
    }

    @FXML
    void bt_resetEvent(ActionEvent event) {
        tf_search.setText("");
    }

    @FXML
    void  bt_leaseEvent(ActionEvent event) {
           new MainApp().initLeaseDeviceFrame();
    }
}
