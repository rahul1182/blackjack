package com.casino.game.blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList <Card> cards;

    public Deck() {
        super();
        this.cards = new ArrayList <Card>();
    }


    public void createFullDeck() {
        for (Suit cardSuit : Suit.values()) {
            for (SuitValue cardValue : SuitValue.values()) {
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public String toString() {
        String cardListOutput = "";
        for (Card card : this.cards) {
            cardListOutput += "\n " + card.toString();
        }
        return cardListOutput;
    }

    public void shuffle() {
        ArrayList <Card> tmpDck = new ArrayList <Card>();
        Random rdm = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for (int i = 0; i < originalSize; i++) {
            randomCardIndex = rdm.nextInt((this.cards.size() - 1 - 0) + 1) + 0;
            tmpDck.add(this.cards.get(randomCardIndex));
            this.cards.remove(randomCardIndex);
        }

        this.cards = tmpDck;
    }

    public void removeCard(int i) {
        this.cards.remove(i);
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public void addCard(Card addCard) {
        this.cards.add(addCard);
    }

    // Draws cards from the Deck

    public void draw(Deck comingFrom) {
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;

        for (Card card : this.cards) {
            if (card.getSuitValue().equals(SuitValue.ACE)) {
                aces++;
                if (aces == 1) {
                    totalValue += 11;
                } else {
                    totalValue += 1;
                }
            } else {
                totalValue += card.getSuitValue().value;
            }
        }
        return totalValue;
    }

    public int deckSize() {
        return this.cards.size();
    }

    public void moveAllToDec(Deck moveTo) {
        int thisDeckSize = this.cards.size();
        for (int i = 0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }

        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }
    }

}
