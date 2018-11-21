package com.ranze.likechat.im.server.handle;


import com.ranze.likechat.im.enums.ResultEnum;
import com.ranze.likechat.im.proto.LoginProto;
import com.ranze.likechat.im.util.LoginUtil;
import com.ranze.likechat.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Slf4j
@Component
@Scope("prototype")
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginProto.LoginRequest> {
    @Autowired
    SessionUtil sessionUtil;
    @Autowired
    LoginUtil loginUtil;

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        sessionUtil.removeLoginState(loginUtil.getUserId(ctx.channel()));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginProto.LoginRequest loginRequest) {
        LoginProto.LoginResponse.Builder loginResponseBuilder = LoginProto.LoginResponse.newBuilder();
        if (valid(loginRequest)) {
            boolean success = sessionUtil.setLoginState(loginRequest.getPhoneNum(), localIp(channelHandlerContext));
            if (success) {
                loginResponseBuilder.setCode(ResultEnum.SUCCESS.getCode());
                loginResponseBuilder.setMessage(ResultEnum.SUCCESS.getMessage());
                log.info("用户 '{}' 登录成功", loginRequest.getPhoneNum());

                loginUtil.setUserId(channelHandlerContext.channel(), loginRequest.getPhoneNum());

                channelHandlerContext.channel().writeAndFlush(loginResponseBuilder.build());
            } else {
                loginResponseBuilder.setCode(1);
                loginResponseBuilder.setMessage("Inner error");
                log.warn("用户 '{}' 登录失败, message = {}", loginRequest.getPhoneNum(),
                        ResultEnum.WRONG_USER_PASSWORD.getMessage());
                channelHandlerContext.close();
            }
        } else {
            loginResponseBuilder.setCode(ResultEnum.WRONG_USER_PASSWORD.getCode());
            loginResponseBuilder.setMessage(ResultEnum.WRONG_USER_PASSWORD.getMessage());
            log.warn("用户 '{}' 登录失败, message = {}", loginRequest.getPhoneNum(),
                    ResultEnum.WRONG_USER_PASSWORD.getMessage());
            channelHandlerContext.close();
        }
    }

    private boolean valid(LoginProto.LoginRequest loginRequest) {
        return true;
    }

    private String localIp(ChannelHandlerContext ctx) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().localAddress();
        return inetSocketAddress.getAddress().getHostAddress();
    }
}
