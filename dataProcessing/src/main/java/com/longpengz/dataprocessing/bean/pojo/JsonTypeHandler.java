package com.longpengz.dataprocessing.bean.pojo;

import org.springframework.util.StringUtils;

public class JsonTypeHandler<T> extends JsonTypeHandlerAbstract<T>{

    public JsonTypeHandler(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public T toObject(String content, Class<T> clazz) {
        if(StringUtils.hasLength(content)){
            return  gson.fromJson(content, clazz);
        }else {
            return null;
        }
    }

}
