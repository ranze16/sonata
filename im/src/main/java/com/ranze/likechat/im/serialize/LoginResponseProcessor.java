package com.ranze.likechat.im.serialize;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ranze.likechat.im.proto.LoginProto;

public class LoginResponseProcessor implements ProtoProcessor<LoginProto.LoginResponse> {

    @Override
    public LoginProto.LoginResponse process(byte[] bytes) throws InvalidProtocolBufferException {
        return LoginProto.LoginResponse.parseFrom(bytes);
    }
}
