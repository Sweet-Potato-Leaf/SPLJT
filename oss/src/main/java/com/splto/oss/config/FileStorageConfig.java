package com.splto.oss.config;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "com.splto.file-storage")
@Tag(name = "文件存储仓库配置")
public class FileStorageConfig {

    @Schema(title = "类型")
    private String type;

    private LocalConfig localConfig;

    private MinioConfig minioConfig;
}
