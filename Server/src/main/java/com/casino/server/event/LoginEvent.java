package com.casino.server.event;

import com.casino.entity.Messages;
import com.casino.entity.Player;
import com.casino.main.Main;
import com.casino.packet.ResponsePacket;
import com.casino.save.SaveManager;
import xyz.baddeveloper.lwsl.packet.Packet;
import xyz.baddeveloper.lwsl.server.SocketHandler;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoginEvent extends Event{
    @Override
    public void onPacketReceived(SocketHandler socket, Packet packet) {
        if (!packet.getObject().getString("packetId").equals("login")) {
            return;
        }

        String username = packet.getObject().getString("username");
        String password = packet.getObject().getString("password");

        if (Main.sm.getPlayerSave(username) == null) {
            socket.sendPacket(new ResponsePacket(Messages.LOGIN_ERROR));
        }

        try {
            Player player = Main.gson.fromJson(new FileReader(SaveManager.SAVE_PLAYER_FOLDER + "/" + username + ".json"), Player.class);

            if (!player.getPassword().equals(password))
            {
                socket.sendPacket(new ResponsePacket(Messages.WRONG_PASSWORD));
                return;
            }

            player.setSocket(socket);
            player.getSocket().sendPacket(new ResponsePacket(Messages.LOGIN_SUCCESS));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
