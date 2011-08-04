/**
 * 
 */
package players;
import cards.*;
import java.util.ArrayList;

/**
 * Represents a player in a blackjack game
 * @author ebakan
 */
public class BlackJackPlayer {
	
	/**
	 * Default Constructor
	 */
	public BlackJackPlayer() {
	}
	
	/**
	 * Typical constructor which gives
	 * player a new hand of cards
	 * @param cards ArrayList of cards
	 * to be held
	 */
	public BlackJackPlayer(ArrayList<Card> cards) {
		newHand(cards);
	}
	
	/**
	 * Optional constructor to give
	 * BlackJackPlayer a default hand
	 * @param h hand to be held
	 */
	public BlackJackPlayer(Hand h) {
		hand=h;
	}
	
	/**
	 * Gives BlackJackPlayer a new
	 * hand of cards
	 * @param cards new cards to be held
	 */
	public void newHand(ArrayList<Card> cards) {
		hand=new Hand(cards);
	}
	
	/**
	 * Gives BlackJackPlayer a new hand
	 * @param h hand to be held
	 */
	public void newHand(Hand h) {
		hand=h;
	}
	
	/**
	 * Gives BlackJackPlayer a new card
	 * @param c card to be added
	 */
	public void add(Card c) {
		hand.add(c);
	}
	
	/**
	 * @return value of player's hand
	 */
	public int getVal() {
		return hand.getVal();
	}
	
	/**
	 * To-be-overridden method
	 * @return whether player wants
	 * to be hit or not
	 */
	public boolean wantHit() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Returns BlackJackPlayer's
	 * hand and value of hand
	 */
	public String toString() {
		return String.format("Hand:\n%s\nValue:%d",hand,getVal());
	}
	
	/**
	 * Hits player with new card
	 * @param c card to be added
	 * @return new value of hand
	 */
	protected int hitMe(Card c) {
		hand.add(c);
		return getVal();
	}
	
	/**
	 * Player's hand
	 */
	protected Hand hand;
}