package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.ResultStatEnum;

public class BusinessException extends RuntimeException {
    protected ResultStatEnum resultStatEnum;

    public BusinessException(ResultStatEnum resultStatEnum) {
        this.resultStatEnum = resultStatEnum;
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
