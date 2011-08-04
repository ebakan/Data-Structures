import java.util.LinkedList;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.HashMap;
/**
 * A basic BASIC interpreter which
 * handles LET and PRINT commands
 * and evaluates expressions using
 * a hash map.
 * 
 * NOTES: This program has been proven
 * to perform well and handle errors
 * gracefully with a variety of tests,
 * including invalid commands and
 * improper variable names. The only
 * feature which it does not support
 * is parentheses at the moment
 * (attempting to use them will yield
 * an exception, but as this is not
 * required of the assigned project
 * it is left unfixed)
 * @author Eric Bakan
 *
 */
public class BasicInterpreter_Bakan {
	/**
	 * Main method, runs the interpreter
	 * until "quit" or "exit" is entered
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		System.out.println("BASIC_INTERPRETER_BAKAN v1.0");
		Scanner sc = new Scanner(System.in);
		HashMap<String,Double> map = new HashMap<String,Double>();
		Calculator calculator = new Calculator();
		String input="";
		while(!input.equals("QUIT") && !input.equals("EXIT")) {
			System.out.print(">>>");
			input=sc.nextLine().trim().toUpperCase();
			if(!input.equals("QUIT") && !input.equals("EXIT")) {
				try {
					parseCommand(input, map, calculator);
				}
				catch(VarNotDefinedException e) {
					System.out.println("Variable not defined: "+e.getMessage());
				}
				catch(InvalidVarNameException e) {
					System.out.println("Invalid variable name: "+e.getMessage());
				}
				catch(InvalidCommandException e) {
					System.out.println("Invalid command: "+e.getMessage());
				}
				catch(InvalidExpressionException e) {
					System.out.println("Invalid expression: "+e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Substitutes valid variable names
	 * for their current value, based on
	 * the current HashMap
	 * @param command String to parse
	 * @param map HashMap of current variables and values
	 * @return String with doubles in place of variables
	 * @throws VarNotDefinedException
	 * @throws InvalidVarNameException
	 */
	public static String substituteTokens(String command, HashMap<String,Double> map) throws VarNotDefinedException, InvalidVarNameException {
		String upperCommand = command.toUpperCase().trim(); // just to be sure
		// first split up the string into valid tokens
		String[] elements = upperCommand.split("[*()+-/ 0-9]");
        for(String s : elements) {
            if(s.matches("[A-Z]+[A-Z0-9_]*")) {// if a valid character name
                if(map.containsKey(s))
                	upperCommand=upperCommand.replace(s,map.get(s).toString());
                else // if the key is not in the map
                	throw new VarNotDefinedException(s);
            }
            else if(s.length()>0)// ignore blank tokens, if the token is not a valid variable name, throw an exception
            	throw new InvalidVarNameException(s);
        }
        return upperCommand;
	}
	
	/**
	 * Parses an inputted string and evaluates
	 * the command based on the current HashMap
	 * of variables and values
	 * @param command String to parse and evaluate
	 * @param map HashMap of String variables and Double values
	 * @param calculator Calculator object to use for calculation
	 * @throws InvalidCommandException
	 * @throws InvalidVarNameException
	 * @throws VarNotDefinedException
	 * @throws InvalidExpressionException
	 */
	public static void parseCommand(String command, HashMap<String,Double> map, Calculator calculator) throws InvalidCommandException, InvalidVarNameException, VarNotDefinedException, InvalidExpressionException {
		String upperCommand = command.toUpperCase().trim(); // just to be sure
		// assignment
		if(upperCommand.length()>=3 && upperCommand.substring(0,3).equals("LET")) {
			int equalsIndex = upperCommand.indexOf('=');
			if(equalsIndex<0) // no equals sign found
				throw new InvalidExpressionException(command);
			String varName = upperCommand.substring(4,equalsIndex).trim();
			if(!varName.matches("[A-Z]+[A-Z0-9_]*")) // if an invalid var name
				throw new InvalidVarNameException(varName);
			double val = calculator.calculate(substituteTokens(upperCommand.substring(equalsIndex+1),map));
			map.put(varName,val);
			System.out.println("0K");
		}
		// printing
		else if(upperCommand.length() >= 5 && upperCommand.substring(0,5).equals("PRINT")) {
			System.out.println(calculator.calculate(substituteTokens(upperCommand.substring(5),map)));
		}
		// bad command
		else {
			int spaceIndex=upperCommand.indexOf(' ');
			if(spaceIndex<0)
				spaceIndex=upperCommand.length();
			throw new InvalidCommandException(upperCommand.substring(0,spaceIndex));
		}
	}
}

