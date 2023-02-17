package com.splto.cache.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class CachePrefixKeyAbstract implements CachePrefixKey {

    protected final static String DELIMITER = ":";

    /**
     * key 前缀
     */
    private String prefix;

    /**
     * key 原始值
     */
    private String value;


    @Override
    public String getPrefixKey() {
        return prefix;
    }

    @Override
    public String getKey() {
        return getPrefixKey() + DELIMITER + value;
    }



}
