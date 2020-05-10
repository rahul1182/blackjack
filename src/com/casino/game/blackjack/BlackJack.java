package com.casino.game.blackjack;

import java.util.List;
import java.util.Scanner;

public class BlackJack {
    private List <Player> players;
    private Player dealer;
    private Deck playingDeck;

    private boolean gameOn = true;

    public BlackJack() {
        playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();
        Player dealer = new Player();
        dealer.setName("Dealer");
        this.dealer = dealer;
    }

    public List <Player> getPlayers() {
        return players;
    }

    public void setPlayers(List <Player> players) {
        this.players = players;
    }

    public Player getDealer() {
        return dealer;
    }

    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public Deck getPlayingDeck() {
        return playingDeck;
    }

    public void setPlayingDeck(Deck playingDeck) {
        this.playingDeck = playingDeck;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }


    public void dealCards(final int i) {
        int notInGame = 0;
        for (int j = 0; j < players.size(); j++) {
            Player player = players.get(j);
            if (player.getWins() < 5 && player.getGameMoney() > 0) {
                player.getPlayerDeck().draw(playingDeck);
                if (i == 0) {
                    player.getPlayerDeck().draw(playingDeck);
                }
                System.out.println(player.getName() + "'s Hands : ");
                System.out.println(player.getPlayerDeck().toString());
                System.out.println(player.getName() + " Hand is valued at " + player.getPlayerDeck().cardsValue());

            } else {
                notInGame++;
                System.out.println(player.getName() + " is not playing the Game No money Left for Bet");
                //players.remove(player);
                if (player.getGameMoney() <= 0 && players.size() == 1) {
                    System.out.println(player.getName() + " is Out of Game Please Try Again...");
                    System.exit(0);
                }
                if (player.getGameMoney() <= 0 && players.size() > 1) {
                    int sum = players.stream().filter(o -> o.getGameMoney() > 0).mapToInt(o -> o.getGameMoney()).sum();
                    if (sum == 0 || sum < 0) {
                        System.out.println(player.getName() + " and All Other Players are Out of Game since No money for Betting Please Try Again...");
                        System.exit(0);
                    }
                }
            }
        }
        if (notInGame == players.size()) {
            gameOn = false;
        }
        if (gameOn) {
            dealer.getPlayerDeck().draw(playingDeck);
            if (i == 0) {
                dealer.getPlayerDeck().draw(playingDeck);
            }
            System.out.println("Dealer Hands: " + dealer.getPlayerDeck().getCard(0) + " [Hidden]");
        }
    }

