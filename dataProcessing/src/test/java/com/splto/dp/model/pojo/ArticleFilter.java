package com.splto.dp.model.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "文章筛选参数")
public class ArticleFilter {

    @Schema(title = "文章id")
    private Integer articleId;

    @Schema(title = "用户id")
    private Integer userId;

    @Schema(title = "热门排序")
    private Boolean isHot;

    @Schema(title = "最新排序")
    private Boolean isNew;
    
    @Schema(title = "综合搜索")
    private String comprehensiveContent;

    @Schema(title = "状态")
    private ArticleStatusEnum status;

    @Schema(title = "文章ids列表")
    private List<Integer> articleIds;

}
