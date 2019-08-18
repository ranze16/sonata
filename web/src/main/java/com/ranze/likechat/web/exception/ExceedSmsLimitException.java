package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.ResultStatEnum;

public class ExceedSmsLimitException extends Exception {
    private ResultStatEnum resultStatEnum;


    public ExceedSmsLimitException(ResultStatEnum resultStatEnum) {
        this.resultStatEnum = resultStatEnum;
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
