/*Global Warming Quiz by Eric Bakan
 * Data Structures P3
 * 8/31/10
 */
package globalWarming;

import java.util.Scanner;
/**
 * Represents a Global Warming Quiz
 * @author eric.bakan12
 *
 */
public class GlobalWarming {
	private static Scanner scanner = new Scanner(System.in);
	
	//Questions in the format of a two dimensional array of String arrays in the following format:
	//{*question*,*answer1*,*answer2*,*answer3*,*answer4*,*correctAnswer*}
	private static String[][] questions = {{"Which of the following is a greenhouse gas?",
	                                        "CH4",
	                                        "O2",
	                                        "NO2",
	                                        "C6H12O6",
	                                        "A"},
	                                        
	                                       {"Which of the following greenhouse gasses poses the greatest threat to the environment as of now?",
										    "Methane",
										    "Water Vapor",
										    "Carbon Dioxide",
										    "Ozone",
										    "C"},
										    
										   {"What is the largest source of methane?",
										    "Human feces and flatulence",
										    "Bovine feces and flatulence",
										    "Natural gas leaks",
										    "Landfills",
										    "B"},
										    
										   {"How many degrees Celsius has the Earth's temperature risen in the 20th century?",
										    "1.6",
										    "5.2",
										    ".74",
										    ".32",
										    "C"},
										    
										   {"Which of the following will NOT directly contribute to the fight against global warming?",
										    "Riding a bike to school or work instead of driving a car",
										    "Eating more fruits and vegetables rather than meats",
										    "Planting trees in your neighborhood",
										    "Telling people how they can live more eco-friendly lives while failing to do so themselves",
										    "D"}};
	public static void main(String[] args) {
		System.out.println("Welcome to the Global Warming Quiz!");
		int score=0;
		int temp;
		for (int i=0;i<questions.length;i++) {
			temp=ask(i+1,questions[i]);
			if (temp==1) System.out.println("Correct!\n");
			else System.out.println("Wrong!\n");
			score+=temp;
		}
		System.out.printf("You got %d %s right! Your final score is %.1f%%.", score, score!=1?"questions":"question", (float) score/5*100);
	}
	
	//accepts the question number and a formatted string array, accepts an inputted answer,
	//returns 1 if the user is correct or 0 if he is wrong
	private static int ask(int qnum, String[] question) {
		System.out.printf("%d)%s\nA)%s\nB)%s\nC)%s\nD)%s\n", qnum, question[0], question[1], question[2], question[3], question[4]);
		char inputted = scanner.nextLine().trim().toUpperCase().charAt(0);
		return inputted==question[5].charAt(0)?1:0;
	}
}