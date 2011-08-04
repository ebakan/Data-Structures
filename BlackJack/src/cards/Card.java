package cards;

/**
 * @author Eric Bakan
 * Represents a standard playing card
 */
/**
 * @author ebakan
 *
 */
public class Card {	
	
	/**
	 * Default constructor
	 * creates card with value 0
	 * (Ace of Diamonds)
	 */
	public Card() {
		this(0);
	}
	
	/**
	 * Creates a specific card
	 * @param val value to assign
	 */
	public Card(int val) {
		this.val=val;
	}
	
	/**
	 * @return value of card
	 */
	public int getVal() {
		return val;
	}
	
	/**
	 * @return face value of card
	 */
	public int getNum() {
		return val%13;
	}
	
	/**
	 * @return suit of card
	 */
	public int getSuit() {
		return val/13;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * returns card face value and suit
	 */
	public String toString() {
		return String.format("%s of %s",values[getNum()],suits[getSuit()]);
	}
	
	/**
	 * Card faces
	 */
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
	
	/**
	 * Card suits
	 */
	private String[] suits =  {"Diamonds",
          				 	   "Clubs",
          				 	   "Hearts",
          				       "Spades"};
	/**
	 * Card value
	 */
	private int val;
	
}