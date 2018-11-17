package com.ranze.likechat.im.serialize;

import com.google.protobuf.InvalidProtocolBufferException;

public interface ProtoProcessor<T> {
    T process(byte[] bytes) throws InvalidProtocolBufferException;
}
