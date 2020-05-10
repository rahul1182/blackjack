package com.casino.game.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unused")
public class GameExecuter {

    public static void main(String[] args) {
        int count = 0;
        try (Scanner userInput = new Scanner(System.in)) {
            System.out.println("Welcome to Black Jack Game...");

            BlackJack blackJack = new BlackJack();

            //Creating Player Config
            System.out.println("How many players wants to play BlackJack...Please enter Numeric Value...1-2-3-N");
            int noOfPlayers = userInput.nextInt();
            ArrayList <Player> players = new ArrayList <Player>();
            for (int i = 0; i < noOfPlayers; i++) {
                Player play = new Player();
                System.out.println("Please Enter Player Name...");
                String playerName = userInput.next();
                System.out.println("Please Enter Player Strategy...Conservative or Aggressive? Enter Either C or A...");
                String playerStrategy = userInput.next();
                Deck playerDeck = new Deck();
                play.setName(playerName);
                play.setStrategy(playerStrategy);
                players.add(play);
            }
            blackJack.setPlayers(players);
            System.out.println(players);
            //Create a Deck for the Player


            //looping Game Logic Below
            while (blackJack.isGameOn()) {
                boolean endRound = false;
                blackJack.checkBets(userInput);
                blackJack.dealCards(0);

                for (int i = 0; i < blackJack.getPlayers().size(); i++) {
                    while (true) {
                        if (blackJack.playGame(i, userInput)) {
                            break;
                        }
                    }
                }

                //Get Dealers Card now
                Deck dealerDeck = blackJack.getDealer().getPlayerDeck();
                System.out.println("Dealer Cards " + dealerDeck.toString());
                // check if dealer has more point than player
                Player winner = blackJack.checkWinner();
                if (winner == null) {
                    System.out.println("Push");
                    endRound = true;
                } else if (winner.getName().equalsIgnoreCase("dealer")) {
                    endRound = true;
                } else if (winner.getPlayerDeck().cardsValue() == 21) {
                    System.out.println(winner.getName() + " wins");
                    endRound = true;
                }

                //Dealer Draws at 16 and stands at 17
                while ((!blackJack.isEveryoneBusted()) && (dealerDeck.cardsValue() < 17) && endRound == false) {
                    dealerDeck = blackJack.dealerDraw();
                }

                //Display total value to Dealer

                System.out.println("Dealer's Hand is valued at: " + dealerDeck.cardsValue());

                winner = blackJack.checkWinner();
                if (winner == null) {
                    System.out.println("Push");
                } else {
                    System.out.println(winner.getName() + " wins");
                }
                blackJack.addMoney(winner);
                blackJack.resetDeck();

                System.out.println("---End of Hand.---");
            }

        } catch (Exception e) {
            count++;
            System.out.println("Wrong Input from User,Please Enter values as per Instruction..Run Application Again!");
        }
        if (count == 0)
            System.out.println("You Are out of Money Game Over or Wins 5 Games in Total");
    }


}
