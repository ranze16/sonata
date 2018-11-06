package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.ResultStatEnum;

public class InnerErrorException extends Exception {
    public InnerErrorException(ResultStatEnum resultStatEnum) {
        this(resultStatEnum.getMessage());
    }

    public InnerErrorException(String message) {
        super(message);
    }

    public InnerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
