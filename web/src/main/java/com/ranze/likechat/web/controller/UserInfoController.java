package com.ranze.likechat.web.controller;

import com.ranze.likechat.web.exception.UserExistsException;
import com.ranze.likechat.web.result.Result;
import com.ranze.likechat.web.entity.UserInfo;
import com.ranze.likechat.web.result.ResultStatEnum;
import com.ranze.likechat.web.service.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    @Autowired
    UserInfoServiceImpl userInfoServiceImpl;

    @PostMapping("/create")
    public Result<UserInfo> create(@RequestBody UserInfo userInfo) {
        if (userInfo == null) {
            return Result.failure(ResultStatEnum.USER_EXISTS);
        }

        UserInfo user = null;
        try {
            user = userInfoServiceImpl.createUser(userInfo);
        } catch (UserExistsException e) {
            return Result.failure(ResultStatEnum.USER_EXISTS);
        }
        return Result.success(user);
    }

}
