package com.ranze.likechat.web.controller;

import com.ranze.likechat.web.exception.CellPhoneExistsException;
import com.ranze.likechat.web.result.Result;
import com.ranze.likechat.web.entity.UserInfo;
import com.ranze.likechat.web.result.ResultStatEnum;
import com.ranze.likechat.web.service.UserInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/userinfo")
@Slf4j
public class UserInfoController {
    @Autowired
    UserInfoServiceImpl userInfoServiceImpl;

    @PostMapping("/create")
    public Result<UserInfo> create(@RequestBody @Valid UserInfo userInfo, BindingResult errorResult) {
        if (errorResult.hasErrors()) {
            log.warn("UserInfo has error: {}", errorResult.getAllErrors());
            return Result.failure(ResultStatEnum.PARAMETER_ERROR);
        }

        UserInfo user = null;
        try {
            user = userInfoServiceImpl.createUser(userInfo);
        } catch (CellPhoneExistsException e) {
            return Result.failure(ResultStatEnum.CELL_PHONE_EXISTS);
        }
        return Result.success(user);
    }

}
