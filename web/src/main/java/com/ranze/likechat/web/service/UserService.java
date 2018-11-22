package com.ranze.likechat.web.service;

import com.ranze.likechat.web.entity.viewobject.BasicUserInfo;
import com.ranze.likechat.web.entity.viewobject.UserCreate;
import com.ranze.likechat.web.entity.viewobject.UserLoginReq;
import com.ranze.likechat.web.entity.viewobject.UserLoginResp;

public interface UserService {
    void createUser(UserCreate userCreate);
    UserLoginResp userLogin(UserLoginReq userLoginReq);

    void userLogout(BasicUserInfo basicUserInfo);
}
