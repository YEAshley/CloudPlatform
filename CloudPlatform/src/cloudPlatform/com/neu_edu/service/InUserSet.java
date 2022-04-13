package cloudPlatform.com.neu_edu.service;

import cloudPlatform.com.neu_edu.beans.property.UserTableData;
import cloudPlatform.com.neu_edu.exception.DataLoadException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * UserService的接口，继承于InAutoSet
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public interface InUserSet extends InAutoSet{

    void autoSetNum(TableColumn<UserTableData, String> tc_number, Text text_tip);

    void autoSetCol1(TableColumn<UserTableData, String> tc_modify,TableColumn<UserTableData, String> tc_name,TableColumn<UserTableData, String> tc_phone,
                     TableView<UserTableData> tv_user);

    void autoSetCol2(TableColumn<UserTableData, String> tc_delete,TableView<UserTableData> tv_user);

    void delete(UserTableData utd, TableView<UserTableData> tv_user) throws IOException, DataLoadException;

    void modify(UserTableData utd) throws IOException, DataLoadException;
}
