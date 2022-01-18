package com.longpengz.oss.config;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "本地上传配置")
@ConfigurationProperties(prefix = "oss.local.config")
public class LocalConfig {

    @ApiModelProperty(value = "上传文件路径")
    private String uploadPath;

    @ApiModelProperty(value = "访问地址")
    private String address;

}
