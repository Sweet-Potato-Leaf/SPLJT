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
@ApiModel(value = "Minio配置")
public class MinioConfig {

    @ApiModelProperty(value = "URL，域名，IPv4地址或IPv6地址")
    private String endpoint;

    @ApiModelProperty(value = "URL，内网地址，方便上传")
    private String localEndpoint;

    @ApiModelProperty(value = "是否初始化")
    private boolean initialization=false;

    @ApiModelProperty(value = "公钥")
    private String accessKey;

    @ApiModelProperty(value = "私钥")
    private String secretKey;

    @ApiModelProperty(value = "空间名称")
    private String bucketName;

}
