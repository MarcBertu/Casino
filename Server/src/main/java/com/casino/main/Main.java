package com.casino.main;

import com.casino.save.SaveManager;
import com.casino.server.Server;
import com.casino.server.event.LoginEvent;
import com.casino.server.event.RegisterEvent;
import com.casino.server.event.TestEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static SaveManager sm = new SaveManager();
    public static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    public static void main(String[] args) {
        Server server = new Server();

        //Add event
        server.addEvent(new TestEvent());
        server.addEvent(new RegisterEvent());
        server.addEvent(new LoginEvent());


    }

}
