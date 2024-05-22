package com.splto.oss.config;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Minio配置")
public class MinioConfig {

    @Schema(title = "URL，域名，IPv4地址或IPv6地址")
    private String endpoint;

    @Schema(title = "URL，内网地址，方便上传")
    private String localEndpoint;

    @Builder.Default
    @Schema(title = "是否初始化")
    private boolean initialization=false;

    @Schema(title = "公钥")
    private String accessKey;

    @Schema(title = "私钥")
    private String secretKey;

    @Schema(title = "空间名称")
    private String bucketName;

}
