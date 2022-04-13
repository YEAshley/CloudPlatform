package cloudPlatform.com.neu_edu.service;

import cloudPlatform.com.neu_edu.beans.property.CFactoryInfoTableData;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

/**
 * FactoryService的接口，继承于InAutoSet
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public interface InFactorySet extends InAutoSet {

    void autoSetNum(TableColumn<CFactoryInfoTableData, String> tc_number, Text text_tip);

    void autoSetCol1(TableColumn<CFactoryInfoTableData, String> tc_action, TableView<CFactoryInfoTableData> tv_CFactory);


}
