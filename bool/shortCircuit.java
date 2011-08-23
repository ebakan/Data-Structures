package bool;
/**
 * demonstrates an example where (A && B) != (B && A)
 * @author eric.bakan12
 */
public class shortCircuit {

	/**
	 * demonstrates an example where (A && B) != (B && A)
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Using unary operators:");
		int a=0,b=0;
		System.out.printf("A && B: %b\n",a++==b && a==b);
		a=0;
		System.out.printf("B && A: %b\n",a==b && a++==b);
		
		System.out.println("Without sing unary operators:");
		//System.out.printf("A && B: %b\n",true || false && false && true);
		//System.out.printf("B && A: %b\n",false && true && true || false);

	}

}
