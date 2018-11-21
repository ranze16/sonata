package com.ranze.likechat.im.util;

import com.ranze.likechat.common.RedisUtil;
import com.ranze.likechat.im.cons.RedisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil {
    @Autowired
    RedisUtil redisUtil;

    public boolean setLoginState(String userId, String serverIp) {
        return redisUtil.vSet(RedisConstants.KEY_SERVER_IP + userId, serverIp);
    }

    public boolean removeLoginState(String userId) {
        return redisUtil.del(RedisConstants.KEY_SERVER_IP + userId);
    }
}
