package com.ranze.likechat.web.util;

import com.ranze.likechat.common.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void set() {
        String key = "test-key1";
        String value = "test-value2";
        redisUtil.vSet(key, value);
        System.out.println(redisUtil.<String>vGet(key));
        assertEquals(value, redisUtil.vGet(key));
    }

    @Test
    public void get() {
    }
}