    public void checkBets(Scanner userInput) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player.getWins() < 5 && player.getGameMoney() > 0) {
                System.out.println(player.getName() + " You Have $" + player.getGameMoney() + " How much money you would like to bet for Current Game");
                double playerBet = userInput.nextDouble();
                player.setCurrentBet(playerBet);
                if (playerBet > player.getGameMoney()) {
                    System.out.println("You Cannot Bet more than the money available to you.");
                    playerBet = player.getGameMoney();
                    player.setCurrentBet(playerBet);
                    System.exit(0);
                }
            } else {
                // System.out.println(player.getName() + " is not playing the Game No money Left for Bet");
            }
        }
    }

    public boolean playGame(int i, Scanner userInput) {
        boolean endRound = false;
        Player player = players.get(i);
        Deck playerDeck = player.getPlayerDeck();
        String strategy = player.getStrategy();
        if (playerDeck.cardsValue() == 21) {
            System.out.println(player.getName() + " Got a Black Jack Yoo Yoo...You Wins and Hand's At" + playerDeck.cardsValue());
            player.gotBlackJack();
            endRound = true;
            return endRound;
        }
        System.out.println("\n");
        if (player.getGameMoney() > 0) {
            if (strategy.compareToIgnoreCase("c") == 0) {
                if (playerDeck.cardsValue() > 10) {
                    return getRoundsOutcome(player, playerDeck);
                } else {
                    System.out.println("Player " + player.getName() + " Would you like to (1)Hit or (2)Stand?");
                    int response = userInput.nextInt();
                    //They Hit
                    if (response == 1) {
                        playerDeck.draw(playingDeck);
                        System.out.println(player.getName() + " Draw a: " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
                        //BUst if >21
                        System.out.println(player.getName() + "'s Hands : ");
                        System.out.println(player.getPlayerDeck().toString());
                        System.out.println(player.getName() + " Hand is valued at " + player.getPlayerDeck().cardsValue());
                        System.out.println("\n");
                        if (playerDeck.cardsValue() > 21) {
                            System.out.println("You Busted. Currently valued at " + playerDeck.cardsValue());
                            //player.setGameMoney(player.getGameMoney()-(int)player.getCurrentBet());
                            endRound = true;
                            return true;
                        }
                    }
                    if (response == 2) {
                        System.out.println("You Stand's...");
                        return true;
                    }
                }
            } else {
                if (playerDeck.cardsValue() >= 18) {
                    return getRoundsOutcome(player, playerDeck);
                } else {
                    System.out.println("Player " + player.getName() + " Would you like to (1)Hit or (2)Stand?");
                    int response = userInput.nextInt();
                    //They Hit
                    if (response == 1) {
                        playerDeck.draw(playingDeck);
                        System.out.println(player.getName() + " Draws: " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
                        //Bust if >21
                        System.out.println(player.getName() + "'s Hands : ");
                        System.out.println(player.getPlayerDeck().toString());
                        System.out.println(player.getName() + " Hand is valued at " + player.getPlayerDeck().cardsValue());
                        if (playerDeck.cardsValue() > 21) {
                            System.out.println(player.getName() + " Busted. Currently valued at " + playerDeck.cardsValue());
                            //player.setGameMoney(player.getGameMoney()-(int)player.getCurrentBet());
                            endRound = true;
                            return endRound;
                        }
                    }
                    if (response == 2) {
                        System.out.println(player.getName() + " Stand's...");
                        return true;
                    }
                }
            }
        } else {
            return true;
        }
        return endRound;
    }

    private boolean getRoundsOutcome(Player player, Deck playerDeck) {
        boolean endRound;
        if (playerDeck.cardsValue() > 21) {
            System.out.println(player.getName() + " Busted. Currently valued at " + playerDeck.cardsValue());
            endRound = true;
        } else {
            System.out.println(player.getName() + " Stand's Automatically As per Your Default Game Strategy...");
        }
        return true;
    }

    public void resetDeck() {
        players.forEach(player -> player.setPlayerDeck(new Deck()));
        dealer.setPlayerDeck(new Deck());
    }

    public Deck dealerDraw() {
        dealer.getPlayerDeck().draw(playingDeck);
        System.out.println("Dealer Draws : " + dealer.getPlayerDeck().getCard(dealer.getPlayerDeck().deckSize() - 1).toString());
        return dealer.getPlayerDeck();
    }

    public Player checkWinner() {
        int min = 0;
        Player winner = null;
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            int playerCardValue = player.getPlayerDeck().cardsValue();
            if (playerCardValue <= 21 && playerCardValue > min) {
                min = playerCardValue;
                winner = player;
            }
        }
        if (min < dealer.getPlayerDeck().cardsValue() && dealer.getPlayerDeck().cardsValue() <= 21) {
            winner = dealer;
        }
        if (min == dealer.getPlayerDeck().cardsValue() && dealer.getPlayerDeck().cardsValue() <= 21) {
            winner = null;
        }
        return winner;
    }

    public void addMoney(Player player) {
        if (player != null) {
            for (int i = 0; i < players.size(); i++) {
                if (player.equals(players.get(i)) && players.get(i).getPlayerDeck().cardsValue() != 21) {
                    players.get(i).setGameMoney(players.get(i).getGameMoney() + (int) players.get(i).getCurrentBet());
                } else if (player.getName().equalsIgnoreCase("dealer")) {
                    players.get(i).setGameMoney(players.get(i).getGameMoney() - (int) players.get(i).getCurrentBet());
                } else if (players.get(i).getPlayerDeck().cardsValue() != 21) {
                    players.get(i).setGameMoney(players.get(i).getGameMoney() - (int) players.get(i).getCurrentBet());
                }
            }
        }
    }

    public boolean isEveryoneBusted(){
        int count=0;
        for(int i=0;i<players.size();i++) {
            int cardValue = players.get(i).getPlayerDeck().cardsValue();
            if (cardValue > 21) {
                count++;
            }
        }
            if(count==players.size()){
                return true;
            }
            return false;
    }
}
