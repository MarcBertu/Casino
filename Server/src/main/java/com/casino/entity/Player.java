package com.casino.entity;


import com.google.gson.annotations.Expose;
import xyz.baddeveloper.lwsl.server.SocketHandler;

import java.util.ArrayList;
import java.util.List;

public class Player {

    @Expose
    private String username;
    @Expose
    private String password;

    @Expose
    private int score;

    @Expose
    private int money;

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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
