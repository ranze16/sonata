package com.ranze.likechat.im.codec;

import com.google.protobuf.AbstractMessage;
import com.ranze.likechat.im.protocal.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<AbstractMessage> {
    protected void encode(ChannelHandlerContext channelHandlerContext, AbstractMessage message, ByteBuf byteBuf) throws Exception {
        PacketCodec.INSTANCE.encode(byteBuf, message);
    }
}
