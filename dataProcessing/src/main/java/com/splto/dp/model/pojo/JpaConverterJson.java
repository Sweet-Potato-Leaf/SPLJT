package com.splto.dp.model.pojo;

import com.google.gson.reflect.TypeToken;
import com.splto.utils.JsonUtil;

import java.lang.reflect.Type;

public class JpaConverterJson<T> extends JpaConverterAbstract<T> {


    @Override
    public Type getType() {
        return new TypeToken<T>(){}.getType();
    }

}
