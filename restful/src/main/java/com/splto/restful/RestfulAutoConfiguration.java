package com.splto.restful;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.splto.restful")
public class RestfulAutoConfiguration {
    public RestfulAutoConfiguration() {
        System.out.println("init rest");
    }
}
