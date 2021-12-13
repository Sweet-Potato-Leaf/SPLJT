package com.longpengz.dataprocessing.bean.pojo;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author longpengZ
 */
@Data
@ApiModel(value = "PageResult",description = "分页后的参数")
public class PageResult<T> {

    @ApiModelProperty(value = "数据列表")
    private List<T> content;

    @ApiModelProperty(value = "分页参数")
    private PageParameter page;

    //正常返回
    public static <T> PageResult<T> of(List<T> content){
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setContent(content);
        pageResult.setPage(PageParameter.setPageParameter((Page<T>)content));
        return pageResult;
    }

    // jpa转换为正常返回
    public static <T> PageResult<T> jpaOf(org.springframework.data.domain.Page<T> jpaPage){
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setContent(jpaPage.getContent());
        pageResult.setPage(PageParameter.builder()
                .pageNum(jpaPage.getNumber())
                .pageSize(jpaPage.getSize())
                .total((int)jpaPage.getTotalElements())
                .pages(jpaPage.getTotalPages()).build());
        return pageResult;
    }

    // jpa分页返回参数
    public static <T> org.springframework.data.domain.Page<T> ofJpa(List<T> content){
        PageParameter pageParameter = PageParameter.setPageParameter((Page<T>) content);
        return new PageImpl<T>(content, PageRequest.of(pageParameter.getPageNum(), pageParameter.getPageSize()),pageParameter.getTotal());
    }
}
