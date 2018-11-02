package com.ranze.likechat.web.service;

import com.ranze.likechat.web.entity.UserInfo;
import com.ranze.likechat.web.exception.LikeChatException;
import com.ranze.likechat.web.exception.UserExistsException;
import com.ranze.likechat.web.mapper.UserInfoMapper;
import com.ranze.likechat.web.result.ResultStatEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo createUser(UserInfo userInfo) throws UserExistsException {
        try {
            int insertCount = userInfoMapper.insertSelective(userInfo);
            return userInfo;
        } catch (DuplicateKeyException e) {
            throw new UserExistsException(ResultStatEnum.USER_EXISTS.getMessage());
        } catch (Exception e) {
            throw new LikeChatException(e.getMessage(), e);
        }

    }

}
