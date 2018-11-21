package com.ranze.likechat.im.serialize;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

import java.util.HashMap;
import java.util.Map;

import static com.ranze.likechat.im.protocal.command.Command.LOGIN_REQUEST;
import static com.ranze.likechat.im.protocal.command.Command.LOGIN_RESPONSE;


public class ProtoSerializer {
    private static Map<Byte, ProtoProcessor<? extends AbstractMessage>> processors;

    static {
        processors = new HashMap<>();
        processors.put(LOGIN_REQUEST, new LoginRequestProcessor());
        processors.put(LOGIN_RESPONSE, new LoginResponseProcessor());

    }

    public static byte[] serialize(Object object) {
        return ((MessageLite) object).toByteArray();
    }

    public static <T> T deserialize(byte command, byte[] bytes) {
        ProtoProcessor<? extends AbstractMessage> protoProcessor = processors.get(command);
        if (protoProcessor != null) {
            try {
                return (T) protoProcessor.process(bytes);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
