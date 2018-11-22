package com.ranze.likechat.im.client.handler;


import com.ranze.likechat.im.proto.LoginProto;
import com.ranze.likechat.im.proto.MessageProto;
import com.ranze.likechat.im.util.LoginUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginProto.LoginResponse> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginProto.LoginResponse loginResponse) throws Exception {
        if (loginResponse.getCode() == 0) {
            log.info("登录成功");
            LoginUtil.markAsLogin(channelHandlerContext.channel());
            startConsoleThread(channelHandlerContext.channel());
        } else {
            log.info("登录失败，原因：" + loginResponse.getMessage());
        }

    }

    private void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("---开始发送消息---");
                    Scanner sc = new Scanner(System.in);
                    System.out.print("发送给：");
                    String userId = sc.nextLine();
                    System.out.print("消息内容：");
                    String content = sc.nextLine();
                    MessageProto.MessageRequest.Builder msgRequestBuilder = MessageProto.MessageRequest.newBuilder();
                    msgRequestBuilder.setTo(userId);
                    msgRequestBuilder.setContent(content);
                    channel.writeAndFlush(msgRequestBuilder.build());
                }
            }
        }).start();
    }
}
