package com.longpengz.dataprocessing.bean.pojo;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author longpengZ
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "page",description = "分页参数")
public class PageParameter {

    @ApiModelProperty(value = "当前页0开始")
    private int pageNum;

    @ApiModelProperty(value = "每页大小")
    private int pageSize;

    @ApiModelProperty(value = "总条数")
    private int total;

    @ApiModelProperty(value = "总页数")
    private int pages;

    @ApiModelProperty(value = "扩展信息")
    private Object expand;

    public static <T> PageParameter setPageParameter(Page<T> page){
        PageParameter pageParameter=new PageParameter();
        pageParameter.setPageNum(page.getPageNum()-1);
        pageParameter.setPageSize(page.getPageSize());
        pageParameter.setTotal((int)page.getTotal());
        pageParameter.setPages(page.getPages());
        return pageParameter;
    }

}
