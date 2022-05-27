package com.casino.packet;

import com.casino.entity.Card;
import xyz.baddeveloper.lwsl.packet.Packet;

import java.util.List;

public class BankPacket extends Packet {

    public BankPacket(List<Card> cards) {
        getObject().put("packetId", "bankCards");
        getObject().put("cards", cards);
    }

}
