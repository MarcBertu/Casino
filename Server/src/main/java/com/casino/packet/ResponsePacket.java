package com.casino.packet;

import com.casino.entity.Messages;
import xyz.baddeveloper.lwsl.packet.Packet;

public class ResponsePacket extends Packet {

    public ResponsePacket(Messages msg) {
        getObject().put("packetId", "responseMsg");
        getObject().put("msg", msg);


    }
}
