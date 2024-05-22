package com.splto.restful.config.interceptor;

import com.splto.restful.model.TraceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

/**
 * 日志处理
 */
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!StringUtils.hasLength(TraceContext.getTraceId())) {
            TraceContext.setTraceId(UUID.randomUUID().toString());
        }
        return true;
    }

}
