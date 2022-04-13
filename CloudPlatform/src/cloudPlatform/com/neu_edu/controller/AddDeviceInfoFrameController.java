package cloudPlatform.com.neu_edu.controller;

import cloudPlatform.com.neu_edu.exception.DataLoadException;
import cloudPlatform.com.neu_edu.service.impl.DevInfoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 添加设备信息界面控制类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class AddDeviceInfoFrameController {

    @FXML
    private Stage addDevInfoStage;

    public Stage getAddDevInfoStage() {
        return addDevInfoStage;
    }

    public void setAddDevInfoStage(Stage addDevInfoStage) {
        this.addDevInfoStage = addDevInfoStage;
    }

    @FXML
    private TextField tf_devSize;

    @FXML
    private Button bt_ok;

    @FXML
    private TextField tf_devName;

    @FXML
    private ComboBox<String> cb_devType;

    @FXML
    private Button bt_reset;

    @FXML
    private TextField tf_factory;

    @FXML
    private TextField tf_devDescription;

    private DevInfoService devInfoService = new DevInfoService();

    public void initialize() throws DataLoadException {
        devInfoService.addComboBoxItems(cb_devType);
    }

    @FXML
    void bt_okEvent(ActionEvent event) throws IOException {
        boolean isAdd = devInfoService.add(tf_devName,cb_devType,tf_devSize,tf_devDescription,tf_factory);

        if(isAdd) {
            addDevInfoStage.close();
        }
    }

    @FXML
    void bt_resetEvent(ActionEvent event) {
           tf_devName.setText("");
           tf_devSize.setText("");
           tf_devDescription.setText("");
           tf_factory.setText("");
    }

}
