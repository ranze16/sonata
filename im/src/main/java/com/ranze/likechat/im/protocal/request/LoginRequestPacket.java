package com.ranze.likechat.im.protocal.request;


import com.ranze.likechat.im.protocal.Packet;
import lombok.Data;

import static com.ranze.likechat.im.protocal.command.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {
    private String userId;
    private String userName;
    private String password;

    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
