package edu.dcccd;

import java.util.Random;

public class Main2 {
    Random random = new Random(System.currentTimeMillis());
    public static void main( String[] args ) {
        new Main2().go();
    }

    private void go() {
        Card card2;
        Card card = new Card(getRandomInt(Card.allRanks.length),
                             getRandomInt(Card.allSuits.length));
        do card2 = new Card(getRandomInt(Card.allRanks.length),
                            getRandomInt(Card.allSuits.length));
        while (card.equalTo(card2));

        System.out.println("The " + card + " is " +
        (!card.lessThan(card2) ? "not ":"") + "less than the " + card2 + ".");

        for ( int i = 0; i < Card.DECK_COUNT; i++ ) {
            int r = i % 13;
            int s = i / 13;
            System.out.println(new Card(r, s));
        }
    }

    private int getRandomInt( int bound ) {
        return random.nextInt(bound);
    }
}
