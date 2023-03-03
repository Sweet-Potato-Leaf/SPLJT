package com.splto.restful.config.interceptor;

import com.splto.restful.model.TraceContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
