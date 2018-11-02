package com.ranze.likechat.web.mapper;

import com.ranze.likechat.web.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoMapperTest {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
        long id = 3;
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setNickName("test3");
        userInfo.setSex("male");
        userInfo.setCellPhoneNum("15261163838");
        userInfoMapper.insertSelective(userInfo);
    }

    @Test
    public void selectByPrimaryKey() {
        long id = 1;
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        System.out.println(userInfo);

    }

    @Test
    public void updateByPrimaryKeySelective() {
        long id = 1;
        UserInfo u = new UserInfo();
        u.setId(id);
        u.setNickName("test1-1");
        userInfoMapper.updateByPrimaryKeySelective(u);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        System.out.println(userInfo);

    }

    @Test
    public void updateByPrimaryKeyWithBLOBs() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}