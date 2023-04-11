package com.splto.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtil {

    public static Map<String, Field> getObjFieldMap(Object obj) {
        return getObjFieldMap(obj.getClass());
    }

    public static Map<String, Field> getObjFieldMap(Class<?> clz) {
        return new HashMap<>(){{
            for (Field field : clz.getDeclaredFields()) {
                field.setAccessible(true);
                put(field.getName(), field);
            }
        }};
    }
}
