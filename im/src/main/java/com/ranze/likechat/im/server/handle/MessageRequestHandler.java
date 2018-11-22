package com.ranze.likechat.im.server.handle;

import com.ranze.likechat.im.proto.MessageProto;
import com.ranze.likechat.im.util.LoginUtil;
import com.ranze.likechat.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@Scope("prototype")
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageProto.MessageRequest> {
    @Autowired
    SessionUtil sessionUtil;
    @Autowired
    LoginUtil loginUtil;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProto.MessageRequest msg) throws Exception {
        String userId = loginUtil.getUserId(ctx.channel());
        log.info("Receive from {}, message = {}", userId, msg.getContent());

        MessageProto.MessageResponse.Builder responseBuilder = MessageProto.MessageResponse.newBuilder();
        responseBuilder.setContent(msg.getContent());

        Channel channel = sessionUtil.getChannel(msg.getTo());
        if (channel != null) {
            log.info("User '{}' stay in this server", msg.getTo());
            channel.writeAndFlush(responseBuilder.build());
        } else {
            String serverIp = sessionUtil.getServerIp(msg.getTo());
            if (StringUtils.isEmpty(serverIp)) {
                log.info("User '{}' is offline", msg.getTo());
            }
            log.info("User '{}' stay in server '{}'", msg.getTo(), serverIp);
        }
    }
}
