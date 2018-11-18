package com.ranze.likechat.web.result;

public enum ResultStatEnum {
    SUCCESS(0, "success"),
    INNER_ERROR(1, "inner error"),
    PARAMETER_ERROR(2, "Parameter error"),
    CELL_PHONE_EXISTS(100, "手机号已经被注册"),
    WRONG_VALIDATION_CODE(101, "验证码错误"),
    EXCEED_QPS_LIMIT(102, "太频繁了，请稍后再试"),
    USER_NOT_EXISTS(103, "用户不存在或者密码错误"),
    USER_NOT_LOGGED_IN(104, "未登录");

    private int state;

    private String message;

    ResultStatEnum(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public static ResultStatEnum stateOf(int index) {
        for (ResultStatEnum resultStatEnum : values()) {
            if (resultStatEnum.state == index) {
                return resultStatEnum;
            }
        }
        return null;
    }
}
