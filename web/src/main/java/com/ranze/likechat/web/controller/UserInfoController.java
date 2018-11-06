package com.ranze.likechat.web.controller;

import com.ranze.likechat.web.entity.viewobject.UserCreate;
import com.ranze.likechat.web.exception.CellPhoneExistsException;
import com.ranze.likechat.web.exception.ExceedQpsLimitException;
import com.ranze.likechat.web.exception.WrongValidationCodeException;
import com.ranze.likechat.web.result.Result;
import com.ranze.likechat.web.entity.dataobject.UserInfo;
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
    public Result<Void> create(@RequestBody @Valid UserCreate userCreate, BindingResult errorResult) {
        if (errorResult.hasErrors()) {
            log.warn("UserInfo has error: {}", errorResult.getAllErrors());
            return Result.failure(ResultStatEnum.PARAMETER_ERROR);
        }

        try {
            userInfoServiceImpl.createUser(userCreate);
        } catch (CellPhoneExistsException | WrongValidationCodeException | ExceedQpsLimitException e) {
            return Result.failure(e.getResultStatEnum());
        }
        return Result.success();
    }

}
