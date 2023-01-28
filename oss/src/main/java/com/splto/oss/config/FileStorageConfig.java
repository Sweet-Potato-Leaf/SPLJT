package com.splto.oss.config;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "com.longpengz.file-storage")
@ApiModel(description = "文件存储仓库配置")
public class FileStorageConfig {

    @ApiModelProperty(value = "类型")
    private String type;

    private LocalConfig localConfig;

    private MinioConfig minioConfig;
}
