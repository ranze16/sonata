package com.ranze.likechat.im.server.handle;


import com.ranze.likechat.im.proto.LoginProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginProto.LoginRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginProto.LoginRequest loginRequest) throws Exception {
        System.out.println(new Date() + "收到客户端请求, phone name = " + loginRequest.getPhoneNum());

        LoginProto.LoginResponse.Builder loginResponseBuilder = LoginProto.LoginResponse.newBuilder();
        if (valid(loginRequest)) {
            loginResponseBuilder.setCode(0);
            loginResponseBuilder.setMessage("ok");
            System.out.println(new Date() + ": 登录成功");
        } else {
            loginResponseBuilder.setCode(100);
            loginResponseBuilder.setMessage("账号密码校验失败");
            System.out.println(new Date() + ": 登录失败");
        }
        channelHandlerContext.channel().writeAndFlush(loginResponseBuilder.build());
    }

    private boolean valid(LoginProto.LoginRequest loginRequest) {
        return true;
    }
}
