package cloudPlatform.com.neu_edu.service.impl;

import cloudPlatform.com.neu_edu.beans.ProductType;
import cloudPlatform.com.neu_edu.beans.property.ProTypeTableData;
import cloudPlatform.com.neu_edu.service.InProTypeSet;
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
 * ProTypeService，作为产品类别管理的的功能实现类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class ProTypeService implements InProTypeSet {
    private final SimpleUtil simpleUtil = new SimpleUtil();

    /**
     * 获取产品类别的List集合
     */
    public List<ProductType> getProTypeList() {
        //创建List<ProductType>集合
        List<ProductType> proTypeList = new ArrayList<>();
        List<Object> pts = FileUtil.getData("productType.data", ProductType.class);
        for (Object o : pts) {
            ProductType pt = (ProductType) o;
            if (pt != null) {
                proTypeList.add(pt);
            }
        }
        return proTypeList;
    }

    /**
     * 让数据显示在产品类别管理的表格中
     */
    public void setProTypeTableViewData(TableView<ProTypeTableData> tableView, ObservableList<ProTypeTableData> data, TableColumn<ProTypeTableData, String> tc_proType) {

        //设置产品类别名称列数据
        tc_proType.setCellValueFactory(cellData -> cellData.getValue().productTypeProperty());
        //将数据添加到表格控件中
        tableView.setItems(data);
    }

    public ObservableList<ProTypeTableData> getProTypeTableViewData() {
        ObservableList<ProTypeTableData> ptList = FXCollections.observableArrayList();
        for (ProductType pt : getProTypeList()) {
            ProTypeTableData ptTD = new ProTypeTableData(pt.getProductType());
            //将ProTypeTableData对象添加到ptList中
            ptList.add(ptTD);
        }
        //返回数据
        return ptList;
    }

    /**
     * 自动添加序号列
     */
    @Override
    public void autoSetNum(TableColumn<ProTypeTableData, String> tc_number, Text text_tip) {
        tc_number.setCellFactory((col) -> {
            TableCell<ProTypeTableData, String> cell = new TableCell<ProTypeTableData, String>() {
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
    public void autoSetCol1(TableColumn<ProTypeTableData, String> tc_modify, TableColumn<ProTypeTableData, String> tc_productType,
                            TableView<ProTypeTableData> tv_productType) {
        tc_modify.setCellFactory((col) -> {
            TableCell<ProTypeTableData, String> cell = new TableCell<ProTypeTableData, String>() {

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
                                ProTypeTableData ptTD1 = this.getTableView().getItems().get(this.getIndex());
                                update(ptTD1);
                                if (me.getClickCount() == 1) {
                                    tv_productType.setEditable(true);
                                    tc_productType.setCellFactory(TextFieldTableCell.forTableColumn());
                                } else {
                                    ProTypeTableData ptTD2 = this.getTableView().getItems().get(this.getIndex());
                                    modify(ptTD2);
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

    public void autoSetCol2(TableColumn<ProTypeTableData, String> tc_delete, TableView<ProTypeTableData> tv_productType) {
        tc_delete.setCellFactory((col) -> {
            TableCell<ProTypeTableData, String> cell = new TableCell<ProTypeTableData, String>() {

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
                            ProTypeTableData ptTD = this.getTableView().getItems().get(this.getIndex());
                            try {
                                delete(ptTD, tv_productType);
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
     * 删除
     */
    @Override
    public void delete(ProTypeTableData ptTD, TableView<ProTypeTableData> tv_productType) throws IOException {
        boolean is = simpleUtil.informationDialog(Alert.AlertType.CONFIRMATION, "提示", "请确认", "是否删除！");
        if (is) {
            List<ProductType> list = getProTypeList();
            Iterator<ProductType> iterator = list.iterator();
            while (iterator.hasNext()) {
                ProductType pt = iterator.next();
                if (pt.getProductType().equals(ptTD.getProductType())) {
                    iterator.remove();
                    saveFormat(list);
                    tv_productType.setItems(null);
                    tv_productType.setItems(getProTypeTableViewData());
                    simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "已成功删除！");
                }
            }
        }
    }

    public void update(ProTypeTableData ptTD1) throws IOException {
        List<ProductType> list = getProTypeList();
        ProductType oldP = new ProductType(ptTD1.getProductType());
        System.out.println("old " + oldP.toString());
        int index = 0;
        boolean is = false;
        for (ProductType p : list) {
            if (p.getProductType().equals(ptTD1.getProductType())) {
                index = list.indexOf(p);
                System.out.println("index " + index);
                is = true;
            }
        }
        if (is) {
            list.remove(list.get(index));
        }
        saveFormat(list);
    }

    //@Override
    public void modify(ProTypeTableData ptTD2) throws IOException {
        List<ProductType> list = getProTypeList();
        ProductType newP = new ProductType(ptTD2.getProductType());
        list.add(newP);
        saveFormat(list);
        simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "已修改成功！");
    }

    /**
     * 将变动后数据以json格式保存至文件
     */
    private void saveFormat(List<ProductType> ptList) throws IOException {
        for (int i = 0; i < ptList.size(); i++) {
            ProductType pt = ptList.get(i);
            String data = JsonUtil.toJson(pt);
            data = data.replace("\n", "");
            data = data.replace(" ", "");
            FileUtil.writeData(data, "productType.data", i != 0);
        }
    }

    /**
     * 添加
     */
    public void isOkAdd(TextInputControl tf_add, TableView<ProTypeTableData> tv_productType) throws IOException {
        boolean isOkAdd = true;
        List<ProductType> list = getProTypeList();
        if (!simpleUtil.isEmpty(tf_add.getText())) {
            for (ProductType pt : list) {
                if (pt.getProductType().equals(tf_add.getText())) {
                    simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "该产品类别已存在！");
                    isOkAdd = false;
                }
            }
            if (isOkAdd) {
                ProductType p = new ProductType(tf_add.getText());
                list.add(p);
                ProTypeTableData ptTD = new ProTypeTableData(tf_add.getText());
                getProTypeTableViewData().add(ptTD);
                String data = JsonUtil.toJson(p);
                data = data.replace("\n", "");
                data = data.replace(" ", "");
                FileUtil.writeData(data, "productType.data", true);
                tv_productType.setItems(null);
                tv_productType.setItems(getProTypeTableViewData());
                simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "新建产品类别成功！");
            }
        } else {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "新建产品类别名称不能为空！");
        }
    }

    /**
     * 查询
     */
    public String isSearch(String productType) {
        List<ProductType> list = getProTypeList();
        String result = null;
        boolean is = false;
        for (int i = 0; i < list.size(); i++) {
            ProductType pt = list.get(i);
            if (pt.getProductType().equals(productType)) {
                result = "序号：" + (i + 1) + " 类别名称：" + productType;
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

    public void autoSetCol2() {

    }

}
