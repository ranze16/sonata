package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.ResultStatEnum;

public class InnerErrorException extends BaseException {
    public InnerErrorException(ResultStatEnum resultStatEnum) {
        super(resultStatEnum);
    }

    public InnerErrorException(String message) {
        super(message);
    }

    public InnerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
