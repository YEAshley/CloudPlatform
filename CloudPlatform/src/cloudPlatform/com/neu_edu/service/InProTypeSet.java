package cloudPlatform.com.neu_edu.service;

import cloudPlatform.com.neu_edu.beans.property.ProTypeTableData;
import cloudPlatform.com.neu_edu.beans.property.UserTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.IOException;


/**
 * ProTypeService的接口，继承于InAutoSet
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public interface InProTypeSet extends InAutoSet {

    void autoSetNum(TableColumn<ProTypeTableData, String> tc_number, Text text_tip);

    void autoSetCol1(TableColumn<ProTypeTableData, String> tc_modify, TableColumn<ProTypeTableData, String> tc_productType,
                     TableView<ProTypeTableData> tv_productType);

    void autoSetCol2(TableColumn<ProTypeTableData, String> tc_delete, TableView<ProTypeTableData> tv_productType);

    void delete(ProTypeTableData ptTD, TableView<ProTypeTableData> tv_productType) throws IOException, DataLoadException;

}
