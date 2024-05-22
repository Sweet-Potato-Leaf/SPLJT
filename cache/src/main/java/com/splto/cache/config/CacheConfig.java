package com.splto.cache.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.splto.cache")
public class CacheConfig {

    @Schema(title = "缓存类型 redis")
    private String type;

}
