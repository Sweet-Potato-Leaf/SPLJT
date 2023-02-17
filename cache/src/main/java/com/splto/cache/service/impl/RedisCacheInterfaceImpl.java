package com.splto.cache.service.impl;

import com.splto.cache.model.pojo.CachePrefixKey;
import com.splto.cache.service.CacheInterface;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheInterfaceImpl implements CacheInterface {

    @Getter
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<Object,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setKeySerializer(new RedisSerializer<String>() {
            @Override
            public byte[] serialize(String s) throws SerializationException {
                return s.getBytes(StandardCharsets.UTF_8);
            }
            @Override
            public String deserialize(byte[] bytes) throws SerializationException {
                return new String(bytes,StandardCharsets.UTF_8);
            }
        });
    }

    @Override
    public void saveObject(String key, Object object, Integer timeout, TimeUnit timeUnit) {
        ValueOperations<Object, Object> operation = redisTemplate.opsForValue();
        if (timeout == null){
            operation.set(key,object);
            return;
        }
        operation.set(key, object, timeout, timeUnit);
    }

    @Override
    public void saveObject(String key, Object object) {
        saveObject(key,object,null,null);
    }

    @Override
    public void saveObject(CachePrefixKey key, Object object) {
        saveObject(key.getKey(), object, key.getTimeout(), key.getTimeUnit());
    }

    @Override
    public <T> T getObject(String key) {
        ValueOperations<Object,Object> operation = redisTemplate.opsForValue();
        return (T) operation.get(key);
    }

    @Override
    public void deleteObject(String key) {
        redisTemplate.delete(key);
    }
}
