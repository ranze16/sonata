package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.ResultStatEnum;

public class UserNotLoggedInException extends BaseException {
    public UserNotLoggedInException(ResultStatEnum resultStatEnum) {
        super(resultStatEnum);
    }

    public UserNotLoggedInException(String message) {
        super(message);
    }

    public UserNotLoggedInException(String message, Throwable cause) {
        super(message, cause);
    }
}
