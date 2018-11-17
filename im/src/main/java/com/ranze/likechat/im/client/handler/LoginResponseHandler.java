package com.ranze.likechat.im.client.handler;


import com.ranze.likechat.im.proto.LoginProto;
import com.ranze.likechat.im.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginProto.LoginResponse> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginProto.LoginRequest.Builder loginRequestBuilder = LoginProto.LoginRequest.newBuilder();
        loginRequestBuilder.setPhoneNum(UUID.randomUUID().toString());
        loginRequestBuilder.setPassword("ll");

        ctx.channel().writeAndFlush(loginRequestBuilder.build());
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginProto.LoginResponse loginResponse) throws Exception {
        if (loginResponse.getCode() == 0) {
            System.out.println(new Date() + "：客户端登录成功");
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponse.getMessage());
        }

    }
}
