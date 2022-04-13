package cloudPlatform.com.neu_edu.service.impl;

import cloudPlatform.com.neu_edu.beans.CloudFactoryAdmin;
import cloudPlatform.com.neu_edu.beans.Franchiser;
import cloudPlatform.com.neu_edu.beans.User;
import cloudPlatform.com.neu_edu.beans.property.UserTableData;
import cloudPlatform.com.neu_edu.service.InUserSet;
import cloudPlatform.com.neu_edu.utils.FileUtil;
import cloudPlatform.com.neu_edu.utils.JsonUtil;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * UserService，作为登录、注册、用户管理三大功能的后端功能实现类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class UserService implements InUserSet {
    private final SimpleUtil simpleUtil = new SimpleUtil();

    public List<User> getUserList() {
        //创建List<User>集合
        List<User> userList = new ArrayList<>();
        List<Object> users = FileUtil.getData("user.data", User.class);
        for (Object user : users) {
            User u = (User) user;
            if (u != null) {
                userList.add(u);
            }
        }
        return userList;
    }

    /**
     * 将数据显示在用户信息管理表格中
     *
     *
     */
    public void setUserTableViewData(TableView<UserTableData> tableView,
                                     ObservableList<UserTableData> data,
                                     TableColumn<UserTableData, String> tc_id,
                                     TableColumn<UserTableData, String> tc_name,
                                     TableColumn<UserTableData, String> tc_phone,
                                     TableColumn<UserTableData, String> tc_actor) {
        //设置各列数据
        tc_id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        tc_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tc_phone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        tc_actor.setCellValueFactory(cellData -> cellData.getValue().actorProperty());
        //将数据添加到表格控件中
        tableView.setItems(data);

    }

    public ObservableList<UserTableData> getUserTableViewData() {
        ObservableList<UserTableData> dataList = FXCollections.observableArrayList();
        for (User u : getUserList()) {
            UserTableData utd = new UserTableData(u.getId(), u.getName(), u.getPhone(), u.getActor());
            //将UserTableData对象添加到dataList中
            dataList.add(utd);
        }
        return dataList;
    }

    /**
     * 自动添加序号列
     */
    @Override
    public void autoSetNum(TableColumn<UserTableData, String> tc_number, Text text_tip) {
        tc_number.setCellFactory((col) -> {
            TableCell<UserTableData, String> cell = new TableCell<UserTableData, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });
    }

    /**
     * 自动添加操作列--修改、删除
     */
    @Override
    public void autoSetCol1(TableColumn<UserTableData, String> tc_modify, TableColumn<UserTableData, String> tc_name, TableColumn<UserTableData, String> tc_phone,
                            TableView<UserTableData> tv_user) {

        tc_modify.setCellFactory((col) -> {
            TableCell<UserTableData, String> cell = new TableCell<UserTableData, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Button bt_modify = new Button("修改");
                        this.setGraphic(bt_modify);

                        bt_modify.setOnMouseClicked((me) -> {
                            try {
                                if (me.getClickCount() == 1) {
                                    tv_user.setEditable(true);
                                    tc_name.setCellFactory(TextFieldTableCell.forTableColumn());
                                    tc_phone.setCellFactory(TextFieldTableCell.forTableColumn());
                                } else {
                                    UserTableData newU = this.getTableView().getItems().get(this.getIndex());
                                    modify(newU);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            };
            return cell;
        });
    }

    @Override
    public void autoSetCol2(TableColumn<UserTableData, String> tc_delete, TableView<UserTableData> tv_user) {

        tc_delete.setCellFactory((col) -> {
            TableCell<UserTableData, String> cell = new TableCell<UserTableData, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        Button bt_delete = new Button("删除");
                        this.setGraphic(bt_delete);
                        bt_delete.setOnMouseClicked((me) -> {
                            UserTableData utd = this.getTableView().getItems().get(this.getIndex());
                            try {
                                delete(utd, tv_user);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            };
            return cell;
        });
    }


    /**
     * 删除操作
     */
    @Override
    public void delete(UserTableData utd, TableView<UserTableData> tv_user) throws IOException {
        boolean is = simpleUtil.informationDialog(Alert.AlertType.CONFIRMATION, "提示", "请确认", "是否删除！");
        if (is) {
            getUserTableViewData().remove(utd);
            List<User> list = getUserList();
            Iterator<User> iterator = list.iterator();
            while (iterator.hasNext()) {
                User u = iterator.next();
                if (u.getId().equals(utd.getId())) {
                    iterator.remove();
                    saveFormat(list);
                    tv_user.setItems(null);
                    tv_user.setItems(getUserTableViewData());
                    simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "用户信息", "已成功删除！");
                }
            }
        }
    }

    /**
     * 修改
     */
    public void modify(UserTableData utd) throws IOException {
        List<User> list = getUserList();
        for (User user : list) {
            if (user.getId().equals(utd.getId())) {
                user.setName(utd.getName());
                user.setPhone(utd.getPhone());
                saveFormat(list);
                simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "已修改成功！");
            }
        }
    }

    /**
     * 查询
     */
    public String isSearch(String name){
        List<User> list = getUserList();
        boolean is = false;
        String result = null;
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getName().equals(name)) {
                result = "序号：" + (i + 1) + "\n账号：" + user.getId() + "\n姓名：" + user.getName() + "\n联系方式：" + user.getPhone() + "\n用户类型：" + user.getActor();
                is = true;
            }
        }
        if (!is) {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "未查询到此用户，请检查输入信息是否正确！");
            result = null;
        }
        return result;
    }

    /**
     * 将变动后数据以json格式保存至文件
     */
    private void saveFormat(List<User> userList) throws IOException {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            String data = JsonUtil.toJson(user);
            data = data.replace("\n", "");
            data = data.replace(" ", "");
            FileUtil.writeData(data, "user.data", i != 0);
        }
    }

    /**
     * 登录
     */
    public boolean isLogin(TextInputControl tf_id, TextInputControl pf_password) {
        SimpleUtil simpleUtil = new SimpleUtil();
        boolean isOK = false;

        if (!simpleUtil.isEmpty(tf_id.getText())) {
            if (!simpleUtil.isEmpty(pf_password.getText())) {
                boolean is1 = false;
                boolean is2 = false;
                for (User u : getUserList()) {
                    if (u.getId().equals(tf_id.getText())) {
                        is1 = true;
                        if (u.getPassword().equals(pf_password.getText())) {
                            simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "用户登录成功！");
                            isOK = true;
                            is2 = true;
                        }
                    }
                }
                if (!is1) simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "该用户账号不存在！");
                if (!is2) simpleUtil.informationDialog(Alert.AlertType.ERROR, "提示", "警告", "密码错误！");
            } else {
                simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "密码不能为空！");
                isOK = false;
            }
        } else {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "用户账号不能为空！");
            isOK = false;
        }
        return isOK;
    }

    public String actor(String id)  {
        String actor;
        if (id.startsWith("c")) {
            actor = "云工厂";
        } else if (id.startsWith("f")) {
            actor = "经销商";
        } else {
            actor = "超级管理员";
        }
        System.out.println("actor " + actor);
        return actor;
    }

    public String getFactory(String id) throws IOException {
        FactoryService factoryService = new FactoryService();
        String factory = null;
        List<CloudFactoryAdmin> list = factoryService.getCFList();
        for (CloudFactoryAdmin c : list) {
            if (c.getId().equals(id)) {
                factory = c.getFactoryName();
            }
        }
        return factory;
    }

    /**
     * 新建/注册
     */
    public boolean isAdd(TextInputControl tf_id, TextInputControl pf_password, TextInputControl tf_name,
                         TextInputControl tf_phone, RadioButton bt_factoryName, ToggleGroup group, TextInputControl tf_factoryName,
                         TextInputControl tf_factoryProfile) throws IOException {
        boolean isOK = false;

        if (!simpleUtil.isEmpty(tf_id.getText())) {
            if (!simpleUtil.isEmpty(pf_password.getText())) {
                if (!simpleUtil.isEmpty(tf_name.getText())) {
                    if (!simpleUtil.isEmpty(tf_phone.getText())) {
                        if (group.getSelectedToggle() != null) {
                            if (bt_factoryName.isSelected()) {
                                if (!simpleUtil.isEmpty(tf_factoryName.getText())) {
                                    if (!simpleUtil.isEmpty(tf_factoryProfile.getText())) {
                                        CloudFactoryAdmin cfa = new CloudFactoryAdmin(tf_id.getText(), tf_name.getText(), tf_phone.getText(),
                                                "云工厂", pf_password.getText(), tf_factoryName.getText(), tf_factoryProfile.getText(), "正常");
                                        isCloudFactory(tf_id.getText(), cfa);
                                        isOK = isOk(tf_id.getText(), cfa);
                                    } else {
                                        simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "工厂简介不能为空！");
                                    }
                                } else {
                                    simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "工厂名称不能为空！");
                                }
                            } else {
                                Franchiser f = new Franchiser(tf_id.getText(), tf_name.getText(), tf_phone.getText(), "经销商", pf_password.getText());
                                isOK = isOk(tf_id.getText(), f);
                            }
                        } else {
                            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请选择用户类型！");
                        }
                    } else {
                        simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "联系方式不能为空！");
                        isOK = false;
                    }
                } else {
                    simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "真实姓名不能为空！");
                    isOK = false;
                }
            } else {
                simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "用户密码不能为空！");
                isOK = false;
            }
        } else {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "用户账号不能为空！");
            isOK = false;
        }
        return isOK;
    }

    /**
     * 判断输入账号是否已存在
     */
    public boolean isOk(String id, User ur) throws IOException {
        boolean isOkAdd = true;
        for (User u : getUserList()) {
            if (u.getId().equals(id)) {
                simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "该用户账号已存在！");
                isOkAdd = false;
                break;
            }
        }
        if (isOkAdd) {
            saveUserInfo(ur);
            simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "操作成功！");
        }
        return isOkAdd;
    }

    public void isCloudFactory(String id, User ur) throws IOException {
        boolean isOkAdd = true;
        for (User u : getUserList()) {
            if (u.getId().equals(id)) {
                simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "该用户账号已存在！");
                isOkAdd = false;
                break;
            }
        }
        if (isOkAdd) {
            String data = JsonUtil.toJson(ur);
            data = data.replace("\n", "");
            data = data.replace(" ", "");
            FileUtil.writeData(data, "cloudFactory.data", true);
        }
    }

    /**
     * 将新增数据保存至文件
     */
    public void saveUserInfo(User ur) throws IOException {
        String data = JsonUtil.toJson(ur);
        data = data.replace("\n", "");
        data = data.replace(" ", "");
        FileUtil.writeData(data, "user.data", true);
    }

    @Override
    public void autoSetNum() {

    }

    @Override
    public void autoSetCol1() {

    }

    @Override
    public void autoSetCol2() {

    }

}
