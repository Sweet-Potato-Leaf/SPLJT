package com.longpengz.dataprocessing.bean.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.io.Serializable;

public class JpaConverterJson implements AttributeConverter<Object, String> , Serializable {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object meta) {
        if(ObjectUtils.isEmpty(meta)){
            return null;
        }
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        if(!StringUtils.hasLength(dbData)){
            return null;
        }
        try {
            return objectMapper.readValue(dbData, Object.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
