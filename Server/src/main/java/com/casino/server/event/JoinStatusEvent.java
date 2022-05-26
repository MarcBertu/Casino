package com.casino.server.event;

import com.casino.enums.Messages;
import com.casino.game.Game;
import com.casino.main.Main;
import com.casino.packet.GameInfoPacket;
import com.casino.packet.ResponsePacket;
import xyz.baddeveloper.lwsl.packet.Packet;
import xyz.baddeveloper.lwsl.server.SocketHandler;

import java.util.Optional;
import java.util.UUID;

public class JoinStatusEvent extends Event{
    @Override
    public void onPacketReceived(SocketHandler socket, Packet packet) {
        if (!packet.getObject().getString("packetId").equals("joinStatus")) {
            return;
        }

        Optional<Game> oGame = Main.gm.getGame(UUID.fromString(packet.getObject().getString("gameId")));

        if (oGame.isEmpty()) {
            return;
        }

        Game game = oGame.get();

        if (packet.getObject().getBoolean("isJoined")) {
            for (Game gameIt : Main.gm.getGames()) {
                if (gameIt.isInGame(socket)) {
                    socket.sendPacket(new ResponsePacket(Messages.JOIN_ERROR));
                    return;
                }
            }

            if (game.getPlayers().size() >= game.getMaxPlayer()) {
                socket.sendPacket(new ResponsePacket(Messages.GAME_FULL));
                return;
            }

            if (!game.isInGame(socket)) {
                game.addPlayer(Main.getPlayer(socket.getSocket()));
                socket.sendPacket(new ResponsePacket(Messages.JOIN_SUCCESS));
            }

        } else {
            game.removePlayer(Main.getPlayer(socket.getSocket()));
        }

    }
}