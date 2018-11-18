package com.ranze.likechat.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@SuppressWarnings("unchecked")
public class RedisUtil {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public <T> void vSet(String key, T value, long expire) {
        redisTemplate.opsForValue().set(key, value);
        if (expire != -1) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public <T> void vSet(String key, T value) {
        vSet(key, value, -1);
    }

    public <T> T vGet(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public <T> T hGet(String key, String hashKey) {
        return (T) redisTemplate.opsForHash().get(key, hashKey);
    }

    public void hSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public void hDel(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    public boolean del(String key) {
        return redisTemplate.delete(key);
    }

    public boolean has(String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean expire(String key, long time) {
        boolean ret = false;
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
                ret = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

}
