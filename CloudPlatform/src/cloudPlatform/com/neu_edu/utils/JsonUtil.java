package cloudPlatform.com.neu_edu.utils;

import com.google.gson.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 件json字符串和对象转换的工具类
 *
 * @author 侯心怡
 * @class 1916
 * @StudentID 20195782
 * @date 2020-07-23
 */
public class JsonUtil {

    /**
     * 将object对象转换成json格式字符串
     */
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

    /**
     * 将json格式字符串对象转换成object（针对文件中一行是一个对象的情况）
     */
    public static Object toObject(String fd, Class<?> C) {
        JsonParser parser = new JsonParser();
        //将JSON的String转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(fd).getAsJsonArray();
        Gson gson = new Gson();
        Object object = null;
        for (JsonElement item : jsonArray) {
            object = gson.fromJson(item, C);
        }
        return object;
    }

   /* *//**
     * 将json格式字符串对象转成object集合（针对文件中一行是多个对象的情况）
     *//*
    public static List<Object> toObjList(String fd, Class<?> C) {
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(fd).getAsJsonArray();
        Gson gson = new Gson();
        ArrayList<Object> objList = new ArrayList<>();
        for (JsonElement item : jsonArray) {
            Object object = gson.fromJson(item, C);
            objList.add(object);
        }
        return objList;

    }*/
}
