package com.longpengz.dataprocessing.bean.pojo;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "查询基础参数")
public class SeachForm {

    @ApiModelProperty(value = "当前页（0开始）")
    private int pageNum = 0;

    @ApiModelProperty(value = "每页大小")
    private int pageSize = 20;

    @ApiModelProperty(value = "时间筛选条件字符串格式name,start,end;")
    private String timeStrs;

    @ApiModelProperty(value = "其他筛选条件字符串格式name,content,blurry;")
    private String fieldStrs;

    @ApiModelProperty(value = "时间筛选条件")
    private List<TimeFilter> timeFilters = new ArrayList<>();

    @ApiModelProperty(value = "其他筛选条件")
    private List<FieldFilter> fieldFilers = new ArrayList<>();

    public PageRequest  pageRequest(){
        return PageRequest.of(pageNum , pageSize, Sort.by(Sort.Direction.DESC,"createTime"));
    }

    public void pageHelperStartPage(){
        PageHelper.startPage(pageNum + 1,pageSize);
    }
}
