package com.splto.cache.model.pojo;

import java.util.concurrent.TimeUnit;

public interface CachePrefixKey {

    /**
     * 获取 key 前缀
     */
    String getPrefixKey();

    /**
     * 获取完整 key 值
     */
    String getKey();

    /**
     * 获取过期时间
     */
    Integer getTimeout();

    /**
     * 获取时间类型 时分秒
     */
    TimeUnit getTimeUnit();
}
