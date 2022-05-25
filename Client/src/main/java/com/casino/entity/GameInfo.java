package com.casino.entity;

import com.casino.enums.Games;
import org.json.JSONObject;

import java.util.UUID;

public class GameInfo {

    public enum GameInfoEnum {

        NAME("name"),
        GAME("game"),
        PLAYER_IN_GAMES("playerInGame"),
        MAX_PLAYERS("maxPlayer"),
        IS_STARTED("started"),
        TIME_BEFORE_START("timeBeforeStart"),
        CREATE_AT("createdAt"),

        GAME_ID("gameId");

        private String value;

        GameInfoEnum(String value) {
            this.value = value;
        }


    }

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

    @Override
    public String toString() {

        String statut = "";

        if (isStarted) {
            statut = "En cours";
        }
        else {
            statut = "Prête";
        }

        return name + "\t" + "\t" + "Joueurs: " + playerInGame + "/" + maxPlayer + "\t" + "\t" + "Début dans : " + timeBeforeStart + "\t" + "Statut: " + statut;
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

    public static GameInfo initFrom( JSONObject object ) {

        String name = object.getString(GameInfoEnum.NAME.value);

        Games game = Games.valueOf(object.getString(GameInfoEnum.GAME.value));

        int playerInGame = object.getInt(GameInfoEnum.PLAYER_IN_GAMES.value);

        int maxPlayer = object.getInt(GameInfoEnum.MAX_PLAYERS.value);

        boolean isStarted = object.getBoolean(GameInfoEnum.IS_STARTED.value);

        int timeBeforeStart = object.getInt(GameInfoEnum.TIME_BEFORE_START.value);

        long createAt = object.getLong(GameInfoEnum.CREATE_AT.value);

        UUID gameId = UUID.fromString( object.getString("gameId") );

        GameInfo gameInfo = new GameInfo(
                gameId, name, game, playerInGame, maxPlayer, isStarted, timeBeforeStart, createAt
        );

        return gameInfo;
    }
}
