package cloudPlatform.com.neu_edu.service;

import cloudPlatform.com.neu_edu.beans.property.DeviceTypeTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * DevTypeService的接口，继承于InAutoSet
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public interface InDevTypeSet extends InAutoSet {

    void autoSetNum(TableColumn<DeviceTypeTableData, String> tc_number, Text text_tip);

    void autoSetCol1(TableColumn<DeviceTypeTableData, String> tc_modify, TableColumn<DeviceTypeTableData, String> tc_deviceType,
                     TableView<DeviceTypeTableData> tv_deviceType);

    void autoSetCol2(TableColumn<DeviceTypeTableData, String> tc_delete, TableView<DeviceTypeTableData> tv_deviceType);

    void delete(DeviceTypeTableData dtd, TableView<DeviceTypeTableData> tv_deviceType) throws IOException, DataLoadException;

    void modify(DeviceTypeTableData dtd2) throws IOException, DataLoadException;
}
