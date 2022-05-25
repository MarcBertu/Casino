package com.casino.game;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GameManager{

    private List<Game> games = new ArrayList<>();

    public GameManager() {
    }

    public void run() {
        while(games.size() < 3) {
            games.add(new Blackjack(this));
            System.out.println(games.size());
        }
    }

    public List<Game> getGames() {
        return games;
    }

    public Optional<Game> getGame(UUID uuid) {
        return games.stream().filter(game -> game.getGameId().equals(uuid)).findFirst();
    }
}
