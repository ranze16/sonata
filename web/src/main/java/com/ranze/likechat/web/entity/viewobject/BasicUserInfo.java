package com.ranze.likechat.web.entity.viewobject;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class BasicUserInfo {
    @Pattern(regexp = "^\\d{11}$", message = "Wrong phone number")
    private String cellPhoneNum;
    @NotEmpty
    private String token;
}
