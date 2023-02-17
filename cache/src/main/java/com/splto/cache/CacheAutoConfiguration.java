package com.splto.cache;

import com.splto.cache.config.CacheConfig;
import com.splto.cache.service.CacheService;
import com.splto.cache.service.impl.RedisCacheInterfaceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CacheConfig.class)
public class CacheAutoConfiguration {


    @Bean
    CacheService cacheService(){
        return new CacheService();
    }

    @Bean
    @ConditionalOnProperty(name = "com.splto.cache.type",havingValue = "redis")
    RedisCacheInterfaceImpl redisCacheInterfaceImpl(){
        return new RedisCacheInterfaceImpl();
    }

}
