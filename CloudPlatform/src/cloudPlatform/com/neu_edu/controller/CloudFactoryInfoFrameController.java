package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.beans.property.CFactoryInfoTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.FactoryService;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * 云工厂信息管理界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class CloudFactoryInfoFrameController {

    @FXML
    private Button bt_search;

    @FXML
    private Button bt_okSearch;

    @FXML
    private TableColumn<CFactoryInfoTableData, String> tc_action;

    @FXML
    private TextField tf_search;

    @FXML
    private TableColumn<CFactoryInfoTableData, String> tc_factoryProfile;

    @FXML
    private TableColumn<CFactoryInfoTableData, String> tc_number;

    @FXML
    private TableColumn<CFactoryInfoTableData, String> tc_factoryName;

    @FXML
    private TableColumn<CFactoryInfoTableData, String> tc_phone;

    @FXML
    private TableColumn<CFactoryInfoTableData, String> tc_status;

    @FXML
    private TableColumn<CFactoryInfoTableData, String> temp;

    @FXML
    private Button bt_reset;

    @FXML
    private TableColumn<CFactoryInfoTableData, String> tc_id;

    @FXML
    private TableColumn<CFactoryInfoTableData, String>tc_name;

    @FXML
    private TableView<CFactoryInfoTableData> tv_FactoryInfo;

    @FXML
    private Text text_tip;

    private final FactoryService factoryService = new FactoryService();
    private final SimpleUtil simpleUtil = new SimpleUtil();

    @FXML
    private void initialize() throws IOException {

        //将数据添加到表格控件中
        factoryService.setCFactoryInfoTableData(tv_FactoryInfo,factoryService.getCloudFactoryInfoTableData(),tc_factoryName,
                tc_factoryProfile,tc_name,tc_id,tc_phone,tc_status);

        //自动添加序号列
        factoryService.autoSetNum(tc_number,text_tip);

        //向状态列和操作列分别添加状态按钮toggleButton
       // factoryService.autoSetCol1(tc_status);
        factoryService.autoSetCol1(tc_action,tv_FactoryInfo);
    }

    /**
     * "检索"按钮的监听器事件
     */
    @FXML
    void bt_searchEvent(ActionEvent event) {
        tf_search.setVisible(true);
        bt_okSearch.setVisible(true);
        bt_reset.setVisible(true);
    }

    /**
     * "查询"按钮的监听器事件，以弹框形式，提示查询结果
     */
    @FXML
    void bt_okSearchEvent(ActionEvent event) throws DataLoadException, IOException {
        String result = factoryService.isSearch(tf_search.getText());
        if (result != null) {
            simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "查询结果", result);
        }
    }

    /**
     * "重置"按钮的监听器事件，将查询框内容清空
     */
    @FXML
    void bt_resetEvent(ActionEvent event) {
        tf_search.setText("");
    }

}
