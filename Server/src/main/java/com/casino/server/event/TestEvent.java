package com.casino.server.event;

import xyz.baddeveloper.lwsl.packet.Packet;
import xyz.baddeveloper.lwsl.server.SocketHandler;

public class TestEvent extends Event {

    @Override
    public void onPacketReceived(SocketHandler socket, Packet packet) {
        System.out.println("Packet reçu");
    }
}
