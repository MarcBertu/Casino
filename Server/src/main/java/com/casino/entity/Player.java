package com.casino.entity;


import xyz.baddeveloper.lwsl.server.SocketHandler;

public class Player {

    private String username;
    private String password;

    private int score;

    private SocketHandler socket;

    public Player(String username, String password, SocketHandler socket) {
        this.username = username;
        this.password = password;
        this.socket = socket;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public SocketHandler getSocket() {
        return socket;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSocket(SocketHandler socket) {
        this.socket = socket;
    }
}
