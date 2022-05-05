package com.casino.packet;

import xyz.baddeveloper.lwsl.packet.Packet;

public class RegisterPacket extends Packet {

    public RegisterPacket(String username, String password){
        getObject().put("packetId", "register");
        getObject().put("username", username);
        getObject().put("password", password);
    }
}
