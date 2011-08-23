package sml;

/**
 * SML Interpreter Exception Class
 * @author Eric Bakan
 * @author Nikhil Desai
 * Last Edited 9/21/10
 * 
 * Accepts a command and a descriptor of
 * the exception, used in cases of fatal
 * error such as invalid command, division
 * by zero, or accumulator exceeding defined
 * min/max values
 */
@SuppressWarnings("serial") //remove Eclipse warning :P
class BadCommandException extends RuntimeException {
	
	private int command;
	
	/**
	 * Class Constructor
	 * @param command command code raising exception
	 * @param msg descriptor of exception
	 */
	public BadCommandException(int command, String msg) {
		super(msg);
		this.command = command;
	}
	
	/**
	 * Description of how/why exception was raised
	 * @return string of exception message and command
	 */
	public String toString() {
		return super.toString() + " on command " + command + ".";
	}
}
