package com.longpengz.dataprocessing.bean.pojo;

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
@ApiModel(description = "搜索筛选参数")
public class FieldFilter {
    
    @ApiModelProperty(value = "字段")
    private String name;
    
    @ApiModelProperty(value = "查询内容")
    private Object content;

    @ApiModelProperty(value = "是否模糊查询")
    private boolean blurry;

}
