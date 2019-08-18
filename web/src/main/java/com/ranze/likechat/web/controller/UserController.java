package com.ranze.likechat.web.controller;

import com.ranze.likechat.web.entity.viewobject.BasicUserInfo;
import com.ranze.likechat.web.entity.viewobject.UserCreate;
import com.ranze.likechat.web.entity.viewobject.UserLoginReq;
import com.ranze.likechat.web.entity.viewobject.UserLoginResp;
import com.ranze.likechat.web.result.Result;
import com.ranze.likechat.web.result.ResultStatEnum;
import com.ranze.likechat.web.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/create")
    public Result<Void> create(@RequestBody @Valid UserCreate userCreate, BindingResult errorResult) {
        if (errorResult.hasErrors()) {
            log.warn("BasicUserInfo has error: {}", errorResult.getAllErrors());
            return Result.failure(ResultStatEnum.PARAMETER_ERROR);
        }

        userService.createUser(userCreate);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<UserLoginResp> login(@RequestBody @Valid UserLoginReq userLoginReq) {
        UserLoginResp userLoginResp = userService.userLogin(userLoginReq);
        return Result.success(userLoginResp);

    }

    @PostMapping("/logout")
    public Result<Void> logout(@RequestBody @Valid BasicUserInfo basicUserInfo) {
        userService.userLogout(basicUserInfo);
        return Result.success();
    }

}
