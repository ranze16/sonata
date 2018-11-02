package com.ranze.likechat.web.result;

public enum ResultStatEnum {
    SUCCESS(0, "success"),
    INNER_ERROR(1, "inner error"),
    USER_EXISTS(2, "user already exists");


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
