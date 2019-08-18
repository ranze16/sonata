package com.ranze.likechat.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class JWTResult {
    public static final int OK = 0;
    public static final int NO_AUTH = 1;
    public static final int EXPIRED = 2;

    private boolean valid;
    private String uid;
    private int code;
    private String msg;

}