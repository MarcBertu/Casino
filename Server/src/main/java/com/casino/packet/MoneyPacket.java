package com.casino.packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class MoneyPacket extends Packet {

    public MoneyPacket(int money) {
        getObject().put("packetId", "defineMoney");
        getObject().put("money", money);
    }
}
