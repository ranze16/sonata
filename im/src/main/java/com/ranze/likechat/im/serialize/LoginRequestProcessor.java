package com.ranze.likechat.im.serialize;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ranze.likechat.im.proto.LoginProto;

public class LoginRequestProcessor implements ProtoProcessor<LoginProto.LoginRequest> {

    @Override
    public LoginProto.LoginRequest process(byte[] bytes) throws InvalidProtocolBufferException {
        return LoginProto.LoginRequest.parseFrom(bytes);
    }
}
