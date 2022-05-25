package com.casino.entity;

import com.casino.enums.Games;

import java.util.UUID;

public class GameInfo {

    private String name;
    private Games game;
    private int playerInGame;
    private int maxPlayer;
    private boolean isStarted;
    private int timeBeforeStart;
    private long createdAt;
    private UUID gameId;

    public GameInfo(UUID gameId, String name, Games game, int playerInGame, int maxPlayer, boolean isStarted, int timeBeforeStart, long createdAt) {
        this.gameId = gameId;
        this.name = name;
        this.game = game;
        this.playerInGame = playerInGame;
        this.maxPlayer = maxPlayer;
        this.isStarted = isStarted;
        this.timeBeforeStart = timeBeforeStart;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public Games getGame() {
        return game;
    }

    public int getPlayerInGame() {
        return playerInGame;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public int getTimeBeforeStart() {
        return timeBeforeStart;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public UUID getGameId() {
        return gameId;
    }
}
