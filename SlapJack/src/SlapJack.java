import java.util.Scanner;
public class SlapJack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to SlapJack!");
		System.out.print("What is the first player's name?");
		String p1 = sc.nextLine();
		System.out.print("What is the second player's name?");
		String p2 = sc.nextLine();
		SlapJackGame game = new SlapJackGame(p1,p2);
		//String winner = game.playGame();
		//System.out.println(winner + " wins!");
	}

}
