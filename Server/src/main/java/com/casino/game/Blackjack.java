package com.casino.game;

import com.casino.entity.Card;
import com.casino.entity.Deck;
import com.casino.entity.Hand;
import com.casino.entity.Player;
import com.casino.enums.Games;
import com.casino.enums.Value;
import com.casino.main.Main;
import com.casino.packet.BankPacket;
import com.casino.packet.PlayerInformationPacket;
import com.casino.save.SaveManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Blackjack extends Game {

    private GameManager gm;

    private boolean isGameStart = false;

    private int deckNumber = 1;

    private List<Card> bankCards = new ArrayList<>();

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
        preGameCounter = 30;
        for (int i = 0; preGameCounter > 0; i++) {
            decrementPreGame();
            System.out.println(this.getGameId() + " " + preGameCounter);
        }

        this.isGameStart = true;

        Deck deck = new Deck(deckNumber);
        deck.shuffle();

        if (!getPlayers().isEmpty()) {
            //Timer Récupération des mises
            for (miseTimer = 30; miseTimer > 0; miseTimer--) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            //Les joueurs sans mise sont kick
            List<Player> toRemove = new ArrayList<>();
            for (Player player : getPlayers()) {
                if (!getPlayerMises().containsKey(player)) {
                    toRemove.add(player);
                }
            }

            getPlayers().removeAll(toRemove);

            //Distribue les cartes
            for (int i = 0; i < 2; i++) {
                for (Player player : getPlayers()) {
                    if (getPlayerCard().containsKey(player)) {
                        getPlayerCard().get(player).addCard(deck.drawCard());
                    } else {
                        Hand hand = new Hand();
                        hand.addCard(deck.drawCard());
                        getPlayerCard().put(player, hand);
                    }
                }
            }
            //La banque pioche une carte
            bankCards.add(deck.drawCard());

            //On envoie les cartes aux joueurs
            for (Player player : getPlayers()) {
                player.getSocket().sendPacket(new BankPacket(bankCards));
                for (Player player2 : getPlayers()) {
                    player.getSocket().sendPacket(new PlayerInformationPacket(player2, this));
                }
            }

            //On check la valeur des cartes des joueurs
            int value = 0;
            for (Player player : getPlayers()) {
                boolean hasAs = false;
                for (Card card : getPlayerCard().get(player).getCards()) {
                    if (card.getValue().equals(Value.AS)) {
                        if (!hasAs) {
                            hasAs = true;
                            value += 11;
                        } else {
                            value += 1;
                        }
                    } else {
                        value += Math.max(card.getValue().getValue(), 10);

                        if (hasAs && value > 21) {
                            value -= 10;
                        }
                    }
                }

                if (value == 21) {
                    player.setMoney(player.getMoney() + getPlayerMises().get(player) * 2);
                    getPlayerMises().remove(player);
                    player.getSocket().sendPacket(new PlayerInformationPacket(player, null));
                    
                    try(FileWriter writer = new FileWriter(SaveManager.SAVE_PLAYER_FOLDER + "/" + player.getUsername() + ".json")) {
                        Main.gson.toJson(player, writer);
                        writer.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (value > 21) {
                    //TODO Perdu
                } else {
                    //TODO Faut piocher
                }
            }

//            while (!getPlayers().isEmpty()) {
//
//            }
        }

        System.out.println("Pas assez de joueur, arret de la partie");
        this.stopGame();

    }

    private void stopGame() {
        this.gm.getGames().remove(this);
        this.gm.run();
        this.interrupt();
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
