package com.splto.dp.model.pojo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author longpengZ
 */
@Data
@Tag(name = "PageResult",description = "分页后的参数")
public class PageResult<T> {

    @Schema(title = "数据列表")
    private List<T> content;

    @Schema(title = "分页参数")
    private PageParameter page;

    @Schema(title = "扩展参数")
    private Map<String , Object> extInfos;

    /**
     * mybatis 转换
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
}
