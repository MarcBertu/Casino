package com.casino.entity;

import com.casino.enums.Color;
import com.casino.enums.Value;


public class Card {

    private Value value;
    private Color color;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }
}
