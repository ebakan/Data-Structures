package exceptions;

public class ExceptionDemo {
	public static void main(String[] args) {
		System.out.println("Welcome to the Exception Demo!\n");
		
		try {
			int[] ints = {0,1,2,3};
			@SuppressWarnings("unused")
			int f=ints[4];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBoundsException caught from attempt to access an array index greater or equal to the array's length.\n");
		}
		
		try {
			String str = "asdf";
			@SuppressWarnings("unused")
			char c = str.charAt(5);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException caught from attempt to access an array index greater or equal to the array's length.\n");
		}
		
		try {
			IllegalArgumentExceptionThrower("error");
		}
		catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException caught from attempt to pass an invalid argument to a method.\n");
		}
		
		
		try {
			@SuppressWarnings("unused")
			int i=1/0;
		}
		catch (ArithmeticException e) {
			System.out.println("ArithmeticException caught from attempt to divide by zero.\n");
		}
		
		try {
			BakanExceptionThrower();
		}
		catch (BakanException e) {
			System.out.println("BakanException caught");
		}
	}
	
	private static void IllegalArgumentExceptionThrower(String msg) {
		if(msg.equals("error"))
			throw new IllegalArgumentException();
	}
	
	private static void BakanExceptionThrower() throws BakanException {
		throw new BakanException("test throw");
	}
	
}
