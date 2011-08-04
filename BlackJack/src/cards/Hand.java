package cards;
import java.util.ArrayList;

/**
 * Represents a player's hand
 * of cards
 * @author Eric Bakan
 */
public class Hand {

	/**
	 * Constructor, assigns cards to hand
	 * @param cards cards to give to hand
	 */
	public Hand(ArrayList<Card> cards) {
		this.cards=cards;
	}
	
	/**
	 * @param c card to add to hand
	 */
	public void add(Card c) {
		cards.add(c);
	}
	
	/**
	 * @return ArrayList of cards in hand
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	/**
	 * @param index index to return card at
	 * @return card at index index
	 */
	public Card get(int index) {
		return cards.get(index);
	}
	
	/**
	 * @return value of hand
	 */
	public int getVal() {
		int val=0;
		for(Card c: cards) {
			int card=c.getNum();
			if(card==0) val+=11;
			else if(card>=9) val+=10;
			else val+=card+1;
		}
		if(val>21) {
			val=0;
			for(Card c: cards) {
				int card=c.getNum();
				if(card>=9) val+=10;
				else val+=card+1;
			}
		}
		return val;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * returns newline-delimited list of cards
	 * in hand
	 */
	public String toString() {
		String out="";
		for(Card c: cards)
			out+=c.toString()+'\n';
		return out.substring(0,out.length());
	}
	
	/**
	 * Holds cards in hand
	 */
	private ArrayList<Card> cards;

}