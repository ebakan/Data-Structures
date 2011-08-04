import java.util.Random;
/**
 * I tested if the heap was empty or not
 * by checking if the length of the ArrayList
 * it uses to store its data is zero or nonzero.
 * In this tester class I tested a random range of numbers
 * from 0-100 and the removal worked successfully in all cases.
 * The only case I did not check is adding null values
 * to the heap, which should result in exceptions.
 * @author Eric Bakan
 *
 */
public class HeapTester_Bakan {
	public static void main(String[] args) {
		Heap heap = new Heap();
		Random rand = new Random();
		for(int i=0;i<100;i++) {
			heap.add(rand.nextInt(100));
		}
		while(!heap.isEmpty()) {
			System.out.println(heap+" ");
			heap.removeRoot();
		}
	}
}
