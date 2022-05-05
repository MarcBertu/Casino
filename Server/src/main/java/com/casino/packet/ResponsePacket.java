package com.casino.packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class ResponsePacket extends Packet {

    public ResponsePacket(boolean error, String msg) {
        getObject().put("packetId", "responseMsg");
        getObject().put("isError", error);
        getObject().put("msg", msg);
    }
}
