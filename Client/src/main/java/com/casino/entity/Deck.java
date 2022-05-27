package com.casino.entity;

import com.casino.enums.Color;
import com.casino.enums.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    List<Card> cards = new ArrayList<>();

    public Deck(int nb) {
        for (int i = 0; i < nb; i++) {
            //Heart
            for (Value value : Value.values()) {
                cards.add(new Card(Color.HEART, value));
            }

            //Spade
            for (Value value : Value.values()) {
                cards.add(new Card(Color.SPADE, value));
            }

            //Diamond
            for (Value value : Value.values()) {
                cards.add(new Card(Color.DIAMOND, value));
            }

            //Club
            for (Value value : Value.values()) {
                cards.add(new Card(Color.CLUB, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card getCard(int index){
        return this.cards.remove(index);
    }

    public Card drawCard() {
        return this.cards.remove(0);
    }

    public void insertCard(Card card) {
        this.cards.add(card);
    }



}
