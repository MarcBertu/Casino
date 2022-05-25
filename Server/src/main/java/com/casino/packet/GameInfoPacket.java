package com.casino.packet;

import com.casino.entity.GameInfo;
import com.casino.game.Game;
import xyz.baddeveloper.lwsl.packet.Packet;

import java.util.ArrayList;
import java.util.List;

public class GameInfoPacket extends Packet {

    public GameInfoPacket(List<Game> games) {
        getObject().put("packetId", "gameInfo");
        List<GameInfo> gameInfos = new ArrayList<GameInfo>();

        for (Runnable runna : games) {
            Game game = (Game) runna;
            GameInfo gi = new GameInfo(game.getName(), game.getType(), game.getPlayers().size(), game.getMaxPlayer(), false, game.getPreGameCounter(), game.getCreatedAt());
            gameInfos.add(gi);
        }

        getObject().put("games", gameInfos);

    }

}
