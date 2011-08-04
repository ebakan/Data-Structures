package cards;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Represents a deck of cards
 * @author Eric Bakan
 */
public class Deck {
	
	/**
	 * Creates standard deck
	 * of 52 playing cards
	 */
	public Deck() {
		cards=new ArrayList<Card>();
		fill();
	}
	
	/**
	 * Optional constructor to
	 * initialize deck
	 * @param cards default card list
	 */
	public Deck(ArrayList<Card> cards) {
		this.cards=cards;
	}
	
	/**
	 * Shuffles deck using
	 * Collections.shuffle()
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Prints newline-delimited list
	 * of cards in deck
	 */
	public String toString() {
		String out="";
		for(Card c: cards)
			out+=c.toString()+'\n';
		return out.substring(0,out.length());
	}
	
	/**
	 * @return single card
	 */
	public Card deal() {
		checkSize();
		return cards.remove(0);
	}
	
	/**
	 * @param num number of cards to deal
	 * @return ArrayList of cards
	 */
	public ArrayList<Card> deal(int num){
		if (num<=0) throw new IllegalArgumentException();
		ArrayList<Card> out = new ArrayList<Card>();
		for(int i=0;i<num;i++) {
			checkSize();
			out.add(cards.remove(0));
		}
		return out;
		
	}
	
	/**
	 * Refills deck if out of cards
	 * and shuffles
	 */
	private void checkSize() {
		if(cards.size()==0) {
			System.out.println("Deck empty. Adding new deck");
			fill();
			shuffle();
		}
	}
	
	/**
	 * Clears deck and refills
	 * with 52 new cards
	 */
	private void fill() {
		cards.clear();
		for(int i=0;i<52;i++)
			cards.add(new Card(i));
	}
	
	/**
	 * Holds cards
	 */
	private ArrayList<Card> cards;
}