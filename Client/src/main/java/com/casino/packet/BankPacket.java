package com.casino.packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class BankPacket extends Packet {

    public BankPacket(int money) {
        getObject().put("packetId", "defineMoney");
        getObject().put("money", money);
    }
}
