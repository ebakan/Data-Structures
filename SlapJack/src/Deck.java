/**
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
/**
 * @author eric.bakan12
 *
 */
public class Deck {
	private ArrayList<Card> cards;
	public Deck() {
		cards=new ArrayList<Card>(52);
		fill();
	}
	public Deck(ArrayList<Card> cards) {
		this.cards=cards;
	}
	public void shuffle() {
		Collections.shuffle(cards);
	}
	public String toString() {
		String out="";
		for(Card c: cards)
			out+=c.toString()+'\n';
		return out.substring(0,out.length());
	}
	public Card deal() {
		return cards.remove(0);
	}
	public ArrayList<Card> deal(int num){
		if (num<=0) throw new IllegalArgumentException();
		ArrayList<Card> out = new ArrayList<Card>();
		for(int i=0;i<num;i++)
			out.add(cards.remove(0));
		return out;
		
	}
	private void fill() {
		for(int i=0;i<52;i++)
			cards.add(new Card(i));
	}

}