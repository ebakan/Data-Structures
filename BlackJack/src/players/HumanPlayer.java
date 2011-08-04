package players;
import cards.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author ebakan
 *
 */
public class HumanPlayer extends BlackJackPlayer {
	
	/**
	 * Default Constructor
	 * does nothing
	 */
	public HumanPlayer() {
	}
	
	/**
	 * Gives HumanPlayer new hand of cards
	 * @param cards ArrayList of cards
	 * to be held
	 */
	public HumanPlayer(ArrayList<Card> cards) {
		super(cards);
	}
	
	/**
	 * Assigns HumanPlayer a Hand object
	 * to be held
	 * @param h Hand to be held
	 */
	public HumanPlayer(Hand h) {
		super(h);
	}
	
	/* (non-Javadoc)
	 * @see players.BlackJackPlayer#wantHit()
	 * asks user for hit or stay
	 */
	@Override
	public boolean wantHit() {
		return askHit();
	}
	
	/**
	 * @return whether user wants to hit or not
	 */
	private boolean askHit() {
		Scanner sc = new Scanner(System.in);
		boolean choice;
		char answer;
		do {
			System.out.println("Would you like to hit? (y/n)");
			answer=sc.next().trim().toLowerCase().charAt(0);
			if(answer!='y' && answer!='n')
				System.out.println("Please answer either y or n.");
			choice=answer=='y';
		} while(answer!='y' && answer!='n');
		return choice;
		
	}

}