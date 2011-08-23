package sml;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Interpreter for SML machine code
 * @author Eric Bakan
 * @author Nikhil Desai
 * Last Edited 9/21/10
 * 
 * reads input from an InputStream (either
 * a file or user input) and executes
 * the program
 */
public class SMLInterpreter {
	private int[] memory;
	private int accumulator;
	private int currentAddress;
	private boolean isRunning;
	
	private Scanner in;
	private PrintStream out;
	
	private static final int NUM_REGISTERS = 100;
	private static final String INTRO_MSG = "*** Welcome to SML! ***\n" +
			"*** Please enter your program one instruction\t***\n" +
			"*** (or data word) at a time. I will display\t***\n" +
			"*** the location number and a question mark(?).\t***\n" +
			"*** You then type the word for that location.\t***\n" +
			"*** Type -99999 to stop entering your program.\t***\n";
	
	private static final int READ = 10;
	private static final int WRITE = 11;
	private static final int LOAD = 20;
	private static final int STORE = 21;
	private static final int ADD = 30;
	private static final int SUBTRACT = 31;
	private static final int DIVIDE = 32;
	private static final int MULTIPLY = 33;
	private static final int BRANCH = 40;
	private static final int BRANCHNEG = 41;
	private static final int BRANCHZERO = 42;
	private static final int HALT = 43;
	private static final int VARIABLE = 00;
	
	/**
	 * Class Constructor
	 * @param in inputstream for reading input
	 * @param out outputstream for printing output
	 */
	public SMLInterpreter(InputStream in, PrintStream out) {
		this.in = new Scanner(in);
		this.out = out;

		memory = new int[NUM_REGISTERS];
		accumulator = 0;
		currentAddress = 00;
		isRunning = false;
		init();
	}
	
	/**
	 * Allows the user to try the program again,
	 * enter a new program, or exit after execution
	 */
	public void runRepeat() {
		int tryAgain=-1;
		run();
		do {
			out.println("Enter 1 to try program again, 2 to enter a new program, or 3 to exit");
			tryAgain=in.nextInt();
			switch (tryAgain) {
			case 1: run(); break;
			case 2: init(); run(); break;
			case 3: out.println("Goodbye."); break;
			default: out.println("Invalid entry."); break;
			}
		} while (tryAgain!=3);
	}
	
	/**
	 * Basic runner method, executes line after line
	 * until either a HALT is received or an error
	 * is thrown
	 */
	public void run() {
		isRunning = true;
		currentAddress=0;
		try {
			while(isRunning) exec();
		}
		catch (BadCommandException e) {
			out.println(e.toString());
			dump();
			System.exit(1);
		}
		currentAddress--;
		if (currentAddress<0) currentAddress=0;
	}

	/**
	 * Initializer method, prints intro message
	 * and reads program data
	 */
	private void init() {
		out.println(INTRO_MSG);
		getInput();
	}
	
	/**
	 * Gets input from the in buffer - either
	 * a file or user input depending on usage
	 */
	private void getInput() {
		String instr;
		int input=0;
		int index = 0;	
		do {
			out.printf("%02d ? ", index);
			instr = in.nextLine();
			if (instr.charAt(0)=='+') instr=instr.substring(1);
			try {
				input=Integer.parseInt(instr);
			}
			catch (NumberFormatException e) {
				out.println("Invalid entry. Try again.");
				index--;
			}
			memory[index++] = input;
		} while(input!=-99999 && index<NUM_REGISTERS);
		memory[--index]=0;
		
		if(index >= NUM_REGISTERS) 
			throw new BadCommandException(input, "Program too long");
	}
	
	/**
	 * Reads current memory address and executes command
	 * on address, throws fatal error in cases of division
	 * by zero, invalid command, or accumulator value
	 * exceeding defined max/min values
	 */
	private void exec() {
		if (currentAddress==NUM_REGISTERS)
			throw new BadCommandException(memory[--currentAddress], "Reached end of memory. " +
					"Likely encountered an infinite loop.");
		int command = memory[currentAddress];
	    int operation=command/100;
	    int address=command%100;

	    switch (operation) {
	        case READ: out.print("Enter value: "); memory[address]=in.nextInt(); in.nextLine(); break;
	        case WRITE: out.println(memory[address]); break;
	        case LOAD: accumulator=memory[address]; break;
	        case STORE: memory[address]=accumulator; break;
	        case ADD: accumulator+=memory[address]; break;
	        case SUBTRACT: accumulator-=memory[address]; break;
	        case DIVIDE: 
	        	try {
	        		accumulator/=memory[address]; break;
	        	} catch(ArithmeticException e) {
	        		throw new BadCommandException(command, "Divide by zero");
	        	}
	        case MULTIPLY: accumulator*=memory[address]; break;
	        case BRANCH: currentAddress=address-1; break;
	        case BRANCHNEG: if (accumulator<0) currentAddress=address-1; break;
	        case BRANCHZERO: if (accumulator==0) currentAddress=address-1; break;
	        case HALT: isRunning=false; break;
	        case VARIABLE: break;
	        default: throw new BadCommandException(command, "Not a valid command");
	    }
	    
	    if(Math.abs(accumulator) > Math.pow(10,NUM_REGISTERS+2)-1) 
	    	throw new BadCommandException(command, "Accumulator has forbidden value!");
	    
	    currentAddress++;
	}
	
	/**
	 * Dumps current memory contents:
	 * accumulator value, address,
	 * command, operation, operand,
	 * program & program data
	 * Called when an error is encountered
	 */
	public void dump() {
		out.printf("REGISTERS:\n"+
				   "accumulator\t\t+%04d\n"+
				   "instructionCounter\t  +%02d\n"+
				   "instructionRegister\t+%04d\n"+
				   "operationCode\t\t   %02d\n"+
				   "operand\t\t\t  +%02d\n"+
				   "\nMEMORY:\n   ",
				   accumulator,currentAddress,
				   memory[currentAddress],memory[currentAddress]/100,
				   memory[currentAddress]%100);
		for (int i=0;i<10;i++) out.printf("%6d",i);
		out.println();
		for (int i=0;i<100;i+=10) {
			out.printf("%3d ",i);
			for (int j=0;j<10;j++)
				out.printf("+%04d ",memory[i+j]);
			out.println();
		}
	}
}

