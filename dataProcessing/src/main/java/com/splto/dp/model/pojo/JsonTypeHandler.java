package com.splto.dp.model.pojo;

import com.splto.utils.JsonUtil;
import org.springframework.util.StringUtils;

public class JsonTypeHandler<T> extends JsonTypeHandlerAbstract<T>{

    public JsonTypeHandler(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public T toObject(String content, Class<T> clazz) {
        return StringUtils.hasLength(content) ? JsonUtil.fromJson(content, clazz) : null;
    }

}
