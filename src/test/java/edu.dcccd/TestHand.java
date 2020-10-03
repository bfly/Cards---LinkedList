package edu.dcccd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestHand {

	@BeforeEach
	void init() {}

	@Test
	void testHand() {
		Main main = new Main();
		Hand hand = main.loadHand();
		assertEquals(Card.DECK_COUNT, hand.getSize());
		main.discardHand();
		assertEquals(0, hand.getSize()); }
}
