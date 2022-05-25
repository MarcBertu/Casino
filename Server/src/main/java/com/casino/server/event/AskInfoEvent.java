package com.casino.server.event;

import com.casino.main.Main;
import com.casino.packet.GameInfoPacket;
import xyz.baddeveloper.lwsl.packet.Packet;
import xyz.baddeveloper.lwsl.server.SocketHandler;

public class AskInfoEvent extends Event{
    @Override
    public void onPacketReceived(SocketHandler socket, Packet packet) {
        if (!packet.getObject().getString("packetId").equals("askInfoPacket")) {
            return;
        }

        socket.sendPacket(new GameInfoPacket(Main.gm.getGames()));

    }
}
