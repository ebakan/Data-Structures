/**
 * @author eric.bakan12
 *
 */
public class Card {
	private String[] values = {"Ace",
            				   "Two",
		  		               "Three",
				               "Four",
				               "Five",
				               "Six",
				               "Seven",
				               "Eight",
				               "Nine",
				               "Ten",
				               "Jack",
				               "Queen",
				               "King"};
	private String[] suits= {"Diamonds",
	                         "Clubs",
	                         "Hearts",
	                         "Spades"};
	private int val;
	public Card() {
		this(0);
	}
	public Card(int val) {
		this.val=val;
	}
	public int getVal() {
		return val;
	}
	public int getNum() {
		return val%13;
	}
	public int getSuit() {
		return val/13;
	}
	public String toString() {
		return String.format("%s of %s",values[getNum()],suits[getSuit()]);
	}
}
