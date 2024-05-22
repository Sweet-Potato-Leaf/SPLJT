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
@Tag(name = "本地上传配置")
public class LocalConfig {

    @Schema(title = "上传文件路径")
    private String uploadPath;

    @Schema(title = "访问地址")
    private String address;

}
