package com.splto.dp.model.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeFilter {

    @Schema(title = "字段名称")
    private String name;

    @Schema(title = "开始时间")
    private Long start;

    @Schema(title = "结束时间")
    private Long end;
}
