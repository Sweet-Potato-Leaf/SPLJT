package com.splto.dp.model.pojo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "page",description = "分页参数")
public class PageParameter {

    @Schema(title = "当前页0开始")
    private long pageNum;

    @Schema(title = "每页大小")
    private long pageSize;

    @Schema(title = "总条数")
    private long total;

    @Schema(title = "总页数")
    private long pages;

    @Schema(title = "扩展信息")
    private Object expand;

    public static <T> PageParameter setPageParameter(Page<T> page){
        PageParameter pageParameter=new PageParameter();
        pageParameter.setPageNum(page.getCurrent() - 1);
        pageParameter.setPageSize(page.getSize());
        pageParameter.setTotal(page.getTotal());
        pageParameter.setPages(page.getPages());
        return pageParameter;
    }

}
