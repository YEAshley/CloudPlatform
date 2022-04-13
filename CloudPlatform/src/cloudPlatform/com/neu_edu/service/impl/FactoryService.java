package cloudPlatform.com.neu_edu.service.impl;

import cloudPlatform.com.neu_edu.beans.DeviceInfo;
import cloudPlatform.com.neu_edu.beans.property.CFactoryInfoTableData;
import cloudPlatform.com.neu_edu.beans.CloudFactoryAdmin;
import cloudPlatform.com.neu_edu.beans.property.DeviceInfoTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.InFactorySet;
import cloudPlatform.com.neu_edu.utils.FileUtil;
import cloudPlatform.com.neu_edu.utils.JsonUtil;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 作为工厂管理后端功能实现类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class FactoryService implements InFactorySet {
    private final SimpleUtil simpleUtil = new SimpleUtil();
    private final UserService userService = new UserService();


    /**
     * 获取云工厂信息集合
     */
    public List<CloudFactoryAdmin> getCFList() throws IOException {
        List<CloudFactoryAdmin> cFList = new ArrayList<>();
        List<Object> objects = FileUtil.getData("cloudFactory.data", CloudFactoryAdmin.class);
        for (Object o : objects) {
            CloudFactoryAdmin cf = (CloudFactoryAdmin) o;
            if (cf != null) {
                cFList.add(cf);
            }
        }
        return cFList;
    }

    /**
     * 将数据显示在云工厂信息列表中
     */
    public void setCFactoryInfoTableData(TableView<CFactoryInfoTableData> tableView, ObservableList<CFactoryInfoTableData> dataList, TableColumn<CFactoryInfoTableData, String> tc_factoryName,
                                         TableColumn<CFactoryInfoTableData, String> tc_factoryProfile, TableColumn<CFactoryInfoTableData, String> tc_name,
                                         TableColumn<CFactoryInfoTableData, String> tc_id, TableColumn<CFactoryInfoTableData, String> tc_phone,
                                         TableColumn<CFactoryInfoTableData, String> tc_status) {
        //设置各列数据
        tc_factoryName.setCellValueFactory(cellData -> cellData.getValue().factoryNameProperty());
        tc_factoryProfile.setCellValueFactory(cellData -> cellData.getValue().factoryProfileProperty());
        tc_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tc_id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        tc_phone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        tc_status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tableView.setItems(dataList);
    }

    /**
     * 得到ObservableList<CFactoryInfoTableData>集合
     */
    public ObservableList<CFactoryInfoTableData> getCloudFactoryInfoTableData() throws IOException {
        //创建ObservableList<CFactoryInfoTableData>对象
        ObservableList<CFactoryInfoTableData> dataList = FXCollections.observableArrayList();

        for (CloudFactoryAdmin cfa : getCFList()) {
            CFactoryInfoTableData cf = new CFactoryInfoTableData(cfa.getFactoryName(), cfa.getFactoryProfile(),
                    cfa.getName(), cfa.getId(), cfa.getPhone(), cfa.getStatus());
            dataList.add(cf);
        }
        return dataList;
    }

    /**
     * 设置序号自动生成列
     */
    @Override
    public void autoSetNum(TableColumn<CFactoryInfoTableData, String> tc_number, Text text_tip) {
        tc_number.setCellFactory((col) -> {
            TableCell<CFactoryInfoTableData, String> cell = new TableCell<CFactoryInfoTableData, String>() {
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
     * 设置状态列和操作列
     */
    @Override
    public void autoSetCol1(TableColumn<CFactoryInfoTableData, String> tc_action, TableView<CFactoryInfoTableData> tv_CFactory) {
        tc_action.setCellFactory((col) -> {
            TableCell<CFactoryInfoTableData, String> cell = new TableCell<CFactoryInfoTableData, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        // ImageView delICON = new ImageView(getClass().getResource("delete.png").toString());
                        ToggleButton tbt_action = new ToggleButton("开/关");
                        this.setGraphic(tbt_action);
                        tbt_action.setOnMouseClicked((me) -> {
                            try {
                                CFactoryInfoTableData cf1 = this.getTableView().getItems().get(this.getIndex());
                                if (tbt_action.isSelected()) {
                                    tbt_action.setText("开启");
                                } else {
                                    tbt_action.setText("关停");
                                   // CFactoryInfoTableData cf2 = this.getTableView().getItems().get(this.getIndex());
                                }
                                action(cf1, tv_CFactory);
                            } catch (IOException | DataLoadException e) {
                                e.printStackTrace();
                            }

                        });
                    }
                }
            };
            return cell;
        });
    }

    public void action(CFactoryInfoTableData cf, TableView<CFactoryInfoTableData> tv_CFactory) throws IOException, DataLoadException {
        List<CloudFactoryAdmin> list = getCFList();
        for (CloudFactoryAdmin c : list) {
            if (c.getFactoryName().equals(cf.getFactoryName())) {
                if (c.getStatus().equals("正常")) {
                    c.setStatus("关停");
                } else {
                    c.setStatus("正常");
                }
                saveFormat(list);
                tv_CFactory.setItems(null);
                tv_CFactory.setItems(getCloudFactoryInfoTableData());
               // simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "工厂状态", "调整完毕！");
            }
        }

    }

    /**
     * 将变动后数据以json格式保存至文件
     */
    private void saveFormat(List<CloudFactoryAdmin> cfList) throws IOException {

        for (int i = 0; i < cfList.size(); i++) {
            CloudFactoryAdmin cf = cfList.get(i);
            String data = JsonUtil.toJson(cf);
            data = data.replace("\n", "");
            data = data.replace(" ", "");
            if (i == 0) {
                FileUtil.writeData(data, "cloudFactory.data", false);
            } else {
                FileUtil.writeData(data, "cloudFactory.data", true);
            }
        }
    }

    /**
     * 查询
     */
    public String isSearch(String factoryName) throws DataLoadException, IOException {
        List<CloudFactoryAdmin> list = getCFList();
        boolean is = false;
        String result = null;
        for (int i = 0; i < list.size(); i++) {
            CloudFactoryAdmin c = list.get(i);
            if (c.getFactoryName().equals(factoryName)) {
                result = "序号：" + (i + 1) + "\n工厂名称：" + c.getFactoryName() + "\n工厂简介：" + c.getFactoryProfile() + "\n负责人：" + c.getName() +
                        "\n联系方式：" + c.getPhone() + "\n登录账号：" + c.getId() + "\n工厂状态：" + c.getStatus();
                is = true;
            }
        }
        if (!is) {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "未查询到此云工厂的相关信息，请检查输入是否正确！");
            result = null;
        }
        return result;
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
