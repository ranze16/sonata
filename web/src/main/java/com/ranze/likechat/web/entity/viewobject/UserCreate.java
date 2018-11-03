package com.ranze.likechat.web.entity.viewobject;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UserCreate {
    @Pattern(regexp = "^\\d{11}$", message = "Wrong phone number")
    private String cellPhoneNum;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$",
            message = "密码需要8-30个字符，必须同时包含字母和数字")
    private String password;
    private int validationCode = 0;
}
