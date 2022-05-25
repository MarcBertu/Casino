package com.casino.game;

import com.casino.entity.Deck;
import com.casino.entity.Player;
import com.casino.enums.Games;

public class Blackjack extends Game {

    private GameManager gm;

    private boolean isGameStart = false;

    private int deckNumber = 1;

    public Blackjack(GameManager gm) {
        this.gm = gm;
        this.maxPlayer = 5;
        this.start();
    }

    public Games getType() {
        return Games.BLACKJACK;
    }

    @Override
    public void run() {
        preGameCounter = 120;
        for (int i = 0; preGameCounter > 0; i++) {
            decrementPreGame();
            System.out.println(this.getGameId() + " " + preGameCounter);
        }

        if (this.getPlayers().size() < 2) {
            System.out.println("Pas assez de joueur, arret de la partie");
            this.gm.getGames().remove(this);
            this.gm.run();
            this.interrupt();
            return;
        }

        this.isGameStart = true;

        Deck deck = new Deck(deckNumber);
        deck.shuffle();

        for (Player player : getPlayers()) {

        }

    }

    private void decrementPreGame() {
        preGameCounter--;

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
