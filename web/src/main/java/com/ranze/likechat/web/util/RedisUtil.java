package com.ranze.likechat.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public <T> void vSet(String key, T value, long expire) {
        redisTemplate.opsForValue().set(key, value);
        if (expire != -1) {
            redisTemplate.expire(vGet(key), expire, TimeUnit.SECONDS);
        }
    }

    public <T> void vSet(String key, T value) {
        vSet(key, value, -1);
    }

    public <T> T vGet(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public boolean del(String key) {
        return redisTemplate.delete(key);
    }

    public boolean has(String key) {
        return redisTemplate.hasKey(key);
    }

}
