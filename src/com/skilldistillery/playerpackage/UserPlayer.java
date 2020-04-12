package com.skilldistillery.playerpackage;

import com.skilldistillery.blackjack.BlackjackHand;
import com.skilldistillery.blackjack.Card;

public class UserPlayer extends Player {

	private BlackjackHand hand = new BlackjackHand();

	public UserPlayer() {
		hand = new BlackjackHand();
	}

	public BlackjackHand getHand() {
		return hand;
	}

	public void peepCards() {
		System.out.println("You have : " + hand.toString());
	}

	public void hitMe(Card card) {
		hand.addCard(card);
	}

	public boolean stand() {
		return false;
	}
}
