package com.ranze.likechat.im.attribute;

import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<String> USER_ID = AttributeKey.newInstance("user_id");

}
