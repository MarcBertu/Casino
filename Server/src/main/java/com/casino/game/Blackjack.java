package com.casino.game;

import com.casino.entity.Deck;
import com.casino.entity.Player;
import com.casino.enums.Games;

public class Blackjack extends Game {

    private int preGameCounter;
    private boolean isGameStart = false;

    private int deckNumber = 1;

    @Override
    public Games getType() {
        return Games.BLACKJACK;
    }

    @Override
    public void run() {
        preGameCounter = 120;
        for (int i = 0; preGameCounter > 0; i++) {
            decrementPreGame();
        }

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
