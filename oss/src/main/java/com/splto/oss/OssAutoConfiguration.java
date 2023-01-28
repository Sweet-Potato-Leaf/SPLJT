package com.splto.oss;

import com.splto.oss.config.FileStorageConfig;
import com.splto.oss.service.FileStorageService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({FileStorageConfig.class})
public class OssAutoConfiguration {

    @Bean
    public FileStorageService fileStorageService(FileStorageConfig fileStorageConfig){
        return new FileStorageService(fileStorageConfig);
    }

}
