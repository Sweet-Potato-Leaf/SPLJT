package com.longpengz.restful.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Data
public class API<T> {

    private Integer code;
    private T data;
    private String msg;

    @JsonIgnore
    private Class<?> beanClazz;


    public static BiFunction<Object, Class<?>, Map<String, Object>> metaInfoProcessor = null;


    private Map<String, Object> metaInfo = new HashMap<>();


    public static <T> API<T> ok(T t) {
        return ok(t, null);
    }

    public static <T> API<T> ok(T t, Class<?> clazz) {
        API<T> data = new API<T>();
        data.setData(t);
        data.setCode(200);
        data.setMsg("success");
        data.setBeanClazz(clazz);
        if (metaInfoProcessor != null) {
            try {
                data.setMetaInfo(metaInfoProcessor.apply(data.getData(), data.getBeanClazz()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static API<Object> e(Integer code, String msg) {
        API<Object> api = new API<>();
        api.setMsg(msg);
        api.setCode(code);
        return api;
    }

    public static API<Object> ok(Integer code, String msg, String data) {
        API<Object> api = new API<>();
        api.setMsg(msg);
        api.setCode(code);
        api.setData(data);
        return api;
    }

    public static API<Object> e(String msg) {
        return e(400, msg);
    }

    public static API ok() {
        return ok(204, "success", "success");
    }
}
