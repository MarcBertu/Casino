package com.casino.main;

import com.casino.server.Server;
import com.casino.server.event.TestEvent;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();

        //Add event
        server.addEvent(new TestEvent());

        //Load event
        server.loadEvent();

    }

}
