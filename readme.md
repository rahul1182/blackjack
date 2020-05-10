Blackjack technical problem implementation details


Simulation of the game of casino style Blackjack

Implemented this Application (BlackJackCasinoGame) in java which will simulate the Blackjack Game.

A card in a standard deck has a suit and a value. The suit may be one of the four following options: Club, Diamond, Heart, Spade. The value may be one of the following 13 options: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King

I have implemented a very simple version of blackjack with one player (for testing purpose) or for more than one player(n) at a time and one dealer.

The amount of money will be 100$ by default for each player, dealer has finite money. Players or dealer will play rounds of blackjack until either or all the player has no cash left or the player got 5 wins. 

Once amount is exhausted or wins becomes five for any user simulation will stop.

If players win betting amount will going to be added to their respective Game amount or if player lose, amount will be deducted, or if there is a Tie/Push/Draw no deduction or addition will occur.

Two Enums are created and five classes are created.

Game Strategy will be configured at time of player creation i.e. Conservative or Aggressive

Game Executer Class is the main entry point to kick off a game simulation.
