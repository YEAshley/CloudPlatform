package cloudPlatform.com.neu_edu.service.impl;

import cloudPlatform.com.neu_edu.MainApp;
import cloudPlatform.com.neu_edu.beans.DeviceInfo;
import cloudPlatform.com.neu_edu.beans.ProductInfo;
import cloudPlatform.com.neu_edu.beans.property.DeviceInfoTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.utils.FileUtil;
import cloudPlatform.com.neu_edu.utils.JsonUtil;
import cloudPlatform.com.neu_edu.utils.SimpleUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 作为云工厂管理后端功能实现类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class MyDeviceService {

    private final SimpleUtil simpleUtil = new SimpleUtil();
    private final DevInfoService devInfoService = new DevInfoService();
    private final DevTypeService devTypeService = new DevTypeService();
    private final ProInfoService proInfoService = new ProInfoService();
    private final String factory = FileUtils.readFileToString(
            new File("C:\\Users\\Ashley\\IdeaProjects\\JavaFX\\CloudPlatform\\data\\myFactory.txt"),"UTF-8");

    public MyDeviceService() throws IOException {
    }

    public List<DeviceInfo> getMyDevInfos(String factory) {
        System.out.println(factory);
        List<DeviceInfo> deviceInfos = new ArrayList<>();
        List<Object> dis = FileUtil.getData("deviceInfo.data", DeviceInfo.class);
        for (Object o : dis) {
            DeviceInfo di = (DeviceInfo) o;
            if (di != null && di.getFactory().equals(factory)) {
                deviceInfos.add(di);
            }
        }
       return deviceInfos;
    }

    public void setMyDevTableViewData(TableView<DeviceInfoTableData> tableView, ObservableList<DeviceInfoTableData> dataList,
                                        TableColumn<DeviceInfoTableData, String> tc_devCode, TableColumn<DeviceInfoTableData, String> tc_devName,
                                        TableColumn<DeviceInfoTableData, String> tc_devType, TableColumn<DeviceInfoTableData, String> tc_devSize,
                                        TableColumn<DeviceInfoTableData, String> tc_devDescription, TableColumn<DeviceInfoTableData, String> tc_devStatus,
                                        TableColumn<DeviceInfoTableData, String> tc_leaseStatus, TableColumn<DeviceInfoTableData, String> tc_factory) {


        tc_devCode.setCellValueFactory(cellData -> cellData.getValue().deviceCodeProperty());
        tc_devName.setCellValueFactory(cellData -> cellData.getValue().deviceNameProperty());
        tc_devType.setCellValueFactory(cellData -> cellData.getValue().deviceTypeProperty());
        tc_devSize.setCellValueFactory(cellData -> cellData.getValue().deviceSizeProperty());
        tc_devDescription.setCellValueFactory(cellData -> cellData.getValue().deviceDescriptionProperty());
        tc_devStatus.setCellValueFactory(cellData -> cellData.getValue().deviceStatusProperty());
        tc_leaseStatus.setCellValueFactory(cellData -> cellData.getValue().deviceSourceProperty());
        tc_factory.setCellValueFactory(cellData -> cellData.getValue().factoryProperty());

        tableView.setItems(dataList);
    }

    public ObservableList<DeviceInfoTableData> getMyDevTableViewData() {
        ObservableList<DeviceInfoTableData> diList = FXCollections.observableArrayList();
        for (DeviceInfo di : getMyDevInfos(factory)) {
            DeviceInfoTableData diTD = new DeviceInfoTableData(di.getDeviceCode(), di.getDeviceName(), di.getDeviceType(), di.getDeviceSize(),
                    di.getDeviceDescription(), di.getDeviceStatus(), di.getLeaseStatus(), di.getFactory(),di.getDeviceSource());
            diList.add(diTD);
        }
        return diList;
    }

    public void autoSetCol1(TableColumn<DeviceInfoTableData, String> tc_remote, TableView<DeviceInfoTableData> tv_devInfo) {
        tc_remote.setCellFactory((col) -> {
            TableCell<DeviceInfoTableData, String> cell = new TableCell<DeviceInfoTableData, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        // ImageView delICON = new ImageView(getClass().getResource("delete.png").toString());
                        ToggleButton tbt_remote = new ToggleButton("远程开/关机");
                        this.setGraphic(tbt_remote);
                        tbt_remote.setOnMouseClicked((me) -> {
                            try {
                                if (tbt_remote.isSelected()) {
                                    tbt_remote.setText("远程开机");
                                    DeviceInfoTableData dtID1 = this.getTableView().getItems().get(this.getIndex());
                                    remote(dtID1, tv_devInfo);
                                } else {
                                    tbt_remote.setText("远程关机");
                                    DeviceInfoTableData dtID2 = this.getTableView().getItems().get(this.getIndex());
                                    remote(dtID2, tv_devInfo);
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

    public void remote(DeviceInfoTableData dtTD, TableView<DeviceInfoTableData> tv_devInfo) throws IOException {
        List<DeviceInfo> list = getMyDevInfos(factory);
        for (DeviceInfo di : list) {
            if (di.getDeviceName().equals(dtTD.getDeviceName())) {
                if (di.getDeviceStatus().equals("闲置中")) {
                    di.setDeviceStatus("已关闭");
                } else {
                    di.setDeviceStatus("闲置中");
                }
                saveFormat(list);
                tv_devInfo.setItems(null);
                tv_devInfo.setItems(getMyDevTableViewData());
                //simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "设备状态", "调整完毕！");
            }
        }

    }

    public void autoSetCol2(TableColumn<DeviceInfoTableData, String> tc_modify, TableColumn<DeviceInfoTableData, String> tc_devCode,
                            TableColumn<DeviceInfoTableData, String> tc_devSize, TableColumn<DeviceInfoTableData, String> tc_devDescription,
                            TableView<DeviceInfoTableData> tv_devInfo) {
        tc_modify.setCellFactory((col) -> {
            TableCell<DeviceInfoTableData, String> cell = new TableCell<DeviceInfoTableData, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        // ImageView delICON = new ImageView(getClass().getResource("delete.png").toString());
                        Button bt_modify = new Button("修改");
                        this.setGraphic(bt_modify);
                        bt_modify.setOnMouseClicked((me) -> {
                            // ProInfoTableData piTD1 = this.getTableView().getItems().get(this.getIndex());
                            try {
                                if (me.getClickCount() == 1) {
                                    tv_devInfo.setEditable(true);
                                    tc_devCode.setCellFactory(TextFieldTableCell.forTableColumn());
                                    tc_devSize.setCellFactory(TextFieldTableCell.forTableColumn());
                                    tc_devDescription.setCellFactory(TextFieldTableCell.forTableColumn());
                                } else {
                                    DeviceInfoTableData diTD = this.getTableView().getItems().get(this.getIndex());
                                    modify(diTD);
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

    public void modify(DeviceInfoTableData diTD) throws IOException {
        List<DeviceInfo> list = devInfoService.getDevInfoList();
        for (DeviceInfo di : list) {
            if (di.getDeviceName().equals(diTD.getDeviceName())) {
                di.setDeviceCode(diTD.getDeviceCode());
                di.setDeviceSize(diTD.getDeviceSize());
                di.setDeviceDescription(diTD.getDeviceDescription());
                saveFormat(list);
                simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "已修改成功！");
            }
        }
    }

    /**
     * 将变动后数据以json格式保存至文件
     */
    private void saveFormat(List<DeviceInfo> diList) throws IOException {

        for (int i = 0; i < diList.size(); i++) {
            DeviceInfo di = diList.get(i);
            String data = JsonUtil.toJson(di);
            data = data.replace("\n", "");
            data = data.replace(" ", "");

            if (i == 0) {
                FileUtil.writeData(data, "deviceInfo.data", false);
            } else {
                FileUtil.writeData(data, "deviceInfo.data", true);
            }
        }
    }

    public void autoSetCol3(TableColumn<DeviceInfoTableData, String> tc_delete, TableView<DeviceInfoTableData> tv_devInfo) {
        tc_delete.setCellFactory((col) -> {
            TableCell<DeviceInfoTableData, String> cell = new TableCell<DeviceInfoTableData, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        // ImageView delICON = new ImageView(getClass().getResource("delete.png").toString());
                        Button bt_delete = new Button("删除");
                        this.setGraphic(bt_delete);
                        bt_delete.setOnMouseClicked((me) -> {
                            DeviceInfoTableData diTD = this.getTableView().getItems().get(this.getIndex());
                            try {
                                delete(diTD, tv_devInfo);
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


    public void delete(DeviceInfoTableData diTD, TableView<DeviceInfoTableData> tv_devInfo) throws IOException {
        boolean is = simpleUtil.informationDialog(Alert.AlertType.CONFIRMATION, "提示", "请确认", "是否删除！");
        if (is) {
            getMyDevTableViewData().remove(diTD);
            List<DeviceInfo> list = devInfoService.getDevInfoList();
            Iterator<DeviceInfo> iterator = list.iterator();
            while (iterator.hasNext()) {
                DeviceInfo di = iterator.next();
                if (di.getDeviceName().equals(diTD.getDeviceName())) {
                    iterator.remove();
                    saveFormat(list);
                    tv_devInfo.setItems(null);
                    tv_devInfo.setItems(getMyDevTableViewData());
                    simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "设备信息", "已成功删除！");
                }
            }
        }

    }


    public void autoSetCol4(TableColumn<DeviceInfoTableData,String> tc_configure){
        tc_configure.setCellFactory((col) -> {
            TableCell<DeviceInfoTableData, String> cell = new TableCell<DeviceInfoTableData, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        // ImageView delICON = new ImageView(getClass().getResource("delete.png").toString());
                        Button bt_configure = new Button("配置产品");
                        this.setGraphic(bt_configure);
                        bt_configure.setOnMouseClicked((me) -> {
                            DeviceInfoTableData diTD = this.getTableView().getItems().get(this.getIndex());
                            new MainApp().initConfigureFrame(diTD);

                        });
                    }
                }
            };
          return cell;
        });

    }




    public String isSearch(String devName) throws DataLoadException {
        List<DeviceInfo> list = getMyDevInfos(factory);
        boolean is = false;
        String result = null;
        for (int i = 0; i < list.size(); i++) {
            DeviceInfo di = list.get(i);
            if (di.getDeviceName().equals(devName)) {
                result = "序号：" + (i + 1) + "\n设备编号：" + di.getDeviceCode() + "\n设备名称：" + di.getDeviceName() +
                        "\n设备类型：" + di.getDeviceType() + "\n设备规格：" + di.getDeviceSize() + "\n设备描述：" + di.getDeviceDescription()
                        + "\n设备状态：" + di.getDeviceStatus() + "\n租用状态" + di.getLeaseStatus() + "\n所属工厂" + di.getFactory();
                is = true;
            }
        }
        if (!is) {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "未查询到此设备，请检查输入信息是否正确！");
            result = null;
        }
        return result;
    }

    /**
     * Combobox赋值
     */
    public void addComboBoxItems(ComboBox<String> comboBox) throws DataLoadException {
        for (ProductInfo p : proInfoService.getProInfoList()) {
            comboBox.getItems().add(p.getProductName());
        }
    }

    public String setCode(DeviceInfoTableData diTD){
        return diTD.getDeviceCode();
    }

    public String setName(DeviceInfoTableData diTD){
        return diTD.getDeviceName();
    }
    /**
     * 新建
     */
    public boolean add(TextInputControl tf_devName, ComboBox<String> comboBox, TextInputControl tf_devSize, TextInputControl tf_devDescription,
                       TextInputControl tf_factory) throws IOException {
        boolean isAdd = false;
        if (!simpleUtil.isEmpty(tf_devName.getText())) {
            if (!simpleUtil.isEmpty(comboBox.getSelectionModel().selectedItemProperty().getValue())) {
                if (!simpleUtil.isEmpty(tf_devSize.getText())) {
                    if (!simpleUtil.isEmpty(tf_factory.getText())) {
                        String devCode = "CFP2020" + Math.random() * 99999;
                        DeviceInfoTableData diTD = new DeviceInfoTableData(devCode, tf_devName.getText(), comboBox.getSelectionModel().selectedItemProperty().getValue(),
                                tf_devSize.getText(), tf_devDescription.getText(), "闲置中", "工厂设备", tf_factory.getText(),"自有设备");
                        getMyDevTableViewData().add(diTD);
                        DeviceInfo di = new DeviceInfo(devCode, tf_devName.getText(), comboBox.getSelectionModel().selectedItemProperty().getValue(),
                                tf_devSize.getText(), tf_devDescription.getText(), "闲置中", "工厂设备",tf_factory.getText(),"自有设备");
                        devInfoService.getDevInfoList().add(di);
                        if (tf_factory.getText().equals(factory)){
                            getMyDevInfos(factory).add(di);
                        }
                        String data = JsonUtil.toJson(di);
                        data = data.replace("\n", "");
                        data = data.replace(" ", "");
                        FileUtil.writeData(data, "deviceInfo.data", true);
                        simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "设备信息添加成功！");
                        isAdd = true;
                    } else {
                        simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "所属工厂不能为空！");
                    }
                } else {
                    simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "设备规格不能为空！");
                }
            } else {
                simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请选择设备类型！");
            }
        } else {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "设备名称不能为空！");
        }
        return isAdd;
    }

    public List<DeviceInfo> getLeaseList(){
      List<DeviceInfo> list = new ArrayList<>();
      List<Object> dis = FileUtil.getData("deviceInfo.data", DeviceInfo.class);
      for (Object o :dis){
          DeviceInfo di = (DeviceInfo) o;
          if (di!= null && di.getLeaseStatus().equals("工厂设备")){
              list.add(di);
          }
      }
      return list;
    }

    public void setLeaseTableViewData(TableView<DeviceInfoTableData> tableView, ObservableList<DeviceInfoTableData> dataList,
                                      TableColumn<DeviceInfoTableData, String> tc_devCode, TableColumn<DeviceInfoTableData, String> tc_devName,
                                      TableColumn<DeviceInfoTableData, String> tc_devType, TableColumn<DeviceInfoTableData, String> tc_devSize,
                                      TableColumn<DeviceInfoTableData, String> tc_devDescription, TableColumn<DeviceInfoTableData, String> tc_devStatus) {

        tc_devCode.setCellValueFactory(cellData -> cellData.getValue().deviceCodeProperty());
        tc_devName.setCellValueFactory(cellData -> cellData.getValue().deviceNameProperty());
        tc_devType.setCellValueFactory(cellData -> cellData.getValue().deviceTypeProperty());
        tc_devSize.setCellValueFactory(cellData -> cellData.getValue().deviceSizeProperty());
        tc_devDescription.setCellValueFactory(cellData -> cellData.getValue().deviceDescriptionProperty());
        tc_devStatus.setCellValueFactory(cellData -> cellData.getValue().deviceStatusProperty());

        tableView.setItems(dataList);
    }

    public ObservableList<DeviceInfoTableData> getLeaseTableViewData() {
        ObservableList<DeviceInfoTableData> leases = FXCollections.observableArrayList();
        for (DeviceInfo di : getLeaseList()){
            DeviceInfoTableData diTD = new DeviceInfoTableData(di.getDeviceCode(),di.getDeviceName(),
                    di.getDeviceType(),di.getDeviceSize(),di.getDeviceDescription(),di.getDeviceStatus(),
                    di.getLeaseStatus(),di.getFactory(),di.getDeviceSource());
            leases.add(diTD);
        }
        return leases;
    }

    public void autoSetCol5(TableColumn<DeviceInfoTableData, String> tc_lease){
        tc_lease.setCellFactory((col) -> {
            TableCell<DeviceInfoTableData,String> cell = new TableCell<DeviceInfoTableData,String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if(!empty) {
                        Button bt_lease = new Button("租用");
                        this.setGraphic(bt_lease);
                        bt_lease.setOnMouseClicked((me) -> {
                            DeviceInfoTableData diTD = this.getTableView().getItems().get(this.getIndex());
                            try {
                                lease(diTD);
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


    public void lease(DeviceInfoTableData diTD) throws IOException {
        List<DeviceInfo> list = devInfoService.getDevInfoList();
        for (DeviceInfo di :list) {
            if (di.getDeviceName().equals(diTD.getDeviceName())){
                di.setLeaseStatus("已被占用");
                getLeaseList().add(di);
                getLeaseTableViewData().add(diTD);
                saveFormat(list);
                simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "设备", "已租用成功！");
            }
        }
    }

    public String isSearch2(String devName) throws DataLoadException {
        List<DeviceInfo> list = getLeaseList();
        boolean is = false;
        String result = null;
        for (int i = 0; i < list.size(); i++) {
            DeviceInfo di = list.get(i);
            if (di.getDeviceName().equals(devName)) {
                result = "序号：" + (i + 1) + "\n设备编号：" + di.getDeviceCode() + "\n设备名称：" + di.getDeviceName() +
                        "\n设备类型：" + di.getDeviceType() + "\n设备规格：" + di.getDeviceSize() + "\n设备描述：" + di.getDeviceDescription()
                        + "\n设备状态：" + di.getDeviceStatus() ;
                is = true;
            }
        }
        if (!is) {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "未查询到此设备，请检查输入信息是否正确！");
            result = null;
        }
        return result;
    }


}
