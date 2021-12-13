package com.longpengz.restful.bean;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@ApiModel(description = "API异常")
public class APIException  extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 4125096758372084309L;

    private APIError apiError;

    public APIException(APIError apiError) {
        super(apiError.getMsg());
        this.apiError = apiError;
    }

}
