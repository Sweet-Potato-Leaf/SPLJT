package com.splto.dataprocessing.model.pojo;

import org.springframework.util.StringUtils;

import java.util.List;

public class SqlUtil {

    /**
     * 列表转换in SQL语句
     * 转换个格式为 in (1,2,...)
     * @author longpengZ
     * @param list 需要转换的列表不能为空且要大于1
     */
    public static String listIntOfInSql(List<Integer> list){
        StringBuffer stringBuffer = new StringBuffer();
        list.forEach(it -> {
            if(StringUtils.hasLength(stringBuffer.toString())){
                stringBuffer.append(",");
            }
            stringBuffer.append(it);
        });
        stringBuffer.append(") ");
        return " in (" + stringBuffer;
    }

    /**
     * 列表转换in SQL语句
     * 转换个格式为 in ('1','2',...)
     * @author longpengZ
     * @param list 需要转换的列表不能为空且要大于1
     */
    public static String listStrOfInSql(List<String> list){
        StringBuffer stringBuffer = new StringBuffer();
        list.forEach(it -> {
            if(StringUtils.hasLength(stringBuffer.toString())){
                stringBuffer.append(",");
            }
            stringBuffer.append("'").append(it).append("'");
        });
        stringBuffer.append(") ");
        return " in (" + stringBuffer;
    }
}
