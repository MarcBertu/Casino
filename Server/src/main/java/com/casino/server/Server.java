package com.casino.server;

import xyz.baddeveloper.lwsl.server.SocketServer;
import xyz.baddeveloper.lwsl.server.events.OnPacketReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private SocketServer socketServer;
    private List<OnPacketReceivedEvent> events = new ArrayList<OnPacketReceivedEvent>();

    public Server() {
        socketServer = new SocketServer(25566)
                .setMaxConnections(0) // 0 for infinite
                .addConnectEvent(socket -> System.out.println(String.format("Client connected! (%s)", socket.toString())))
                .addDisconnectEvent(socket -> System.out.println(String.format("Client disconnected! (%s)", socket.toString())))
                .addPacketReceivedEvent((socket, packet) -> System.out.println(String.format("Packet received! (%s)", packet.getObject().toString())))
                .addReadyEvent(socketServ -> System.out.println(String.format("Socket server is ready for connections! (%s)", socketServ.getServerSocket().toString())))
                .addPacketSentEvent(((socketHandler, packet) -> System.out.println(String.format("Packet sent! (%s)", packet.getObject().toString()))));
        socketServer.start();
    }

    public void addEvent(OnPacketReceivedEvent event) {
        this.events.add(event);
    }

    public void removeEvent(OnPacketReceivedEvent event) {
        this.events.remove(event);
    }

    public void loadEvent() {
        for (OnPacketReceivedEvent event : events) {
            socketServer.addPacketReceivedEvent(event);
        }
    }

    public SocketServer getSocketServer() {
        return socketServer;
    }
}
