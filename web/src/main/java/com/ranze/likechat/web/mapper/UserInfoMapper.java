package com.ranze.likechat.web.mapper;

import com.ranze.likechat.web.entity.dataobject.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    UserInfo selectByCellPhoneNum(String cellPhoneNum);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKeyWithBLOBs(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}