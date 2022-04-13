package cloudPlatform.com.neu_edu.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读写工具类（json格式）
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class FileUtil {
    /**
     * 读取文件
     *
     * @param fileName：文件名称 C:类类型
     * @return 对象集合
     */
    public static List<Object> getData(String fileName, Class<?> C) {
        List<Object> ret = new ArrayList<>();
        String fd = "C:\\Users\\Ashley\\IdeaProjects\\JavaFX\\CloudPlatform\\data\\" + fileName;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fd));
            String line;
            while ((line = br.readLine()) != null) {
                line = "[" + line + "]";
                Object object = JsonUtil.toObject(line, C);
                ret.add(object);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 将字符串写入文件
     *
     * @param data:
     *            数据字符串
     * @param fileName:
     *            件名称
     * @param mode:
     *            模式   true:追加模式; false:覆盖模式
     * @return
     * @throws IOException
     */
    public static void writeData(String data, String fileName, boolean mode) throws IOException {
        //输出流 true == 追加模式
        String fd = "C:\\Users\\Ashley\\IdeaProjects\\JavaFX\\CloudPlatform\\data\\" + fileName;
        BufferedWriter bw = new BufferedWriter(new FileWriter(fd, mode));
        bw.write(data);
        bw.newLine();
        bw.flush();
        bw.close();
    }


}
