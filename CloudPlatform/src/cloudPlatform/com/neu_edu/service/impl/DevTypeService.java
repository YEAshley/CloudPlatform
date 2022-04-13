package cloudPlatform.com.neu_edu.service.impl;

import cloudPlatform.com.neu_edu.beans.DeviceType;
import cloudPlatform.com.neu_edu.beans.property.DeviceTypeTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.InDevTypeSet;
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
 * 作为设备类型管理后端功能实现类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class DevTypeService implements InDevTypeSet {
    private final SimpleUtil simpleUtil = new SimpleUtil();

    /**
     * 获取产品类别的List集合
     */
    public List<DeviceType> getDevTypeList() throws DataLoadException {
        //创建List<ProductType>集合
        List<DeviceType> devTypeList = new ArrayList<>();
        List<Object> pts = FileUtil.getData("deviceType.data", DeviceType.class);
        for (Object o : pts) {
            DeviceType pt = (DeviceType) o;
            if (pt != null) {
                devTypeList.add(pt);
            }
        }
        return devTypeList;
    }

    public void setDevTypeTableViewData(TableView<DeviceTypeTableData> tableView, ObservableList<DeviceTypeTableData> data, TableColumn<DeviceTypeTableData, String> tc_devType) {

        //设置产品类别名称列数据
        tc_devType.setCellValueFactory(cellData -> cellData.getValue().deviceTypeProperty());
        //将数据添加到表格控件中
        tableView.setItems(data);
    }

    public ObservableList<DeviceTypeTableData> getDevTypeTableViewData() throws DataLoadException {
        ObservableList<DeviceTypeTableData> deList = FXCollections.observableArrayList();
        for (DeviceType d : getDevTypeList()) {
            DeviceTypeTableData dtTD = new DeviceTypeTableData(d.getDeviceType());
            deList.add(dtTD);
        }
        //返回数据
        return deList;
    }

    /**
     * 自动添加序号列
     */

    @Override
    public void autoSetNum(TableColumn<DeviceTypeTableData, String> tc_number, Text text_tip) {
        tc_number.setCellFactory((col) -> {
            TableCell<DeviceTypeTableData, String> cell = new TableCell<DeviceTypeTableData, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }

//                    else {
//                        //当前页面数据数量提示
//                        text_tip.setText("显示第1到第" + (this.getIndex() - 1) + "条记录,总共" + (this.getIndex() - 1) + "条记录");
//                    }
                }
            };
            return cell;
        });
    }

    @Override
    public void autoSetCol1(TableColumn<DeviceTypeTableData, String> tc_modify, TableColumn<DeviceTypeTableData, String> tc_deviceType,
                            TableView<DeviceTypeTableData> tv_deviceType) {
        tc_modify.setCellFactory((col) -> {
            TableCell<DeviceTypeTableData, String> cell = new TableCell<DeviceTypeTableData, String>() {

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
                            try {
                                if (me.getClickCount() == 1) {
                                    DeviceTypeTableData tdt1 = this.getTableView().getItems().get(this.getIndex());
                                    update(tdt1);
                                    tv_deviceType.setEditable(true);
                                    tc_deviceType.setCellFactory(TextFieldTableCell.forTableColumn());
                                } else {
                                    DeviceTypeTableData tdt2 = this.getTableView().getItems().get(this.getIndex());
                                     modify(tdt2);

                                }
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

    public void update(DeviceTypeTableData dt) throws DataLoadException, IOException {
        List<DeviceType> list = getDevTypeList();
        //DeviceType oldD = new DeviceType(dt.getDeviceType());
        int index = 0;
        boolean is = false;
        for (DeviceType d: list ){
            if (d.getDeviceType().equals(dt.getDeviceType())) {
                index = list.indexOf(d);
                is = true;
            }
        }
        if (is){
            list.remove(list.get(index));

        }
        saveFormat(list);
    }

    @Override
    public void modify(DeviceTypeTableData dtd2) throws IOException, DataLoadException {
        List<DeviceType> list = getDevTypeList();
        DeviceType newD = new DeviceType(dtd2.getDeviceType());
        list.add(newD);
        saveFormat(list);
        simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "已修改成功！");
    }

    @Override
    public void autoSetCol2(TableColumn<DeviceTypeTableData, String> tc_delete, TableView<DeviceTypeTableData> tv_deviceType) {
        tc_delete.setCellFactory((col) -> {
            TableCell<DeviceTypeTableData, String> cell = new TableCell<DeviceTypeTableData, String>() {

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
                            DeviceTypeTableData dt = this.getTableView().getItems().get(this.getIndex());
                            try {
                                delete(dt, tv_deviceType);
                            } catch (DataLoadException | IOException e) {
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
    public void delete(DeviceTypeTableData dtd, TableView<DeviceTypeTableData> tv_deviceType) throws IOException, DataLoadException {
        boolean is = simpleUtil.informationDialog(Alert.AlertType.CONFIRMATION, "提示", "请确认", "是否删除！");
        if (is) {
            List<DeviceType> list = getDevTypeList();
            Iterator<DeviceType> iterator = list.iterator();
            while (iterator.hasNext()) {
                DeviceType dt = iterator.next();
                if (dt.getDeviceType().equals(dtd.getDeviceType())) {
                    iterator.remove();
                    saveFormat(list);
                    tv_deviceType.setItems(null);
                    tv_deviceType.setItems(getDevTypeTableViewData());
                    simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "已成功删除！");
                }
            }
        }
    }

    /**
     * 将变动后数据以json格式保存至文件
     */
    private void saveFormat(List<DeviceType> dtList) throws IOException {
        for (int i = 0; i < dtList.size(); i++) {
            DeviceType dt = dtList.get(i);
            String data = JsonUtil.toJson(dt);
            data = data.replace("\n", "");
            data = data.replace(" ", "");
            if (i == 0) {
                FileUtil.writeData(data, "deviceType.data", false);
            } else {
                FileUtil.writeData(data, "deviceType.data", true);
            }
        }
    }

    /**
     * 添加
     */
    public void isOkAdd(TextInputControl tf_add, TableView<DeviceTypeTableData> tv_deviceType) throws DataLoadException, IOException {
        boolean isOkAdd = true;
        List<DeviceType> list = getDevTypeList();
        if (!simpleUtil.isEmpty(tf_add.getText())) {
            for (DeviceType dt : list) {
                if (dt.getDeviceType().equals(tf_add.getText())) {
                    simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "该设备类型已存在！");
                    isOkAdd = false;
                }
            }
            if (isOkAdd) {
                DeviceType d = new DeviceType(tf_add.getText());
                list.add(d);
                DeviceTypeTableData dt = new DeviceTypeTableData(tf_add.getText());
                getDevTypeTableViewData().add(dt);
                String data = JsonUtil.toJson(d);
                data = data.replace("\n", "");
                data = data.replace(" ", "");
                FileUtil.writeData(data, "deviceType.data", true);
                tv_deviceType.setItems(null);
                tv_deviceType.setItems(getDevTypeTableViewData());
                simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "新建设备类型成功！");
            }
        } else {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "新建设备类型名称不能为空！");
        }
    }
    /**
     * 查询
     */
    public String isSearch(String deviceType) throws DataLoadException {
        List<DeviceType> list = getDevTypeList();
        String result = null;
        boolean is = false;
        for (int i = 0; i < list.size(); i++) {
            DeviceType d = list.get(i);
            if (d.getDeviceType().equals(deviceType)) {
                result = "序号：" + (i + 1) + "\n设备类型：" + deviceType;
                is = true;
            }
        }
        if (!is) {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "未查询到此类别名称，请检查输入信息是否正确！");
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
