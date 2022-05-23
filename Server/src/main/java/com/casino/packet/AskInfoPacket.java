package com.casino.packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class AskInfoPacket extends Packet {

    public AskInfoPacket() {
        getObject().put("packetId", "askInfoPacket");
    }
}
