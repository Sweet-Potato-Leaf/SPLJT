package com.longpengz.cache.service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存接口
 * @author longpengZ
 */
public interface CacheInterface {

    /**
     * 缓存对象，有过期时间
     * @author longpengZ
     * @param key key值
     * @param object 缓存对象
     * @param timeout 过期时间
     * @param timeUnit 时间类型 时分秒
     */
    void saveObject(String key, Object object, Integer timeout, TimeUnit timeUnit);

    /**
     * 缓存对象，无过期时间
     * @author longpengZ
     * @param key key值
     * @param object 缓存对象
     */
    void saveObject(String key, Object object);

    /**
     * 根据key值返回缓存对象
     * @author longpengZ
     * @param key key值
     * @param <T> 缓存对象泛型
     */
    <T> T getObject(String key);

    /**
     * 根据key值删除缓存对象
     * @author longpengZ
     * @param key key值
     */
    void  deleteObject(String key);

}
