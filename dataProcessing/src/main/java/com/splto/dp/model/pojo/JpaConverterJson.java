package com.splto.dp.model.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class JpaConverterJson<T> extends JpaConverterAbstract<T> {


    @Override
    public Type getType() {
        return new TypeToken<T>(){}.getType();
    }
}
