package com.ranze.likechat.im.client.handler;

import com.ranze.likechat.im.proto.MessageProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageProto.MessageResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProto.MessageResponse msg) throws Exception {
        log.info("Receive msg: {}", msg.getContent());
    }

}
