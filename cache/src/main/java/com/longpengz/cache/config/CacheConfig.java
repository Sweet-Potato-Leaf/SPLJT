package com.longpengz.cache.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.longpengz.cache")
public class CacheConfig {

    @ApiModelProperty("缓存类型 redis")
    private String type;

}
