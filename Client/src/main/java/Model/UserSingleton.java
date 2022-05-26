package Model;

import com.casino.entity.GameInfo;

public class UserSingleton {

    private String pseudo = "";

    static UserSingleton instance = null;

    private int mise = 0;

    private int argent = 0;

    private GameInfo game = null;

    private UserSingleton() {

    }

    public static UserSingleton getInstance() {
        if(instance==null) {
            instance = new UserSingleton();
        }
        return instance;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getMise() {
        return mise;
    }

    public void setMise(int mise) {
        this.mise = mise;
    }

    public int getArgent() {
        return argent;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public GameInfo getGame() {
        return game;
    }

    public void setGame(GameInfo game) {
        this.game = game;
    }
}
