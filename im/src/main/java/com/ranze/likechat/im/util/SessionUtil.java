package com.ranze.likechat.im.util;

import com.ranze.likechat.common.RedisUtil;
import com.ranze.likechat.im.cons.RedisConstants;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SessionUtil {
    private Map<String, Channel> channelMap = new HashMap<>();
    @Autowired
    RedisUtil redisUtil;

    public boolean setLoginState(String userId, String serverIp, Channel channel) {
        boolean ret = redisUtil.vSet(RedisConstants.KEY_SERVER_IP + userId, serverIp);
        if (ret) {
            addChannel(userId, channel);
        }
        return ret;
    }

    public boolean removeLoginState(String userId) {
        boolean ret = redisUtil.del(RedisConstants.KEY_SERVER_IP + userId);
        removeChannel(userId);
        return ret;
    }

    public String getServerIp(String userId) {
        return redisUtil.vGet(RedisConstants.KEY_SERVER_IP + userId);
    }

    private void addChannel(String userId, Channel channel) {
        channelMap.put(userId, channel);
    }

    private void removeChannel(String userId) {
        channelMap.remove(userId);
    }

    public Channel getChannel(String userId) {
        return channelMap.get(userId);
    }
}
