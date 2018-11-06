package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.ResultStatEnum;

public class ExceedQpsLimitException extends Exception {

    public ExceedQpsLimitException(ResultStatEnum resultStatEnum) {
        super(resultStatEnum.getMessage());
    }

    public ExceedQpsLimitException(String message) {
        super(message);
    }

    public ExceedQpsLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
