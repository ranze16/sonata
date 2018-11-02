package com.ranze.likechat.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @NotNull(message = "ID cannot be null")
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    @NotEmpty(message = "NickName cannot be empty")
    @Size(min = 1, max = 30)
    private String nickName;

    @Pattern(regexp = "male|female", message = "Sex must be one of 'male' or 'female'")
    private String sex;

    private Date birthDate;

    @Pattern(regexp = "^\\d{11}$", message = "Wrong phone number")
    private String cellPhoneNum;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$",
            message = "密码需要8-30个字符，必须同时包含字母和数字")
    private String password;

    private String livingCountry;

    private String livingProvince;

    private String livingCity;

    private String signature;

    private byte[] headPortrait;

}