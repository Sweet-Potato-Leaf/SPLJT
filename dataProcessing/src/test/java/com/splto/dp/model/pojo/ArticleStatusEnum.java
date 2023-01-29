package com.splto.dp.model.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "文章状态")
public enum ArticleStatusEnum {
    @ApiModelProperty("已发布")
    PUBLISHED,
    @ApiModelProperty("审核中")
    UNDER_REVIEW,
    @ApiModelProperty("未通过")
    NOT_PASS
}
