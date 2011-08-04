package calculator;
import java.util.LinkedList;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.StringTokenizer;

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

/**
 * Tester class for Calculator class
 * @author Eric Bakan
 *
 */
public class Calculator_Bakan {
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		Scanner sc = new Scanner(System.in);
		for(;;) {
		System.out.println("Enter an expression:");
		String expr=sc.nextLine();
		double val=calculator.calculate(expr);
		System.out.printf("The value of %s is %.2f\n",expr,val);
		}
		
	}
}
