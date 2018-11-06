package com.ranze.likechat.web.exception;

import com.ranze.likechat.web.result.ResultStatEnum;

public class CellPhoneExistsException extends BaseException {
    public CellPhoneExistsException(ResultStatEnum resultStatEnum) {
        super(resultStatEnum);
    }

    public CellPhoneExistsException(String message) {
        super(message);
    }

    public CellPhoneExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
