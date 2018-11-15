package com.ranze.likechat.im.protocal.request;

import com.ranze.likechat.im.protocal.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ranze.likechat.im.protocal.command.Command.MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String message;

    public MessageRequestPacket(String line) {
        message = line;

    }

    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