/**
 * Exception for an undefined variable
 * @author Eric Bakan
 *
 */
@SuppressWarnings("serial")
class VarNotDefinedException extends Exception {
	public VarNotDefinedException(String s) {
		super(s);
	}
}

/**
 * Exception for an invalid variable name
 * (using Java rules)
 * @author Eric Bakan
 *
 */
@SuppressWarnings("serial")
class InvalidVarNameException extends Exception {
	public InvalidVarNameException(String s) {
		super(s);
	}
}

/**
 * Exception for an invalid command
 * (i.e. not LET or PRINT)
 * @author Eric Bakan
 *
 */
@SuppressWarnings("serial")
class InvalidCommandException extends Exception {
	public InvalidCommandException(String s) {
		super(s);
	}
}

/**
 * Exception for invalid expressions
 * (e.g. a LET command with no '=')
 * @author Eric Bakan
 *
 */
@SuppressWarnings("serial")
class InvalidExpressionException extends Exception {
	public InvalidExpressionException(String s) {
		super(s);
	}
}
	
/**
 * Basic implementation of a stack using a
 * LinkedList to store data
 * 
 * @author Eric Bakan
 *
 * @param <E> type of object stack holds
 */
class Stack<E> {
	
	/**
	 * Default constructor, initializes LinkedList
	 */
	public Stack() {
		list=new LinkedList<E>();
	}
	
	/**
	 * Returns whether stack is empty or not
	 * @return whether stack is empty or not
	 */
	public boolean isEmpty() {
		return list.size()==0;
	}
	
	/**
	 * Pushes an object to the top of the stack
	 * @param e object to be pushed
	 * @return pushed object
	 */
	public E push( E e ) {
		list.addFirst(e);
		return list.getFirst();
	}
	
	/**
	 * Pops an object off the top of the stack
	 * @return popped object
	 */
	public E pop() {
		if(isEmpty())
			throw new EmptyStackException();
		return list.removeFirst();
	}
	
	/**
	 * Returns the object at the top of the stack
	 * without removing it
	 * @return first object on the stack
	 */
	public E peek() {
		if(isEmpty())
			throw new EmptyStackException();
		return list.getFirst();
	}
	
	private LinkedList<E> list;
	
}
/**
 * Stack-based Calculator class
 * @author Eric Bakan
 *
 */
class Calculator {
	
	/**
	 * Default constructor
	 * Initializes output and operator stacks
	 */
	public Calculator() {
		output=new Stack<String>();
		operators=new Stack<operator>();
	}
	
	/**
	 * Calculates the value of an expression
	 * @param expr expression to evaluate
	 * @return value of expression
	 */
	public double calculate(String expr) {
		parseString(expr);
		return evaluateOutput();
	}
	
	/**
	 * Parses a String containing an
	 * infix equation and stores the
	 * RPN equivalent in output
	 * @param expr String to parse
	 */
	private void parseString(String expr) {
		//add whitespace in
		for(int i=0;i<operator.operators.length();i++) {
			String str = ""+operator.operators.charAt(i);
			//handle caret as explicit, not identifier
			if(str.charAt(0)=='^')
				str="\\^";
			expr=expr.replaceAll("["+str+"]", " "+str+" ");
		}
		StringTokenizer tokenizer = new StringTokenizer(expr);
	     while (tokenizer.hasMoreTokens()) {
	    	 String str=tokenizer.nextToken();
	    	 if(isNum(str))
	    		 output.push(str);
	    	 else {
    			 operator op=operator.getOperator(str.charAt(0));
	    		 if(op.equals(operator.lpar))
	    			 operators.push(op);
	    		 else if(op.equals(operator.rpar)) {
	    			 try {
		    			 while(!operators.peek().equals(operator.lpar))
		    				 output.push(operators.pop().toString());
		    			 operators.pop();
		    			 if(!isNum(operators.peek().toString()))
		    				 output.push(operators.pop().toString());
	    			 } catch(EmptyStackException e) {
	    				 throw new IllegalArgumentException("Mismatched Parentheses");
	    			 }
	    		 }
	    		 else {
	    			 //handle operator order
	    			 if(!operators.isEmpty() && op.compareOrder(operators.peek())<=0)
	    				 output.push(operators.pop().toString());
	    			 operators.push(op);
	    		 }
	    	 }
	     }
	     while(!operators.isEmpty())
				output.push(""+operators.pop());
	}
	
