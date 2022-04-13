package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.beans.property.DeviceTypeTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.DevTypeService;
import cloudPlatform.com.neu_edu.utils.SetNumUtil;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * 设备类型管理界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class DeviceTypeFrameController {

    @FXML
    private TableView<DeviceTypeTableData> tv_deviceType;


    @FXML
    private TableColumn<DeviceTypeTableData, String> tc_number;

    @FXML
    private TableColumn<DeviceTypeTableData, String> tc_delete;

    @FXML
    private TableColumn<DeviceTypeTableData, String> tc_modify;

    @FXML
    private TableColumn<DeviceTypeTableData, String> tc_devName;

    @FXML
    private Button bt_search;

    @FXML
    private TextField tf_search;

    @FXML
    private Button bt_okSearch;

    @FXML
    private TextField tf_add;

    @FXML
    private Button bt_okAdd;

    @FXML
    private Button bt_reset;

    @FXML
    private Button bt_add;

    private final SimpleUtil simpleUtil = new SimpleUtil();
    private final DevTypeService devTypeService = new DevTypeService();


    public void initialize() throws DataLoadException {
        //将数据添加到表格控件中
        devTypeService.setDevTypeTableViewData(tv_deviceType,devTypeService.getDevTypeTableViewData(),tc_devName);
        //自动添加序号列
       tc_number.setCellFactory(new SetNumUtil<>());
        devTypeService.autoSetCol1(tc_modify,tc_devName,tv_deviceType);
        devTypeService.autoSetCol2(tc_delete,tv_deviceType);
    }

    @FXML
    void bt_addEvent(ActionEvent event) {
          tf_add.setVisible(true);
          bt_okAdd.setVisible(true);
    }

    @FXML
    void bt_searchEvent(ActionEvent event) throws IOException, DataLoadException {
         tf_search.setVisible(true);
         bt_okSearch.setVisible(true);
         bt_reset.setVisible(true);
    }

    @FXML
    void bt_okSearchEvent(ActionEvent event) throws DataLoadException {
        String result = devTypeService.isSearch(tf_search.getText());
        if (result != null) {
            simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "查询结果", result);
        }
    }

    @FXML
    void bt_resetEvent(ActionEvent event) {
         tf_search.setText("");
    }

    @FXML
    void bt_okAddEvent(ActionEvent event) throws IOException, DataLoadException {
        devTypeService.isOkAdd(tf_add,tv_deviceType);
    }

}
