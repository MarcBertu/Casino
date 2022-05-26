package com.casino.enums;

public enum Messages {

    LOGIN_SUCCESS("Utilisateur connecté"),
    LOGIN_ERROR("Utilisateur n'existe pas"),
    WRONG_PASSWORD("Mot de passe incorrect"),
    REGISTER_ERROR("Utilisateur déjà existant"),
    REGISTER_SUCCESS("Utilisateur enregistré"),

    JOIN_SUCCESS(""),

    GAME_FULL("La partie est pleine"),

    JOIN_ERROR("Impossible de rejoindre la partie !"),

    NO_ENOUGH_MONEY("Vous n'avez pas assez d'argent !");

    String msg;
    Messages(String msg)
    {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public static Messages fromString(String text) {
        for (Messages b : Messages.values()) {
            if (b.name().equals(text)) {
                return b;
            }
        }
        return null;
    }
}
