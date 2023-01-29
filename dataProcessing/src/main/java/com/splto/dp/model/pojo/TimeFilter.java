package com.splto.dp.model.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeFilter {

    @ApiModelProperty("字段名称")
    private String name;

    @ApiModelProperty("开始时间")
    private Long start;

    @ApiModelProperty("结束时间")
    private Long end;
}
