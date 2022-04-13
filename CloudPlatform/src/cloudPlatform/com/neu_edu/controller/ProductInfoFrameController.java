package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.MainApp;
import cloudPlatform.com.neu_edu.beans.property.ProInfoTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.ProInfoService;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

/**
 * 产品信息界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class ProductInfoFrameController {

    @FXML
    private TableView<ProInfoTableData> tv_productInfo;

    @FXML
    private TableColumn<ProInfoTableData, String> tc_proType;

    @FXML
    private Button bt_okSearch;

    @FXML
    private TableColumn<ProInfoTableData, String> tc_number;

    @FXML
    private TableColumn<ProInfoTableData, String> tc_proCode;

    @FXML
    private TableColumn<ProInfoTableData, String> tc_proDescription;

    @FXML
    private TableColumn<ProInfoTableData, String> tc_proSize;

    @FXML
    private TableColumn<ProInfoTableData, String> tc_action;

    @FXML
    private TableColumn<ProInfoTableData, String> tc_modify;

    @FXML
    private TableColumn<ProInfoTableData, String> tc_delete;

    @FXML
    private Button bt_search;

    @FXML
    private TableColumn<ProInfoTableData, String> tc_proName;

    @FXML
    private TextField tf_productName;

    @FXML
    private Button bt_reset;

    @FXML
    private Text text_tip;

    @FXML
    private Button bt_add;

    private final ProInfoService proInfoService = new ProInfoService();
    private final SimpleUtil simpleUtil = new SimpleUtil();

    public void initialize() throws DataLoadException {
        //将数据添加到表格控件中
        proInfoService.setProInfoTableViewData(tv_productInfo, proInfoService.getProInfoTableViewData(), tc_proCode,
                tc_proName, tc_proType, tc_proSize, tc_proDescription);
        //自动添加序号列
        proInfoService.autoSetNum(tc_number, text_tip);
        //向操作列添加“修改”按钮、“删除”按钮
        proInfoService.autoSetCol1(tc_modify, tc_proCode, tc_proType, tc_proSize, tc_proDescription, tv_productInfo);
        proInfoService.autoSetCol2(tc_delete, tv_productInfo);
    }

    @FXML
    void bt_addEvent(ActionEvent event) {
        new MainApp().initAddProductInfoFrame();
    }

    @FXML
    void bt_searchEvent(ActionEvent event) {
        tf_productName.setVisible(true);
        bt_okSearch.setVisible(true);
        bt_reset.setVisible(true);
    }

    @FXML
    void bt_okSearchEvent(ActionEvent event) throws DataLoadException {
        String result = proInfoService.isSearch(tf_productName.getText());
        if (result != null) {
            simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "查询结果", result);
        }
    }

    @FXML
    void bt_resetEvent(ActionEvent event) {
        tf_productName.setText("");
    }

}
