package com.splto.dp.model.pojo;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "文章状态")
public enum ArticleStatusEnum {
    /**
     * 已发布
     */
    PUBLISHED,
    /**
     * 审核中
     */
    UNDER_REVIEW,
    /**
     * 未通过
     */
    NOT_PASS
}
