package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.ProInfoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 添加产品信息界面控制类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class AddProductInfoFrameController {
    private final ProInfoService proInfoService = new ProInfoService();

    @FXML
    private Stage addProInfoStage;

    public Stage getAddProInfoStage() {
        return addProInfoStage;
    }

    public void setAddProInfoStage(Stage addProInfoStage) {
        this.addProInfoStage = addProInfoStage;
    }

    @FXML
    private TextField tf_proSize;

    @FXML
    private Button bt_ok;

    @FXML
    private TextField tf_proName;

    @FXML
    private TextField tf_proDescription;

    @FXML
    private ComboBox<String> cb_proType;

    @FXML
    private Button bt_reset;

    public void initialize() throws DataLoadException {
        proInfoService.addComboBoxItems(cb_proType);
    }

    @FXML
    void bt_okEvent(ActionEvent event) throws IOException, DataLoadException {
         boolean isAdd = proInfoService.add(tf_proName,cb_proType,tf_proSize,tf_proDescription);

         if (isAdd) {
             addProInfoStage.close();
         }
    }

    @FXML
    void bt_resetEvent(ActionEvent event) {
       tf_proName.setText("");
       tf_proSize.setText("");
       tf_proDescription.setText("");
    }

}
