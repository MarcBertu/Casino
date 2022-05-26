package com.casino.server.event;

import com.casino.entity.Player;
import com.casino.enums.Messages;
import com.casino.main.Main;
import com.casino.packet.PlayerInformationPacket;
import com.casino.packet.ResponsePacket;
import com.casino.save.SaveManager;
import xyz.baddeveloper.lwsl.packet.Packet;
import xyz.baddeveloper.lwsl.server.SocketHandler;

import java.io.FileWriter;
import java.io.IOException;

public class MoneyEvent extends Event{
    @Override
    public void onPacketReceived(SocketHandler socket, Packet packet) {
        if (!packet.getObject().getString("packetId").equals("defineMoney")) {
            return;
        }

        Player player = Main.getPlayer(socket.getSocket());
        int moneyValue = packet.getObject().getInt("money");

        if (moneyValue > 0) {
            player.setMoney(player.getMoney() + moneyValue);
        } else {
            if (player.getMoney() + moneyValue >= 0) {
                player.setMoney(player.getMoney() + moneyValue);;
                socket.sendPacket(new PlayerInformationPacket(player, true));
            } else {
                socket.sendPacket(new ResponsePacket(Messages.NO_ENOUGH_MONEY));
                return;
            }
        }

        try(FileWriter writer = new FileWriter(SaveManager.SAVE_PLAYER_FOLDER + "/" + player.getUsername() + ".json")) {
            Main.gson.toJson(player, writer);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
