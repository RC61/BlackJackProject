package com.skilldistillery.blackjack;

import java.util.Scanner;

import com.skilldistillery.playerpackage.Dealer;
import com.skilldistillery.playerpackage.UserPlayer;

public class Table {
	private Scanner kb = new Scanner(System.in);
	private Dealer house = new Dealer();
	private UserPlayer player1 = new UserPlayer();
	private Deck deck = new Deck();
	private BlackjackHand bjh = new BlackjackHand();

	public void startGame() {

		openingSequence();

		while (playOrQuit()) {
			shuffleTime();
			dealTheCards();
			if (player1.getHand().twentyOne()) {
				playerBlackjack();
				continue;
			}
			hitOrStand();
			if (player1.getHand().busted()) {
				continue;
			}
			itsHouseTime();
//			if (house.getHand().twentyOne()) {
//				continue;
//			}
//			if (house.getHand().busted()) {
//				continue;
//			}
			checkResults();
			bjh.wipe();
		}
	}

	public void shuffleTime() {
		iAmFourLines();
		System.out.println("Shuffling...");
		house.dealerShuffle();
		System.out.println("Done");
	}

	public void openingSequence() {
		System.out.println("Welcome to the table");
		System.out.print("What is your name, friend? --> ");
		String name = kb.next();
		System.out.println(name + " you say? LOL Yeah, I don't think so.");
		System.out.println("Your new name is Player 1");
		System.out.println();

	}

	public boolean playOrQuit() {
		System.out.println("Player1, please press 1 to Play or 0 to quit");
		player1.getHand().wipe();
		house.getHand().wipe();
		int userChoice = kb.nextInt();

		switch (userChoice) {
		case 1:
			System.out.println("Here we go");
			return true;
		case 0:
			System.out.println("Exiting program");
			System.exit(0);
			return false;
		default:
			System.out.println("Ok smartass, we're playing");
			return true;
		}

	}

	public void dealTheCards() {
		System.out.println();
		System.out.println("Dealing cards...");
		System.out.println("Done");
		System.out.println();
		house.dealToPlayer(player1);
		house.dealDealer(house.getDeck().dealCard());
		house.dealToPlayer(player1);
		house.dealDealer(house.getDeck().dealCard());
		house.showDealerHand1();
		System.out.println("~~~~~~~~~~~~~~");
		player1.peepCards();

	}

	public void hitOrStand() {
		int keepGoing = 0;
		System.out.println();

		do {
			System.out.println("Player1, stand(0) or hit(1)?");
			keepGoing = kb.nextInt();

			switch (keepGoing) {
			case 0:
				System.out.println("Player1 stands");
				break;
			case 1:
				house.dealToPlayer(player1);
				System.out.println("Player1 hits");
				player1.peepCards();
				break;
			}

			if (!checkPlayerForWinOrLoss()) {
				break;
			}

		} while (keepGoing == 1);
	}

	public void itsHouseTime() {
		System.out.println("Dealer has");
		System.out.println();
		house.dealerShowMeAll();
		
		if (house.getHand().getCardsValue() == 21) {
			System.out.println("House has 21");
			System.out.println();
		}

		while ((house.getHand().getCardsValue() < 17
				|| house.getHand().getCardsValue() <= player1.getHand().getCardsValue())) {
			if (house.getHand().getCardsValue() > player1.getHand().getCardsValue()) {
				break;
			}
//			if (player1.getHand().twentyOne()) {
//				break;
//			}
			if (player1.getHand().busted()) {
				System.out.println("Boo hoo for you, Player1");
				break;
			}
			if (house.getHand().getCardsValue() < 17) {
				System.out.println("Dealer hits");
				System.out.println();
				house.dealDealer(house.getDeck().dealCard());
				house.dealerShowMeAll();
				checkHouseForWinOrLoss();
				if (house.getHand().busted() || house.getHand().twentyOne()) {
					break;
				}
			} else if (house.getHand().getCardsValue() >= 17) {
				System.out.println();
				System.out.println("Dealer stands");
				break;
			}
		}

	}

	public boolean checkPlayerForWinOrLoss() {
		while (true) {

			if (player1.getHand().busted()) {
				playerOneBusted();
				return false;

			} else if (player1.getHand().twentyOne()) {
				playerOneTwoOne();
				return false;

			} else {
				return true;
			}
		}

	}

	public void checkResults() {
//		checkHouseForWinOrLoss();
//		checkPlayerForWinOrLoss();

		if (player1.getHand().getCardsValue() == house.getHand().getCardsValue()) {
			pushIt();
		} else if (player1.getHand().getCardsValue() > house.getHand().getCardsValue() || house.getHand().busted()) {
			playerOneHasWon();
		} else if (player1.getHand().getCardsValue() < house.getHand().getCardsValue() || player1.getHand().busted()) {
			theHouseHasWon();
		} else {
			System.out.println("Call security");
		}

	}

	public boolean checkHouseForWinOrLoss() {
		while (true) {

			if (house.getHand().busted()) {
				houseBusted();
				return false;

			} else if (house.getHand().twentyOne()) {
				houseTwoOne();
				return false;

			} else {
				return true;
			}
		}

	}

	public void iAmFourLines() {
		System.out.println("=====================================");
		System.out.println("=====================================");
	}
	
	public void playerBlackjack() {
		System.out.println();
		System.out.println("XXXXXXXXXXXXXXXXXXX");
		System.out.println("X !!BLACKJACK!!   X");
		System.out.println("X    PLAYER1 WINS X");
		System.out.println("X !!BLACKJACK!!   X");
		System.out.println("X    PLAYER1 WINS X");
		System.out.println("XXXXXXXXXXXXXXXXXXX");
		System.out.println();
	}
	
	public void theHouseHasWon() {
		System.out.println();
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println("X                X");
		System.out.println("X                X");
		System.out.println("X   HOUSE WINS   X");
		System.out.println("X                X");
		System.out.println("X                X");
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println();
	}
	
	public void playerOneHasWon() {
		System.out.println();
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println("X                X");
		System.out.println("X                X");
		System.out.println("X    YOU WIN!    X");
		System.out.println("X                X");
		System.out.println("X                X");
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println();
	}
	public void pushIt() {
		System.out.println();
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println("X                X");
		System.out.println("X      PUSH      X");
		System.out.println("X                X");
		System.out.println("X                X");
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println();
	}
	
	public void houseBusted() {
		System.out.println();
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println("X                X");
		System.out.println("X                X");
		System.out.println("X  HOUSE BUSTED! X");
		System.out.println("X                X");
		System.out.println("X                X");
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println();
	}
	public void houseTwoOne() {
		System.out.println();
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println("X                X");
		System.out.println("X  HOUSE HAS 21  X");
		System.out.println("X    !UH OH!     X");
		System.out.println("X                X");
		System.out.println("X                X");
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println();
	}
	
	public void playerOneBusted() {
		System.out.println();
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println("X                X");
		System.out.println("X                X");
		System.out.println("X   YOU  BUSTED  X");
		System.out.println("X                X");
		System.out.println("X                X");
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println();
	}
	
	public void playerOneTwoOne() {
		System.out.println();
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println("X 21 21 21 21 21 X");
		System.out.println("X21 21 21 21 21  X");
		System.out.println("X  21 21 21 21 21X");
		System.out.println("X 21 21 21 21 21 X");
		System.out.println("XXXXXXXXXXXXXXXXXX");
		System.out.println();
	}

}
