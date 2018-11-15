package com.ranze.likechat.im.protocal.response;

import com.ranze.likechat.im.protocal.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ranze.likechat.im.protocal.command.Command.MESSAGE_RESPONSE;

@Data
@NoArgsConstructor
public class MessageResponsePacket extends Packet {
    private String message;

    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
