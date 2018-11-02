package com.ranze.likechat.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String nickName;

    private String sex;

    private Date birthDate;

    private String cellPhoneNum;

    private String password;

    private String livingCountry;

    private String livingProvince;

    private String livingCity;

    private String signature;

    private byte[] headPortrait;

}