package com.splto.restful.model;


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
@AllArgsConstructor
@NoArgsConstructor
public class API<T> {

    @ApiModelProperty("状态码")
    private Integer code;
    
    @ApiModelProperty("数据")
    private T data;
    
    @ApiModelProperty("信息补充说明")
    private String msg;
    
    @ApiModelProperty("日志追踪id")
    private String traceId;

    public static <T> API<T> ok(T data) {
        return ok(200,"成功", data);
    }


    public static API<Object> e(Integer code, String msg) {
        return API.<Object>builder()
                .code(code)
                .msg(msg)
                .traceId(TraceContext.getTraceId()).build();
    }

    public static <T> API<T> ok(Integer code, String msg, T data) {
        return API.<T>builder()
                .data(data)
                .code(code)
                .msg(msg)
                .traceId(TraceContext.getTraceId()).build();
    }

    public static API<Object> e(String msg) {
        return e(400, msg);
    }


}
