package com.ranze.likechat.web.service;

import com.ranze.likechat.web.entity.UserInfo;
import com.ranze.likechat.web.exception.CellPhoneExistsException;
import com.ranze.likechat.web.exception.LikeChatException;
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
    public UserInfo createUser(UserInfo userInfo) throws CellPhoneExistsException {
        try {
            UserInfo user = userInfoMapper.selectByCellPhoneNum(userInfo.getCellPhoneNum());
            if (user != null) {
                throw new CellPhoneExistsException(ResultStatEnum.CELL_PHONE_EXISTS);
            }

            int insertCount = userInfoMapper.insertSelective(userInfo);
            if (insertCount <= 0) {
                throw new CellPhoneExistsException(ResultStatEnum.CELL_PHONE_EXISTS);
            }
            return userInfo;
        } catch (CellPhoneExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new LikeChatException(e.getMessage(), e);
        }

    }

}
