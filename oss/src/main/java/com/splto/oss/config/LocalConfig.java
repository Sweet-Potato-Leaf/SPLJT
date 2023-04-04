package com.splto.oss.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "本地上传配置")
public class LocalConfig {

    @ApiModelProperty(value = "上传文件路径")
    private String uploadPath;

    @ApiModelProperty(value = "访问地址")
    private String address;

}
