package com.longpengz.dataprocessing.bean.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "文章筛选参数")
public class ArticleFilter {

    @ApiModelProperty(value = "文章id")
    private Integer articleId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "热门排序")
    private Boolean isHot;

    @ApiModelProperty(value = "最新排序")
    private Boolean isNew;
    
    @ApiModelProperty(value = "综合搜索")
    private String comprehensiveContent;

    @ApiModelProperty(value = "状态")
    private ArticleStatusEnum status;

    @ApiModelProperty(value = "文章ids列表")
    private List<Integer> articleIds;

}
