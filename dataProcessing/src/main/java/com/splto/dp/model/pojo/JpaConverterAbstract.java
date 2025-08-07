package com.splto.dp.model.pojo;

import com.splto.utils.JsonUtil;
import jakarta.persistence.AttributeConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Type;

public abstract class JpaConverterAbstract<T> implements AttributeConverter<T, String>, Serializable {
    @Override
    public String convertToDatabaseColumn(Object meta) {
        return ObjectUtils.isEmpty(meta) ? null: JsonUtil.toJson(meta);
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        return StringUtils.hasLength(dbData) ? JsonUtil.fromJson(dbData, getType()):null;
    }

    public abstract Type getType();



}
