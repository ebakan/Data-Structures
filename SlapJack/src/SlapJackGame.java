import java.util.LinkedList;
import java.util.Stack;
import java.awt.event.*;
public class SlapJackGame implements KeyListener{
	public SlapJackGame(String p1, String p2) {
		this.p1=p1;
		this.p2=p2;
		this.hand1=new LinkedList<Card>();
		this.hand2=new LinkedList<Card>();
		this.pot=new Stack<Card>();
		fillHands();		
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {
		System.out.println("HI");
		if(e.getKeyChar()=='z') {
			if(pot.peek().getVal()==10) {
				System.out.println(p1+ " wins the stack!");
				while(!pot.isEmpty())
					hand1.offer(pot.pop());
			}
			else {
				System.out.println(p1+ " was premature!");
				while(!pot.isEmpty())
					hand2.offer(pot.pop());
			}
		}
		else if(e.getKeyChar()=='/') {
			if(pot.peek().getVal()==10) {
				System.out.println(p2+ " wins the stack!");
				while(!pot.isEmpty())
					hand2.offer(pot.pop());
			}
			else {
				System.out.println(p2+ " was premature!");
				while(!pot.isEmpty())
					hand1.offer(pot.pop());
			}
		}
	}
	private String currStatus() {
		return String.format("%s:\t%d cards\n%s:\t%d cards",p1,hand1.size(),p2,hand2.size());
	}
	private void fillHands() {
		hand1.clear();
		hand2.clear();
		Deck startdeck = new Deck();
		startdeck.shuffle();
		System.out.println("test");
		for(int i=0;i<26;i++) {
			hand1.offer(startdeck.deal());
			hand2.offer(startdeck.deal());
		}
		System.out.println("Cards dealed.");
	}
	public String playGame() {
		String player = p1;
		int i=0;
		while(hand1.size() != 0 && hand2.size() != 0) {
			Card topCard = playRound(player);
			if(player.equals(p1))
				player=p2;
			else
				player=p1;
			pot.push(topCard);
			System.out.println(currStatus());
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				System.out.println("interrupted");
			}
			
		}
		if(hand1.size()==0)
			return p1;
		else
			return p2;
	}
	private Card playRound(String player) {
		Card nextCard;
		if(player.equals(p1))
			nextCard = hand1.poll();
		else
			nextCard = hand2.poll();
		System.out.println(player + " plays a "+ nextCard.toString());
		
		return nextCard;
	}
	
	private String p1,p2;
	private LinkedList<Card> hand1, hand2;
	private Stack<Card> pot;
	
}
