package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.ResultStatEnum;

public class BaseException extends Exception {
    protected ResultStatEnum resultStatEnum;

    public BaseException(ResultStatEnum resultStatEnum) {
        this.resultStatEnum = resultStatEnum;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultStatEnum getResultStatEnum() {
        return resultStatEnum;
    }

    @Override
    public String getMessage() {
        if (resultStatEnum != null) {
            return resultStatEnum.getMessage();
        } else {
            return super.getMessage();
        }
    }
}
