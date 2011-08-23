package sml;

/**
 * SML Runner
 * @author Eric Bakan
 * @author Nikhil Desai
 * Last Edited 9/21/10
 * 
 * Basic runner of SMLInterpreter class
 *
 */
public class SMLRunner {
	
	/**
	 * Creates instance of SMLInterpreter class, runs it
	 * @param args default args
	 */
	public static void main(String[] args) {
		SMLInterpreter machine = new SMLInterpreter(System.in, System.out);
		machine.runRepeat();
	}
}