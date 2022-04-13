package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.MainApp;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import cloudPlatform.com.neu_edu.beans.property.UserTableData;
import cloudPlatform.com.neu_edu.service.impl.UserService;

/**
 * 用户管理界面类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class UserManageFrameController {

    @FXML
    private TableView<UserTableData> tv_user;

    @FXML
    private TableColumn<UserTableData, String> tc_action;

    @FXML
    private TableColumn<UserTableData, String> tc_delete;

    @FXML
    private TableColumn<UserTableData, String> tc_modify;
    @FXML
    private Button bt_search;

    @FXML
    private Button bt_reset;

    @FXML
    private Button bt_ok;

    @FXML
    private TextField tf_search;

    @FXML
    private TableColumn<UserTableData, String> tc_number;

    @FXML
    private TableColumn<UserTableData, String> tc_phone;

    @FXML
    private TableColumn<UserTableData, String> tc_actor;

    @FXML
    private TableColumn<UserTableData, String> tc_id;

    @FXML
    private TableColumn<UserTableData, String> tc_name;

    @FXML
    private Text text_tip;

    @FXML
    private Button bt_add;

    @FXML
    private Button bt_refresh;

    private final UserService userService = new UserService();
    private final SimpleUtil simpleUtil = new SimpleUtil();

    /**
     * 初始化 调用UserService中的方法，让数据显示在TableView表格。
     */
    @FXML
    private void initialize() throws DataLoadException {

        //将数据添加到表格控件中
        userService.setUserTableViewData(tv_user, userService.getUserTableViewData(), tc_id, tc_name, tc_phone, tc_actor);

        //自动添加序号列
       // tc_number.setCellFactory(new SetNumUtil<>());
        userService.autoSetNum(tc_number, text_tip);
        //向操作列添加“修改”按钮、“删除”按钮
        userService.autoSetCol1(tc_modify,tc_name,tc_phone,tv_user);
        userService.autoSetCol2(tc_delete,tv_user);

    }



    /**
     * 新建
     */
    @FXML
    void bt_addEvent(ActionEvent event) {
        new MainApp().initAddUserFrame();
    }

    /**
     * 检索功能
     */
    @FXML
    void bt_searchEvent(ActionEvent event) {
        tf_search.setVisible(true);
        bt_ok.setVisible(true);
        bt_reset.setVisible(true);
    }

    /**
     * “立即查询”按钮的监听器事件
     */
    @FXML
    void bt_okEvent(ActionEvent event) throws DataLoadException {
        String result = userService.isSearch(tf_search.getText());
        if (result != null) {
            simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "查询结果", result);
           // tv_user.getSelectionModel().select();
        }
    }

    @FXML
    void bt_resetEvent(ActionEvent event) {
          tf_search.setText("");
    }

    @FXML
    void bt_refreshEvent(ActionEvent event) throws DataLoadException {
          tv_user.setItems(null);
          tv_user.setItems(userService.getUserTableViewData());
    }
}
