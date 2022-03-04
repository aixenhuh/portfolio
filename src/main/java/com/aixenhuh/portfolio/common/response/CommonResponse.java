package com.aixenhuh.portfolio.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse {
    private String message;
    private int status;
    private String code;
    private Object data;
}
