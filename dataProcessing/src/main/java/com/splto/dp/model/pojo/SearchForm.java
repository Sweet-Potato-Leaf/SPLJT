package com.splto.dp.model.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "查询基础参数")
public class SearchForm {

    @Builder.Default
    @Schema(title = "当前页（0开始）")
    private int pageNum = 0;

    @Builder.Default
    @Schema(title = "每页大小")
    private int pageSize = 20;

    @Schema(title = "时间筛选条件字符串格式name,start,end;")
    private String timeStrs;

    @Schema(title = "其他筛选条件字符串格式name,content,blurry;")
    private String fieldStrs;

    @Schema(title = "时间筛选条件")
    private List<TimeFilter> timeFilters = new ArrayList<>();

    @Schema(title = "其他筛选条件")
    private List<FieldFilter> fieldFilers = new ArrayList<>();

    public PageRequest  pageRequest(){
        return PageRequest.of(pageNum , pageSize, Sort.by(Sort.Direction.DESC,"createTime"));
    }
}
