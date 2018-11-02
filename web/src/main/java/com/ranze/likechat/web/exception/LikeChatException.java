package com.ranze.likechat.web.exception;

public class LikeChatException extends RuntimeException {
    public LikeChatException(String message) {
        super(message);
    }

    public LikeChatException(String message, Throwable cause) {
        super(message, cause);
    }
}
