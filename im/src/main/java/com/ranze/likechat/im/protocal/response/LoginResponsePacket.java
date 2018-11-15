package com.ranze.likechat.im.protocal.response;


import com.ranze.likechat.im.protocal.Packet;
import lombok.Data;

import static com.ranze.likechat.im.protocal.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private boolean success;
    private String reason;

    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
