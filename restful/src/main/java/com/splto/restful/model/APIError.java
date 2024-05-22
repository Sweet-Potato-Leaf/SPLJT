package com.splto.restful.model;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "API错误信息")
public class APIError implements Serializable {

    @Schema(title = "信息补充说明")
    private String msg;

    @Schema(title = "状态码")
    private Integer code;

    public static void e(String msg){
        e(400,msg);
    }
    public static void NEED_LOGIN(){
        e(401,"需要登录");
    }
    public static void INVALID_REQ(){
        e(400,"参数错误");
    }
    public static void CONFLICT(){
        e(409,"请勿重复操作");
    }
    public static void FORBIDDEN(){
        e(403,"没有权限");
    }
    public static void NOT_FOUND(){
        NOT_FOUND("");
    }
    public static void NOT_FOUND(String msg){
        e(404,msg + "没有找到");
    }

    public static void e(Integer code, String msg){
        APIError error = new APIError();
        error.setCode(code);
        error.setMsg(msg);
        throw new APIException(APIError.builder()
                .code(code)
                .msg(msg).build());
    }
}
