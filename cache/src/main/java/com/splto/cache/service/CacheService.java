package com.splto.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存服务
 * @author longpengZ
 */
@Service
public class CacheService implements CacheInterface {

    private RedisCacheInterfaceImpl redisCacheInterfaceImpl;

    @Autowired(required = false)
    public void setRedisCacheInterfaceImpl(RedisCacheInterfaceImpl redisCacheInterfaceImpl) {
        this.redisCacheInterfaceImpl = redisCacheInterfaceImpl;
    }

    @Override
    public void saveObject(String key, Object object, Integer timeout, TimeUnit timeUnit) {
        cacheInterface().saveObject(key,object,timeout,timeUnit);
    }

    @Override
    public void saveObject(String key, Object object) {
        cacheInterface().saveObject(key,object);
    }

    @Override
    public <T> T getObject(String key) {
        return cacheInterface().getObject(key);
    }

    @Override
    public void deleteObject(String key) {
        cacheInterface().deleteObject(key);
    }

    private CacheInterface  cacheInterface(){
        if (redisCacheInterfaceImpl != null){
            return redisCacheInterfaceImpl;
        }
        throw new RuntimeException("缓存配置错误");
    }
}
