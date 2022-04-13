package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.beans.property.ProTypeTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.ProTypeService;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * 产品类型管理界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class ProductTypeFrameController {

    @FXML
    private TableColumn<ProTypeTableData, String> tc_action;

    @FXML
    private TableColumn<ProTypeTableData, String> tc_modify;

    @FXML
    private TableColumn<ProTypeTableData, String> tc_delete;

    @FXML
    private Button bt_search;

    @FXML
    private TextField tf_search;

    @FXML
    private TableView<ProTypeTableData> tv_productType;

    @FXML
    private Button bt_okSearch;

    @FXML
    private TableColumn<ProTypeTableData, String> tc_number;

    @FXML
    private TableColumn<ProTypeTableData, String> tc_productType;

    @FXML
    private Button bt_reset;

    @FXML
    private Text text_tip;

    @FXML
    private Button bt_add;

    @FXML
    private TextField tf_add;

    @FXML
    private Button bt_reset2;

    @FXML
    private Button bt_okAdd;

   private final ProTypeService proTypeService = new ProTypeService();
   private final SimpleUtil simpleUtil = new SimpleUtil();

    /**
     * 初始化 调用ProductService中的方法，让数据显示在TableView表格。
     */
   public void initialize() throws DataLoadException {
       //将数据添加到表格控件中
       proTypeService.setProTypeTableViewData(tv_productType,proTypeService.getProTypeTableViewData(),tc_productType);
       //自动添加序号列
       proTypeService.autoSetNum(tc_number,text_tip);
       //向操作列添加“修改”按钮、“删除”按钮
       proTypeService.autoSetCol1(tc_modify, tc_productType,tv_productType);
       proTypeService.autoSetCol2(tc_delete, tv_productType);
   }

    @FXML
    void bt_addEvent(ActionEvent event) {
        tf_add.setVisible(true);
        bt_okAdd.setVisible(true);
    }

    @FXML
    void bt_okAddEvent(ActionEvent event) throws IOException, DataLoadException {
        proTypeService.isOkAdd(tf_add,tv_productType);
    }

    @FXML
    void bt_reset2Event(ActionEvent event) {
        tf_add.setText("");
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
     * "查询"按钮的监听器事件
     */
    @FXML
    void bt_okSearchEvent(ActionEvent event) throws DataLoadException {
       String result = proTypeService.isSearch(tf_search.getText());
       if (result != null) {
           simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "查询结果", result);
       }
    }

    /**
     * "重置"按钮的监听器事件
     */
    @FXML
    void bt_resetEvent(ActionEvent event) {
         tf_search.setText("");
    }

}
