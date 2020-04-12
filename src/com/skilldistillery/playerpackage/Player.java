package com.skilldistillery.playerpackage;

import com.skilldistillery.blackjack.BlackjackHand;
import com.skilldistillery.blackjack.Hand;

public abstract class Player {

	private BlackjackHand hand = new BlackjackHand();

	public void peepCards() {

	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(BlackjackHand hand) {
		this.hand = hand;
	}

	@Override
	public String toString() {
		return "Player [hand=" + hand + "]";
	}

}
