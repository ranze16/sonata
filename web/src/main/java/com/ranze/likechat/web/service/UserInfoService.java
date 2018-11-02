package com.ranze.likechat.web.service;

import com.ranze.likechat.web.entity.UserInfo;
import com.ranze.likechat.web.exception.UserExistsException;

public interface UserInfoService {
    UserInfo createUser(UserInfo userInfo) throws UserExistsException;
}
