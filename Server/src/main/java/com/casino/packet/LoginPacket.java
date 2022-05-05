package com.casino.packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class LoginPacket extends Packet {

    public LoginPacket(String username, String password) {
        getObject().put("packetId", "login");
        getObject().put("username", username);
        getObject().put("password", password);
    }
}