	/**
	 * Evaluates the expression in the output stack
	 * @return value of the expression in output
	 */
	private double evaluateOutput() {
		//temporary stack for rpn calculation
		Stack<Double> values = new Stack<Double>();
		//reverse of output
		Stack<String> equation = new Stack<String>();
		//reverse output
		while(!output.isEmpty())
			equation.push(""+output.pop());
		//calculate rpn
		while(!equation.isEmpty()) {
			String str = equation.pop();
			//handle if character
			if(isNum(str))
				values.push(Double.parseDouble(str));
			//handle if operator
			else {
				operator op=operator.getOperator(str.charAt(0));
				double b=values.pop(), a=values.pop();
				values.push(op.operate(a, b));
			}
		}
		return values.pop();
	}
	
	/**
	 * Determines whether the inputted string
	 * is a number or an operator
	 * Throws an IllegalArgumentException if
	 * s is neither a number nor an operator
	 * @param s string to be checked
	 * @return true if s is a number,
	 * false if n is an operator,
	 * else an IllegalArgumentException
	 */
	private boolean isNum(String s) {
		try { //check if it's an integer
			Double.parseDouble(s);
			return true;
		}
		catch(NumberFormatException e)
		{
			char c = s.charAt(0);
			if(operator.isOperator(c)) //if a valid token
				return false;
			else //invalid character
				throw new IllegalArgumentException("INVALID CHARACTER");
		}
	}
	
	/**
	 * Enum of operators
	 * @author Eric bakan
	 *
	 */
	private enum operator {
		
		/**
		 * The operators:
		 * Power
		 * Multiply
		 * Divide
		 * Add
		 * Subtract
		 * Left Parentheses
		 * Right Parentheses
		 */
		pow(3,'^'),mult(2,'*'),div(2,'/'),add(1,'+'),sub(1,'-'),lpar(0,'('),rpar('0',')');
		
		private static final String operators="^*/+-()";
		private final int order; //order of operations value
		private final char chr; //character representation
		
		/**
		 * Determines of inputted character is an operator
		 * @param c character to check
		 * @return whether c is a character
		 */
		public static boolean isOperator(char c) {
			for(int i=0;i<operators.length();i++)
				if(c==operators.charAt(i))
					return true;
			return false;
		}
		
		/**
		 * Constructor
		 * initializes order and character values
		 * @param order order value
		 * @param chr character representation
		 */
		operator(int order, char chr) {
			this.order=order;
			this.chr=chr;
		}
		
		/**
		 * Parses a character and returns its
		 * operator equivalent
		 * @param c character to parse
		 * @return operator equivalent
		 */
		public static operator getOperator(char c) {
			switch(c) {
			case '^':
				return operator.pow;
			case '*':
				return operator.mult;
			case '/':
				return operator.div;
			case '+':
				return operator.add;
			case '-':
				return operator.sub;
			case '(':
				return operator.lpar;
			case ')':
				return operator.rpar;
			default:
				throw new IllegalArgumentException("INVALID OPERATOR "+c); 
			}
		}
		
		/**
		 * Performs an operation with a left and righ value
		 * @param a left value
		 * @param b right value
		 * @return result of evaluated expression
		 */
		public double operate(double a, double b) {
			switch(this) {
			case pow:
				return Math.pow(a,b);
			case mult:
				return a*b;
			case div:
				return a/b;
			case add:
				return a+b;
			case sub:
				return a-b;
			default:
				throw new ArithmeticException("INVALID OPERATOR");
			}
		}
		
		/**
		 * Returns char representation of operator
		 * @return char representation of operator
		 */
		public char getChar() {
			return this.chr;
		}
		
		/**
		 * Overridden toString method
		 * @return chr
		 */
		@Override
		public String toString() {
			return ""+this.chr;
		}
		
		/**
		 * Returns result of compareTo() of orders
		 * of two operators
		 * @param o operator to compare with
		 * @return 1 if this operator's order is greater
		 * than o's, -1 if this operator's order is less
		 * than o's, 0 if this operator's order is equal
		 * to o's
		 */
		public int compareOrder(operator o) {
			return ((Integer) this.order).compareTo(o.getOrder());
		}
		
		/**
		 * Returns this operator order
		 * @return
		 */
		private int getOrder() {
			return this.order;
		}
		
	};

	private Stack<String> output; //output stack
	private Stack<operator> operators; //operator stack
}
