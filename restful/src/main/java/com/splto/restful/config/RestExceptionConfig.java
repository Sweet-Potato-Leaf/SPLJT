package com.splto.restful.config;


import com.splto.restful.model.API;
import com.splto.restful.model.APIException;
import javax.annotation.Resource;

import com.splto.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;


@Slf4j
public class RestExceptionConfig {


    @Resource
    private ApplicationContext mContext;

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public API<Object> illegalParamsExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        log.error(ExceptionUtil.toStackTrace(ex));
        return API.e(400, !ObjectUtils.isEmpty(fieldError)? fieldError.getDefaultMessage():"");

    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public API<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String error = String.format("The parameter '%s' should be of type '%s'", ex.getName(), !ObjectUtils.isEmpty(ex.getRequiredType())? ex.getRequiredType().getSimpleName():"");
        log.error(ExceptionUtil.toStackTrace(ex));
        return API.e(400, error);
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public API<Object> noHandlerFoundException(Exception ex) {
        log.error(ExceptionUtil.toStackTrace(ex));
        return API.e(404, "Resource Not Found");
    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public API<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(","));
        log.error(ExceptionUtil.toStackTrace(ex));
        return API.e(415, builder.toString());
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public API<Object> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error(ExceptionUtil.toStackTrace(ex));
        return API.e(405, ex.getMessage());
    }

    @ExceptionHandler(value = {APIException.class})
    public API<Object> APIException(APIException ex) {
        log.error(ExceptionUtil.toStackTrace(ex));
        return API.e(ex.getApiError().getCode(), ex.getApiError().getMsg());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public API<Object> APIException(IllegalArgumentException ex) {
        log.error(ExceptionUtil.toStackTrace(ex));
        return API.e(406, ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public API<Object> handleConstraintViolationException(BindException e) {
        log.error(ExceptionUtil.toStackTrace(e));
        return API.e(400, (ObjectUtils.isEmpty(e.getBindingResult().getFieldError()) ? "" : e.getBindingResult().getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    public API<Object> unknownException(Exception ex) {
        if (mContext != null){
            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter( writer );
            ex.printStackTrace(printWriter);
        }
        log.error(ExceptionUtil.toStackTrace(ex));
        return API.e(500,"服务器错误");
    }
}
