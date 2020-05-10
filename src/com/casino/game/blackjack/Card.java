package com.casino.game.blackjack;

public class Card {

    private Suit suit;
    private SuitValue suitValue;


    public Card(Suit suit, SuitValue suitValue) {
        super();
        this.suit = suit;
        this.suitValue = suitValue;
    }


    @Override
    public String toString() {
        return "Card [suit=" + suit + ", suitValue=" + suitValue + "]";
    }


    public SuitValue getSuitValue() {
        return suitValue;
    }


}
