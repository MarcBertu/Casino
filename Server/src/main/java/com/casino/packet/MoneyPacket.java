package com.casino.packet;

import xyz.baddeveloper.lwsl.packet.Packet;

import java.util.UUID;

public class MoneyPacket extends Packet {

    public MoneyPacket(int money, UUID uuid) {
        getObject().put("packetId", "defineMoney");
        getObject().put("gameId", uuid);
        getObject().put("money", money);
    }
}
