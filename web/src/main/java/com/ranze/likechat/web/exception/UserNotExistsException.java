package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.ResultStatEnum;

public class UserNotExistsException extends BaseException {
    public UserNotExistsException(ResultStatEnum resultStatEnum) {
        super(resultStatEnum);
    }

    public UserNotExistsException(String message) {
        super(message);
    }

    public UserNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
