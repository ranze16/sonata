package com.ranze.likechat.im.serialize;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.ranze.likechat.im.protocal.command.Command.LOGIN_REQUEST;
import static com.ranze.likechat.im.protocal.command.Command.LOGIN_RESPONSE;


public class ProtoSerializer {
    private static Map<Byte, ProtoProcessor<? extends AbstractMessage>> processors;
    private static Map<Class<? extends AbstractMessage>, Method> methodCache;

    static {
        processors = new HashMap<>();
        processors.put(LOGIN_REQUEST, new LoginRequestProcessor());
        processors.put(LOGIN_RESPONSE, new LoginResponseProcessor());

        methodCache = new HashMap<>();

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

    public static <T extends AbstractMessage> T deserialize(Class<T> clz, byte[] bytes) {
        try {
            Method parseFromMethod = getMethod(clz);
            return (T) parseFromMethod.invoke(null, bytes);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Method getMethod(Class<? extends AbstractMessage> clz) {
        Method method = methodCache.get(clz);
        if (method == null) {
            try {
                method = clz.getDeclaredMethod("parseFrom", byte[].class);
                methodCache.put(clz, method);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return method;
    }


}
