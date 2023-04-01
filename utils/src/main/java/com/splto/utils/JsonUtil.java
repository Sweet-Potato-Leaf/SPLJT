package com.splto.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {

    private static final Gson gson = new Gson();

    public static <T> String toJson(T obj) {
        return gson.toJson(obj);
    }

    public static <T> T formJson(String json, TypeToken<T> type) {
        return gson.fromJson(json, type);
    }

}
