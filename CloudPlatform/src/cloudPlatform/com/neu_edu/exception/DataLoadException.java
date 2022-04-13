package cloudPlatform.com.neu_edu.exception;

/**
 * 自定义数据加载异常
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class DataLoadException extends Exception{

    public DataLoadException(){}

    public DataLoadException(String message) {
        super(message);
    }
}
