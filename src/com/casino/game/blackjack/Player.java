package com.casino.game.blackjack;

public class Player {

    private String name;
    private int gameMoney;
    private String strategy;
    private int wins;
    private Deck playerDeck=new Deck();

    private double currentBet=0.0;

    public Player() {
        super();
        this.name = name;
        this.gameMoney = 100;
        this.strategy = strategy;
        this.wins = 0;
        this.playerDeck = playerDeck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGameMoney() {
        return gameMoney;
    }

    public void setGameMoney(int gameMoney) {
        this.gameMoney = gameMoney;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public Deck getPlayerDeck() {
        return playerDeck;
    }

    public void setPlayerDeck(Deck playerDeck) {
        this.playerDeck = playerDeck;
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(double currentBet) {
        this.currentBet = currentBet;
    }

    public void gotBlackJack(){
        this.gameMoney+= currentBet * 1.5;
        this.wins++;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", gameMoney=" + gameMoney + ", strategy=" + strategy + ", wins=" + wins
                + "]";
    }


}