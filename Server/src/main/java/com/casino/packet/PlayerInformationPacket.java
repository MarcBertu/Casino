package com.casino.packet;

import com.casino.entity.Player;
import xyz.baddeveloper.lwsl.packet.Packet;

public class PlayerInformationPacket extends Packet {

    public PlayerInformationPacket(Player player, boolean self) {
        getObject().put("packetId", "playerInformation");
        getObject().put("username", player.getUsername());
        getObject().put("money", player.getMoney());
    }

}
