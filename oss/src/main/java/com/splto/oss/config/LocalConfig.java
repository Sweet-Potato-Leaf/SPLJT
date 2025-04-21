package com.splto.oss.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ConfigurationProperties(prefix = "com.splto.file-storage.local-config")
@ApiModel(description = "本地上传配置")
public class LocalConfig {

    @ApiModelProperty(value = "上传文件路径")
    private String uploadPath;

    @ApiModelProperty(value = "访问地址")
    private String address;

}
