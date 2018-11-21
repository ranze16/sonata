package com.ranze.likechat.im.enums;

public enum ResultEnum {
    SUCCESS(0, "OK"),
    INNER_ERROR(1, "Inner error"),
    WRONG_USER_PASSWORD(100, "错误的用户名或密码");

    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ResultEnum stateOf(int index) {
        for (ResultEnum resultStatEnum : values()) {
            if (resultStatEnum.code == index) {
                return resultStatEnum;
            }
        }
        return null;
    }
}
