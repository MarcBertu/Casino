package com.casino.enums;

public enum Color {

    HEART("Heart", 1),
    SPADE("Spade", 0),
    DIAMOND("Diamond", 1),
    CLUB("Club", 0);

    String name;

    // 0: Black, 1: Red
    int color;
    Color(String name, int color) {
        if (color < 0) {
            this.color = 0;
        } else if (color > 1) {
            this.color = 1;
        } else {
            this.color = color;
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}
