package cloudPlatform.com.neu_edu.service;

import cloudPlatform.com.neu_edu.beans.property.ProInfoTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import java.io.IOException;

/**
 * ProInfoService的接口，继承于InAutoSet
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public interface InProInfoSet extends InAutoSet{

    void autoSetNum(TableColumn<ProInfoTableData, String> tc_number, Text text_tip);

    void autoSetCol1(TableColumn<ProInfoTableData, String> tc_modify,TableColumn<ProInfoTableData, String> tc_proCode,
                     TableColumn<ProInfoTableData, String>  tc_proType,TableColumn<ProInfoTableData, String>  tc_proSize,
                     TableColumn<ProInfoTableData, String>  tc_proDescription, TableView<ProInfoTableData> tv_productInfo);

    void autoSetCol2(TableColumn<ProInfoTableData, String> tc_delete,TableView<ProInfoTableData> tv_productInfo);

    void delete(ProInfoTableData piTD,TableView<ProInfoTableData> tv_productInfo) throws IOException, DataLoadException;

    void modify(ProInfoTableData piID)throws IOException, DataLoadException;

}
