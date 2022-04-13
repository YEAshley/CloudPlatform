package cloudPlatform.com.neu_edu.service;

import cloudPlatform.com.neu_edu.beans.property.DeviceInfoTableData;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

/**
 * DevInfoService的接口，继承于InAutoSet
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public interface InDevInfoSet extends InAutoSet {

    void autoSetCol1(TableColumn<DeviceInfoTableData, String> tc_remote, TableView<DeviceInfoTableData> tv_devInfo);

    void autoSetCol2(TableColumn<DeviceInfoTableData, String> tc_modify, TableColumn<DeviceInfoTableData, String> tc_devCode,
                     TableColumn<DeviceInfoTableData, String> tc_devSize, TableColumn<DeviceInfoTableData, String> tc_devDescription,
                     TableView<DeviceInfoTableData> tv_devInfo);

    void autoSetCol3(TableColumn<DeviceInfoTableData, String> tc_delete, TableView<DeviceInfoTableData> tv_devInfo);

    void delete(DeviceInfoTableData diTD, TableView<DeviceInfoTableData> tv_devInfo) throws IOException;

    void modify(DeviceInfoTableData diTD) throws IOException;


}
