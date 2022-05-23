package com.casino.game;


import java.util.ArrayList;
import java.util.List;

public class GameManager implements Runnable{

    List<Game> games = new ArrayList<Game>();

    public GameManager() {

    }

    @Override
    public void run() {
        while(true) {
            if (games.size() < 3) {
                games.add(new Blackjack());
            }
        }
    }
}
