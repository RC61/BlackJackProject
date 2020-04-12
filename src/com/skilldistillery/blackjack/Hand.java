package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	protected List<Card> cards = new ArrayList<Card>();

	public void addCard(Card card) {
		getCards().add(card);
	}

	void showCards() {
		for (Card card : cards) {
			System.out.println(card);
		}
	}

	public void showOne() {
		System.out.println(getCards().get(0));
	}

	public abstract int getCardsValue();

	public void wipe() {
		setCards(new ArrayList<Card>());
	}

	public List<Card> getHand() {
		return cards;
	}

	public void setHand(List<Card> hand) {
		this.cards = hand;
	}

	@Override
	public String toString() {
		return "[  " + cards + "  ]";
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<Card> getCards() {
		return cards;
	}
}
