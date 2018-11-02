package com.ranze.likechat.web.result;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(0, "success", data);
    }

    public static Result<Void> success() {
        return success(null);
    }

    public static <T> Result<T> failure(int code, String message) {
        return new Result<T>(code, message);
    }

    public static <T> Result<T> failure(ResultStatEnum resultStatEnum) {
        return failure(resultStatEnum.getState(), resultStatEnum.getMessage());
    }
}
