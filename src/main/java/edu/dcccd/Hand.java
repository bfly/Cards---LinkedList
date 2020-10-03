package edu.dcccd;

/* *
 * JAVA Advanced Professor William Fly
 * @This Hand Class is subclass that used performance the several task on Card Object.
 * @author Mohsen Shroaki
 * @version 9/20/2018
 */

import java.util.*;

public class Hand {
    // fields that point to the first index of each suit.
    private final int clubs;
    private int diamonds;
    private int hearts;
    private int spades;

    private final List<Card> hand;
    private ListIterator<Card> li;
    private Card card;

    //Constructor responsible to instantiate the List collection and data members.
    Hand() {
        hand = new LinkedList<>();
        clubs = diamonds = hearts = spades = 0;     //fingers
    }

    //newCard Method responsible to call add method to push the card in proper place
    //and increase the suits until finally get first index of each suit.
    void newCard(int rank, int suit){
        card = new Card(rank,suit);
        switch (Card.allSuits[card.suit().ordinal()]) {
            case Clubs -> {                       // clubs
                if(add(card, clubs))    diamonds++; hearts++; spades++;
            }
            case Diamonds -> {                    // diamonds
                if(add(card, diamonds))             hearts++; spades++;
            }
            case Hearts -> {                      // hearts
                if(add(card, hearts))                         spades++;
            }
            case Spades ->                        // spades
                add(card, spades);
        }
    }

    //iterate through list (hand) by starting from first index of each suit
    //push the card in hand depends on value that return from compareTo method.
    private boolean add(Card card, int from) {
        li = hand.listIterator(from);
        while(li.hasNext()) {
            Card current = li.next();
            if(card.compareTo(current) == 0) return false;
            if(card.compareTo(current) < 0) {
                hand.add(hand.indexOf(current), card);
                return true;
            }
        }
        hand.add(card);
        return true;
    }

    //playDown Method actually remove the card from the hand and return it to Main method to desplay it.
    //decrease the fingers index depends on suit that requested from upper method.
    Card playDown(int suit) {
        if(hand.size() == 0) return null;
        try {
            switch (Card.allSuits[suit]) {
                case Clubs ->    li = hand.listIterator(clubs);
                case Diamonds -> li = hand.listIterator(diamonds);
                case Hearts ->   li = hand.listIterator(hearts);
                case Spades ->   li = hand.listIterator(spades);
            }
        } catch (Exception e) {
            li = hand.listIterator(clubs);
        }

        if ( li.hasNext() ) card = li.next();
        else card = hand.get(clubs);
        switch (Card.allSuits[card.suit().ordinal()]) {
            case Clubs    -> {diamonds--; hearts--; spades--; }
            case Diamonds -> {            hearts--; spades--; }
            case Hearts   -> {                      spades--; }
            case Spades   -> {                                }
        }
        hand.remove(card);
        return card;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Card hold = hand.get(0);
        sb.append(String.format("%-10s%s", hold.suit(), ":"));
        for (Card card : hand) {
            if(card.suit() != hold.suit()) {
                sb.setLength(sb.length() - 2);
                sb.append("\n");
                hold = card;
                sb.append(String.format("%-10s%s", hold.suit(), ":"));
            }
            sb.append(Card.allRanks[card.rank().ordinal()]).append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    //return siz of current hand when it called.
    int getSize(){
        return hand.size();
    }

    //printFingers Method return the value of each fingers when it called.
    String printFingers() {
        return String.format("Fingers: %s: %d, %s: %d, %s: %d, %s: %d",
            Card.allSuits[0], clubs,  Card.allSuits[1], diamonds,
            Card.allSuits[2], hearts, Card.allSuits[3], spades);
    }

}
