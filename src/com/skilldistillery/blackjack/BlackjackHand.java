package com.skilldistillery.blackjack;

import com.skilldistillery.blackjack.Card;

public class BlackjackHand extends Hand {

	@Override
	public void wipe() {
		super.wipe();
	}

	public BlackjackHand() {
		super();
	}

	@Override
	public int getCardsValue() {

		int cardValue = 0;
		for (Card card : getCards()) {
			cardValue = cardValue + card.getValue();
		}
		return cardValue;
	}

	public boolean twentyOne(){
		if (this.getCardsValue() == 21){
			return true;
		}else {
			return false;
		}
	}

	public boolean busted() {
		if (this.getCardsValue() > 21) {
			return true;
		} else {
			return false;

		}
	}
}
