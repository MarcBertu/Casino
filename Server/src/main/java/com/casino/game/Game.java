package com.casino.game;

import com.casino.entity.Hand;
import com.casino.enums.Games;
import com.casino.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public abstract class Game {

    private final UUID gameId;
    private String name;
    private List<Player> players = new ArrayList<>();
    private HashMap<Player, Hand> playerCard = new HashMap<Player, Hand>();

    public Game() {
        this.gameId = UUID.randomUUID();
        this.name = this.gameId.toString();
    }

    public Game(String name) {
        this();
        this.name = name;
    }

    public UUID getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerName) {
        this.players.removeIf(player -> player.getUsername().equals(playerName));
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Get game Type
     * @return
     */
    abstract Games getType();

    /**
     * Execute game
     */
    abstract void run();



}
