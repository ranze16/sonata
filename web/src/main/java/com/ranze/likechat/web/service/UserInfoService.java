package com.ranze.likechat.web.service;

import com.ranze.likechat.web.entity.dataobject.UserInfo;
import com.ranze.likechat.web.entity.viewobject.UserCreate;
import com.ranze.likechat.web.exception.CellPhoneExistsException;
import com.ranze.likechat.web.exception.WrongValidationCodeException;

public interface UserInfoService {
    void createUser(UserCreate userCreate) throws CellPhoneExistsException, WrongValidationCodeException;
}
