package com.casino.packet;

import com.casino.entity.Player;
import com.casino.game.Game;
import xyz.baddeveloper.lwsl.packet.Packet;

public class PlayerInformationPacket extends Packet {

    public PlayerInformationPacket(Player player, Game game) {
        getObject().put("packetId", "playerInformation");
        getObject().put("username", player.getUsername());
        getObject().put("money", player.getMoney());
        if (game != null) {
            getObject().put("cards", game.getPlayerCard().get(player).getCards());
        }
    }

}
