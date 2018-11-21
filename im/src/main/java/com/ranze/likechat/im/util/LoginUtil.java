package com.ranze.likechat.im.util;

import com.ranze.likechat.im.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import org.springframework.stereotype.Component;

@Component
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public void setUserId(Channel channel, String userId) {
        channel.attr(Attributes.USER_ID).set(userId);
    }

    public String getUserId(Channel channel) {
        return channel.attr(Attributes.USER_ID).get();
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
