package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.ResultStatEnum;

public class WrongValidationCodeException extends BaseException {
    public WrongValidationCodeException(ResultStatEnum resultStatEnum) {
        super(resultStatEnum);
    }

    public WrongValidationCodeException(String message) {
        super(message);
    }

    public WrongValidationCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
