package com.casino.packet;

import com.casino.enums.Games;
import xyz.baddeveloper.lwsl.packet.Packet;

public class GameInfoPacket extends Packet {

    public GameInfoPacket(Games game, String name, int playersCount, int timeBeforeStart, long startedAt) {
        getObject().put("packetId", "gameinfo");
        getObject().put("name", name);
        getObject().put("playersCount", playersCount);
        getObject().put("timesBeforeStart", timeBeforeStart);
        getObject().put("startedAt", startedAt);
    }

}
