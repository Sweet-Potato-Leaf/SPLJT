package com.splto.restful.model;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@ApiModel(description = "API异常")
public class APIException  extends RuntimeException implements Serializable {

    private APIError apiError;

    public APIException(APIError apiError) {
        super(apiError.getMsg());
        this.apiError = apiError;
    }

}
