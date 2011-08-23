package exceptions;

@SuppressWarnings("serial")
public class BakanException extends Exception{
	public BakanException(String msg) {
		super(msg);
		printStackTrace();
	}
	public String toString() {
		return "This is a Bakan Exception";
	}
}
