package com.splto.dataprocessing.model.pojo;

import org.junit.jupiter.api.Test;

class SpecificationUtilTest {

    @Test
    public void test(){
        SeachForm seachForm = SeachForm.builder().fieldStrs("status,UNDER_REVIEW,fales").build();
        ArticleFilter articleFilter = SpecificationUtil.fieldFilterOfObj(ArticleFilter.class, seachForm.getFieldStrs());
        System.out.println(articleFilter.toString());
//        for (Field declaredField : new ArticleFilter().getClass().getDeclaredFields()) {
//            System.out.println(declaredField.getName());
//        }
    }

}