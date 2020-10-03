package edu.dcccd;

/* *
 * JAVA Advanced Professor William Fly
 * @This Main Class of maven Project that execute the program that arranging the Cards in hand and then play all them down.
 * @author Mohsen Shroaki
 * @version 9/20/2018
 */

import java.util.Random;

public class Main {
    //2 dimensional array used to avoid duplicate random card.
    private final boolean[][] cards = new boolean[Card.allSuits.length][Card.allRanks.length];
    Random random = new Random(System.currentTimeMillis());
    Hand hand;
    Card card;

    private void go(){

        // creating 52 random card until store all possible card.
        loadHand();

        // remove all of the cards from the hand
        discardHand();
    }

    Hand loadHand() {
        hand = new Hand();
        Card card;
        int i = 0;
        while (i < Card.allSuits.length * Card.allRanks.length) {
         int suit = random.nextInt(Card.allSuits.length);
         int rank = random.nextInt(Card.allRanks.length);
         if(!cards[suit][rank]) {
            hand.newCard(rank,suit);
            card = new Card(rank, suit);
            System.out.println((i+1) + ": " + card + " added to hand.");
            cards[suit][rank] = true;
            i++;
         }
        }
        System.out.println(hand);
        System.out.printf("Number of cards in hand: %d.\n", hand.getSize());
        System.out.println(hand.printFingers());
        return hand;
    }

    void discardHand() {
        int i = 0;
        while( i <= Card.allSuits.length * Card.allRanks.length) {
            int suit = random.nextInt(Card.allSuits.length);
            System.out.print((i +1) + ": " + Card.allSuits[suit] + " requested, ");
            card = hand.playDown(suit);
            if (card != null) System.out.println( card + " removed.");
            else System.out.println("No card found to remove.");
            i++;
        }
        System.out.printf("Number of cards in hand: %d.\n", hand.getSize());
        System.out.println(hand.printFingers());
    }

    public static void main(String [] args){
        new Main().go();
    }
}
