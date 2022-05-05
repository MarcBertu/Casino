package com.casino.server.event;

import com.casino.entity.Player;
import com.casino.main.Main;
import com.casino.packet.ResponsePacket;
import com.casino.save.SaveManager;
import xyz.baddeveloper.lwsl.packet.Packet;
import xyz.baddeveloper.lwsl.server.SocketHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterEvent extends Event{
    @Override
    public void onPacketReceived(SocketHandler socket, Packet packet) {
        if (!packet.getObject().getString("packetId").equals("register")) {
            return;
        }

        String username = packet.getObject().getString("username");
        String password = packet.getObject().getString("password");

        if (Main.sm.getPlayerSave(username) != null) {
            socket.sendPacket(new ResponsePacket(true, "L'utilisateur existe déjà"));
        }

        Player player = new Player(username, password, socket);

        try(FileWriter writer = new FileWriter(SaveManager.SAVE_PLAYER_FOLDER + "/" + username + ".json")) {
            Main.gson.toJson(player, writer);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
