package com.splto.dp.model.pojo;

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
@Tag(name = "搜索筛选参数")
public class FieldFilter {
    
    @Schema(title = "字段")
    private String name;
    
    @Schema(title = "查询内容")
    private Object content;

    @Schema(title = "是否模糊查询")
    private boolean blurry;

}
