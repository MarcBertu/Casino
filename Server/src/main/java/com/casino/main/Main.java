package com.casino.main;

import com.casino.entity.Player;
import com.casino.game.Blackjack;
import com.casino.game.GameManager;
import com.casino.save.SaveManager;
import com.casino.server.Server;
import com.casino.server.event.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SocketHandler;

public class Main {

    public static SaveManager sm = new SaveManager();
    public static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    public static Server server;
    public static GameManager gm;

    public static List<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        server = new Server();

        //Add event
        server.addEvent(new TestEvent());
        server.addEvent(new RegisterEvent());
        server.addEvent(new LoginEvent());
        server.addEvent(new AskInfoEvent());
        server.addEvent(new MoneyEvent());
        server.addEvent(new JoinStatusEvent());

        gm = new GameManager();
        gm.run();

    }

    public static Player getPlayer(Socket socket) {
        for (Player player : Main.players) {
            if (player.getSocket().getSocket().equals(socket)) {
                return player;
            }
        }
        return null;
    }

}
