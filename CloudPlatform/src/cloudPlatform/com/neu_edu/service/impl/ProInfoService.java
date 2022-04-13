package cloudPlatform.com.neu_edu.service.impl;

import cloudPlatform.com.neu_edu.beans.ProductInfo;
import cloudPlatform.com.neu_edu.beans.ProductType;
import cloudPlatform.com.neu_edu.beans.property.ProInfoTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.InProInfoSet;
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
 * ProInfoService，作为产品信息管理的的功能实现类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class ProInfoService implements InProInfoSet {
    private final SimpleUtil simpleUtil = new SimpleUtil();
    private final ProTypeService proTypeService = new ProTypeService();

    /**
     * 获取产品信息的List集合
     */
    public List<ProductInfo> getProInfoList() throws DataLoadException {
        //创建List<ProductInfo>集合
        List<ProductInfo> proInfoList = new ArrayList<>();
        List<Object> pis = FileUtil.getData("productInfo.data", ProductInfo.class);
        for (Object o : pis) {
            ProductInfo pi = (ProductInfo) o;
            if (pi != null) {
                proInfoList.add(pi);
            }
        }
        return proInfoList;
    }

    /**
     * 让数据显示在产品信息的表格中
     */
    public void setProInfoTableViewData(TableView<ProInfoTableData> tableView, ObservableList<ProInfoTableData> dataList,
                                        TableColumn<ProInfoTableData, String> tc_proCode, TableColumn<ProInfoTableData, String> tc_proName,
                                        TableColumn<ProInfoTableData, String> tc_proType, TableColumn<ProInfoTableData, String> tc_proSize,
                                        TableColumn<ProInfoTableData, String> tc_proDescription) {

        tc_proCode.setCellValueFactory(cellData -> cellData.getValue().productCodeProperty());
        tc_proName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        tc_proType.setCellValueFactory(cellData -> cellData.getValue().productTypeProperty());
        tc_proSize.setCellValueFactory(cellData -> cellData.getValue().productSizeProperty());
        tc_proDescription.setCellValueFactory(cellData -> cellData.getValue().productDescriptionProperty());

        tableView.setItems(dataList);
    }

    public ObservableList<ProInfoTableData> getProInfoTableViewData() throws DataLoadException {
        ObservableList<ProInfoTableData> piList = FXCollections.observableArrayList();
        for (ProductInfo pi : getProInfoList()) {
            ProInfoTableData piTD = new ProInfoTableData(pi.getProductCode(), pi.getProductName(), pi.getProductType(), pi.getProductSize(),
                    pi.getProductDescription());
            //将ProInfoTableData对象添加到piList中
            piList.add(piTD);
        }
        return piList;
    }

    /**
     * 自动添加序号列
     */
    @Override
    public void autoSetNum(TableColumn<ProInfoTableData, String> tc_number, Text text_tip) {
        tc_number.setCellFactory((col) -> {
            TableCell<ProInfoTableData, String> cell = new TableCell<ProInfoTableData, String>() {
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

    /**
     * 自动添加操作列
     */
    @Override
    public void autoSetCol1(TableColumn<ProInfoTableData, String> tc_modify, TableColumn<ProInfoTableData, String> tc_proCode,
                            TableColumn<ProInfoTableData, String> tc_proType, TableColumn<ProInfoTableData, String> tc_proSize,
                            TableColumn<ProInfoTableData, String> tc_proDescription, TableView<ProInfoTableData> tv_productInfo) {
        tc_modify.setCellFactory((col) -> {
            TableCell<ProInfoTableData, String> cell = new TableCell<ProInfoTableData, String>() {
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
                                    tv_productInfo.setEditable(true);
                                    tc_proCode.setCellFactory(TextFieldTableCell.forTableColumn());
                                    tc_proType.setCellFactory(TextFieldTableCell.forTableColumn());
                                    tc_proSize.setCellFactory(TextFieldTableCell.forTableColumn());
                                    tc_proDescription.setCellFactory(TextFieldTableCell.forTableColumn());
                                } else {
                                    ProInfoTableData piTD2 = this.getTableView().getItems().get(this.getIndex());
                                    modify(piTD2);
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

    @Override
    public void autoSetCol2(TableColumn<ProInfoTableData, String> tc_delete, TableView<ProInfoTableData> tv_productInfo) {
        tc_delete.setCellFactory((col) -> {
            TableCell<ProInfoTableData, String> cell = new TableCell<ProInfoTableData, String>() {
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
                            ProInfoTableData piTD = this.getTableView().getItems().get(this.getIndex());
                            try {
                                delete(piTD, tv_productInfo);
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


    /**
     * 删除
     */
    @Override
    public void delete(ProInfoTableData piTD, TableView<ProInfoTableData> tv_productInfo) throws IOException, DataLoadException {
        boolean is = simpleUtil.informationDialog(Alert.AlertType.CONFIRMATION, "提示", "请确认", "是否删除！");
        if (is) {
            getProInfoTableViewData().remove(piTD);
            List<ProductInfo> list = getProInfoList();
            Iterator<ProductInfo> iterator = list.iterator();
            while (iterator.hasNext()) {
                ProductInfo pi = iterator.next();
                if (pi.getProductName().equals(piTD.getProductName())) {
                    iterator.remove();
                    saveFormat(list);
                    tv_productInfo.setItems(null);
                    tv_productInfo.setItems(getProInfoTableViewData());
                    simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "产品信息", "已成功删除！");
                }
            }
        }
    }

    /**
     * 修改，产品的规格不可修改
     */
    @Override
    public void modify(ProInfoTableData piID) throws IOException, DataLoadException {
        List<ProductInfo> list = getProInfoList();
        for (ProductInfo pi : list) {
            if (pi.getProductName().equals(piID.getProductName())) {
                pi.setProductCode(piID.getProductCode());
                pi.setProductDescription(piID.getProductDescription());
                pi.setProductSize(piID.getProductSize());
                pi.setProductType(piID.getProductType());
                saveFormat(list);
                simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "已修改成功！");
            }
        }

    }

    /**
     * 将变动后数据以json格式保存至文件
     */
    private void saveFormat(List<ProductInfo> piList) throws IOException {

        for (int i = 0; i < piList.size(); i++) {
            ProductInfo pi = piList.get(i);
            String data = JsonUtil.toJson(pi);
            data = data.replace("\n", "");
            data = data.replace(" ", "");

            FileUtil.writeData(data, "productInfo.data", i != 0);
        }
    }

    /**
     * 查询
     */
    public String isSearch(String productName) throws DataLoadException {
        List<ProductInfo> list = getProInfoList();
        boolean is = false;
        String result = null;
        for (int i = 0; i < list.size(); i++) {
            ProductInfo pi = list.get(i);
            if (pi.getProductName().equals(productName)) {
                result = "序号：" + (i + 1) + "\n产品编号：" + pi.getProductCode() + "\n产品名称：" + pi.getProductName() +
                        "\n产品类别：" + pi.getProductType() + "\n产品规格：" + pi.getProductSize() + "\n产品描述：" + pi.getProductDescription();
                is = true;
            }
        }
        if (!is) {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "未查询到此产品，请检查输入信息是否正确！");
            result = null;
        }
        return result;
    }

    /**
     * Combobox赋值
     */
    public void addComboBoxItems(ComboBox<String> comboBox) {
        for (ProductType pt : proTypeService.getProTypeList()) {
            comboBox.getItems().add(pt.getProductType());
        }
    }

    /**
     * 新建
     */
    public boolean add(TextInputControl tf_proName, ComboBox<String> comboBox, TextInputControl tf_proSize,
                    TextInputControl tf_proDescription) throws DataLoadException, IOException {
        boolean isAdd = false;
        if (!simpleUtil.isEmpty(tf_proName.getText())) {
            if (!simpleUtil.isEmpty(comboBox.getSelectionModel().selectedItemProperty().getValue())) {
                if (!simpleUtil.isEmpty(tf_proSize.getText())) {
                    if (!simpleUtil.isEmpty(tf_proDescription.getText())) {
                        String proCode = "CFP2020" + (int) (Math.random() * 999999);
                        ProInfoTableData piTD = new ProInfoTableData(proCode, tf_proName.getText(), comboBox.getSelectionModel().selectedItemProperty().getValue(),
                                tf_proSize.getText(), tf_proDescription.getText());
                        getProInfoTableViewData().add(piTD);
                        ProductInfo pi = new ProductInfo(proCode, tf_proName.getText(), comboBox.getSelectionModel().selectedItemProperty().getValue(),
                                tf_proSize.getText(), tf_proDescription.getText());
                        getProInfoList().add(pi);
                        String data = JsonUtil.toJson(pi);
                        data = data.replace("\n", "");
                        data = data.replace(" ", "");
                        FileUtil.writeData(data, "productInfo.data", true);
                        simpleUtil.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "产品信息添加成功！");
                        isAdd = true;
                    } else {
                        simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "产品描述不能为空！");
                    }
                } else {
                    simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "产品规格不能为空！");
                }
            } else {
                simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "请选择产品类别！");
            }
        } else {
            simpleUtil.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "产品名称不能为空！");
        }
       return isAdd;
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
