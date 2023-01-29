package com.splto.dp.model.pojo;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * @author longpengZ
 */
@Data
@ApiModel(value = "PageResult",description = "分页后的参数")
public class PageResult<T> {

    @ApiModelProperty("数据列表")
    private List<T> content;

    @ApiModelProperty("分页参数")
    private PageParameter page;

    @ApiModelProperty("扩展参数")
    private Map<String , Object> extInfos;

    /**
     * mybatis 转换
     * @author longpengZ
     */
    public static <T> PageResult<T> of(List<T> content){
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setContent(content);
        pageResult.setPage(PageParameter.setPageParameter((Page<T>)content));
        return pageResult;
    }

    public static <T,E> PageResult<E> of(List<T> sources, List<E> targets){
        PageResult<E> pageResult = new PageResult<>();
        pageResult.setContent(targets);
        pageResult.setPage(PageParameter.setPageParameter((Page<T>)sources));
        return pageResult;
    }


    /**
     * jpa 转换
     * @author longpengZ
     */
    public static <T> PageResult<T> of(org.springframework.data.domain.Page<T> jpaPage) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setContent(jpaPage.getContent());
        pageResult.setPage(PageParameter.builder()
                .pageNum(jpaPage.getNumber())
                .pageSize(jpaPage.getSize())
                .total((int)jpaPage.getTotalElements())
                .pages(jpaPage.getTotalPages()).build());
        return pageResult;
    }

    public static <T,E> PageResult<E> of(org.springframework.data.domain.Page<T> jpaPage, List<E> list) {
        PageResult<E> pageResult = new PageResult<>();
        pageResult.setContent(list);
        pageResult.setPage(PageParameter.builder()
                .pageNum(jpaPage.getNumber())
                .pageSize(jpaPage.getSize())
                .total((int)jpaPage.getTotalElements())
                .pages(jpaPage.getTotalPages()).build());
        return pageResult;
    }

    /**
     * mybatis 转 jpa
     * @author longpengZ
     */
    public static <T> org.springframework.data.domain.Page<T> ofJpa(List<T> content){
        PageParameter pageParameter = PageParameter.setPageParameter((Page<T>) content);
        return new PageImpl<T>(content, PageRequest.of(pageParameter.getPageNum(), pageParameter.getPageSize()),pageParameter.getTotal());
    }
}
