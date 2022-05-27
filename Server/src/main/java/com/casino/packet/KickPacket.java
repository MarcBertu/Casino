package com.casino.packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class KickPacket extends Packet {

    public KickPacket() {
        getObject().put("packetId", "kickPacket");
    }
}
