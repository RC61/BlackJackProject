package com.skilldistillery.playerpackage;

import com.skilldistillery.blackjack.BlackjackHand;
import com.skilldistillery.blackjack.Card;
import com.skilldistillery.blackjack.Deck;
import com.skilldistillery.blackjack.Hand;

public class Dealer extends Player {
	private Deck deck;
	private BlackjackHand hand;

	public Dealer() {
		deck = new Deck();
		hand = new BlackjackHand();
	}

	public void dealToPlayer(UserPlayer player1) {
		player1.hitMe(deck.dealCard());
	}

	public void dealDealer(Card card) {
		hand.addCard(deck.dealCard());
	}

	public Hand dealerShowCards() {
		return hand;
	}

	public BlackjackHand getHand() {
		return hand;
	}

	public void dealerShowMeAll() {
		System.out.println(hand.toString());
	}

	public void dealerShuffle() {
		deck.shuffle();
	}

	public void newHand() {
		hand = new BlackjackHand();
	}

	public Deck getDeck() {
		return deck;
	}

	public void showDealerHand1() {
		System.out.println("Dealer has...");
		System.out.print("1 card upsidedown & ");
		hand.showOne();
	}

	public void dealerHit(Card card) {
		dealerHit(deck.dealCard());
	}

}
