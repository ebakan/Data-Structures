package game;
import cards.*;
import players.*;
import java.util.Scanner;

/**
 * Simulates a game of BlackJack
 * @author Eric Bakan
 */
public class BlackJack {
	
	/**
	 * Plays a game of BlackJack
	 * @param args args
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to BlackJack\n");
		
		//initialize players
		for(int i=0;i<NUM_PLAYERS;i++)
			players[i]=new HumanPlayer();
		
		//play game until user wants to exit
		char answer;
		do {
			
			playRound();
			
			//get valid input for playing another round or not
			do {
				System.out.println("Would you like to play another round? (y/n)");
				answer=sc.next().trim().toLowerCase().charAt(0);
				if(answer!='y' && answer!='n')
					System.out.println("Please answer either y or n.");
			} while(answer!='y' && answer!='n');
			
		} while(answer!='n');
		
		//exit
		System.out.println("Thanks for playing!");
	}
	
	
	/**
	 * Plays one round of blackjack with each player
	 * and the dealer playing
	 */
	private static void playRound() {
		
		System.out.println("Dealing cards...\n");
		
		//gives each player a new hand
		for(HumanPlayer p: players)
			p.newHand(dealer.deal(2));
		
		//gives dealer new hand
		dealer.newHand(dealer.deal(2));
		
		//shows dealer's top card
		System.out.printf("Dealer's top card:\n%s\n\n",dealer.showTop());
		
		//plays each player's turn
		for(int i=0;i<NUM_PLAYERS;i++) {
			
			System.out.printf("Player %d's turn:\n",i+1);
			
			//play player's turn
			playerPlay(players[i]);
			
			System.out.println();
			
			//player's status at end of turn
			System.out.println(players[i]+"\n");
			
			//results of player's turn
			if(players[i].getVal()>21) System.out.println("Ouch, you busted.\n");
			else if(players[i].getVal()==21) System.out.println("You got a blackjack!\n");
		}
		
		//plays dealer's turn
		System.out.println("Dealer's turn:");
		dealerPlay();
		
		//results of round
		System.out.println("Results:");
		results();
		
	}
	
	/**
	 * Plays one turn of one HumanPlayer
	 * plays until player gets blackjack,
	 * goes bust, or wants to stay
	 * @param p HumanPlayer to play
	 */
	private static void playerPlay(HumanPlayer p) {
		boolean hitMe=false;
		do {
			System.out.println(p+"\n");
			if(p.getVal()<21) hitMe=p.wantHit();
			if(hitMe) p.add(dealer.deal());
		} while(p.getVal()<21 && hitMe);
		
	}
	
	/**
	 * Plays one dealer's turn
	 * Hits on 16 or less or soft 17
	 */
	private static void dealerPlay() {
		boolean hitMe=false;
		do {
			System.out.printf("Dealer's %s\n\n",dealer);
			hitMe=dealer.wantHit();
			if(hitMe) {
				System.out.println("Dealer hits\n");
				dealer.add(dealer.deal());
			}
			else System.out.println("Dealer stays\n");
		} while(hitMe);
	}
	
	/**
	 * Prints results of one round
	 */
	private static void results() {
		
		//dealer's results
		int dealerval=dealer.getVal();
		if(dealerval>21) System.out.printf("Dealer busted with %d\n",dealerval);
		else if (dealerval==21) System.out.println("Dealer has 21! House wins!");
		else System.out.printf("Dealer has %d\n",dealerval);
		
		
		//each player's results
		int playerval;
		for(int i=0;i<NUM_PLAYERS;i++) {
			playerval=players[i].getVal();
			if(playerval>21) System.out.printf("Player %d busted!\n",i+1);
			else if(playerval==21 && dealerval!=21) System.out.printf("Player %d gets a blackjack!\n",i+1);
			else if(dealerval>21 || playerval>dealerval) System.out.printf("Player %d wins!\n",i+1);
			else if(playerval==dealerval) System.out.printf("Player %d ties dealer!\n",i+1);
			else System.out.printf("Player %d loses!\n",i+1);
		}
	}
	
	/**
	 * Number of Players
	 */
	private static final int NUM_PLAYERS = 2;
	
	/**
	 * Game's dealer
	 */
	private static Dealer dealer=new Dealer(new Deck());
	
	/**
	 * Human Players in game
	 */
	private static HumanPlayer[] players = new HumanPlayer[NUM_PLAYERS];
}
