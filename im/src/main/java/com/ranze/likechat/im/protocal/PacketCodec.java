package com.ranze.likechat.im.protocal;


import com.google.protobuf.AbstractMessage;
import com.ranze.likechat.im.proto.LoginProto;
import com.ranze.likechat.im.serialize.ProtoSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.ranze.likechat.im.protocal.command.Command.LOGIN_REQUEST;
import static com.ranze.likechat.im.protocal.command.Command.LOGIN_RESPONSE;

public class PacketCodec {
    public static final int MAGIC_NUMBER = 0x12345678;
    private static final int version = 0x01;

    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<Class<? extends AbstractMessage>, Byte> messageTypeMap;


    private PacketCodec() {
        messageTypeMap = new HashMap<>();
        messageTypeMap.put(LoginProto.LoginRequest.class, LOGIN_REQUEST);
        messageTypeMap.put(LoginProto.LoginResponse.class, LOGIN_RESPONSE);

    }

    public void encode(ByteBuf byteBuf, AbstractMessage message) {
        byte[] bytes = ProtoSerializer.serialize(message);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(version);
        byteBuf.writeByte(getCommand(message.getClass()));

        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public AbstractMessage decode(ByteBuf byteBuf) {
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 指令
        byte command = byteBuf.readByte();

        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        return ProtoSerializer.deserialize(command, bytes);

    }

    private Byte getCommand(Class<? extends AbstractMessage> clazz) {
        return messageTypeMap.get(clazz);
    }


}
