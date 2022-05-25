package com.casino.packet;

import xyz.baddeveloper.lwsl.packet.Packet;

import java.util.UUID;

public class JoinStatusPacket extends Packet {

    public JoinStatusPacket(boolean isJoined, UUID gameId) {
        getObject().put("packetId", "joinStatus");
        getObject().put("gameId", gameId);
        getObject().put("isJoined", isJoined);
    }

}
