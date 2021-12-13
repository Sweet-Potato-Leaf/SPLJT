package com.longpengz.dataprocessing.bean.pojo;

import com.github.wenhao.jpa.PredicateBuilder;
import com.github.wenhao.jpa.Specifications;
import com.longpengz.restful.bean.APIError;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.*;

public class SpecificationUtil {


    public static <T> PredicateBuilder<T> filter(SeachForm seachForm){
        PredicateBuilder<T> spec = Specifications.and();
        if(!ObjectUtils.isEmpty(seachForm)){
            if(StringUtils.hasLength(seachForm.getTimeStrs())){
                try {
                    parseTimesList(seachForm.getTimeStrs()).forEach(item -> {
                        spec.between(item.getName(),new Timestamp(item.getStart()),new Timestamp(item.getEnd()));
                    });
                }catch (Exception e){
                    APIError.e(400,"时间格式错误！正确格式为：name,start,end;name,start,end");
                }
            }
            if(StringUtils.hasLength(seachForm.getFieldStrs())){
                try {
                    parseFiltersList(seachForm.getFieldStrs()).forEach(item->{
                        if(!item.isBlurry()){
                            spec.eq(item.getName(),item.getContent());
                        }else {
                            spec.like(item.getName(),"%"+item.getContent()+"%");
                        }
                    });
                }catch (Exception e){
                    APIError.e(400,"字段格式错误！正确格式为：name,content,blurry;");
                }
            }
        }
        return spec;
    }

    public static <T> PredicateBuilder<T>  exist(PredicateBuilder<T> spec){
        spec.eq("presenceStatus",1);
        return spec;
    }


    /**
     * 解析时间查询字符串
     * @param times  时间
     * @return 时间筛选列表
     */
    public static List<TimeFilter> parseTimesList(String times){
        String[] timeArr = times.split(";");
        return new ArrayList<>(){{
            for(String time:timeArr){
                add(parseTimesFilterItem(time));
            }
        }};
    }
    public static TimeFilter parseTimesFilterItem(String time){
        String[] split = time.split(",");
        return TimeFilter.builder().name(split[0]).start(Long.parseLong(split[1])).end(Long.parseLong(split[2])).build();
    }


    /**
     * 解析字段查询字符串
     * @author longpengZ
     * @return 字段筛选列表
     */
    public static List<FieldFilter> parseFiltersList(String filters){
        String[] filterArr = filters.split(";");
        return new ArrayList<>(){{
            for(String time:filterArr){
                add(parseFieldFilterItem(time));
            }
        }};
    }
    public static Map<String,FieldFilter> parseFiltersMap(String filters){
        String[] filterArr = filters.split(";");
        return new HashMap<>(){{
            for(String time:filterArr){
                FieldFilter fieldFilter = parseFieldFilterItem(time);
                put(fieldFilter.getName(), fieldFilter);
            }
        }};
    }
    public static FieldFilter parseFieldFilterItem(String time){
        String[] split = time.split(",");
        return FieldFilter.builder().name(split[0]).content(split[1]).blurry("true".equals(split[2])).build();
    }

    /**
     * 设置筛选类信息
     * @author longpengZ
     * @param tClass 筛选类信息
     * @param filters 字段筛选字符串格式
     */
    public static <T> T fieldFilterOfObj(Class<T> tClass, String filters) {
        T t = null;
        try {
            t = tClass.getDeclaredConstructor().newInstance();
            if(StringUtils.hasLength(filters)){
                fieldFilterOfObj(tClass, parseFiltersMap(filters));
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            APIError.e("类初始化失败");
        }
        return t;
    }


    /**
     * 设置筛选类信息
     * @author longpengZ
     * @param t 筛选类实例
     * @param fieldFilterMap 字段筛选Map
     */
    public static <T> void fieldFilterOfObj(T t, Map<String,FieldFilter> fieldFilterMap) throws IllegalAccessException {
        for (Field field : t.getClass().getFields()) {
            FieldFilter fieldFilter = fieldFilterMap.get(field.getName());
            if(ObjectUtils.isEmpty(fieldFilter)
                    && StringUtils.hasLength(fieldFilter.getName())
                    && fieldFilter.getName().equals(field.getName())){
                field.set(t, fieldFilter.getContent());
            }
        }
    }


}
