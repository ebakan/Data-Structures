package players;
import cards.*;
import java.util.ArrayList;

/**
 * @author ebakan
 *
 */
public class Dealer extends BlackJackPlayer {
	
	/**
	 * Default Constructor
	 * does nothing
	 */
	public Dealer() {
	}
	
	/**
	 * Gives dealer new shuffled deck
	 * @param d deck to be given
	 */
	public Dealer(Deck d) {
		this.d=d;
		this.d.shuffle();
	}
	
	/**
	 * Dealer Methods
	 */
	
	/**
	 * Deals a single card
	 * @return card on top of deck
	 */
	public Card deal() {
		return d.deal();
	}
	
	/**
	 * Deals num number of cards
	 * @param num number of cards to deal
	 * @return ArrayList of cards dealt
	 */
	public ArrayList<Card> deal(int num) {
		return d.deal(num);
	}
	
	/**
	 * @return top card of dealer's hand
	 */
	public Card showTop() {
		return hand.get(0);
	}
	
	/**
	 * Play Methods
	 */
	
	/* (non-Javadoc)
	 * @see players.BlackJackPlayer#wantHit()
	 * returns false on 16 or less or soft 17
	 */
	@Override
	public boolean wantHit() {
		return getVal()<=16 || (getVal()==17 && isSoft());
	}
	
	/**
	 * @return true if hand is soft, else false
	 */
	private boolean isSoft() {
		for(Card c: hand.getCards())
			if(c.getNum()==0)
				return true;
		return false;
	}
	
	/**
	 * Dealer's deck
	 */
	private Deck d;
}