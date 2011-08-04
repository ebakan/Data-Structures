import java.util.Random;

/**
 * Tester class for BSTree
 * @author ebakan
 *
 */
public class TreeTester_Bakan {
	
	/**
	 * Main method
	 * @param args command line args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		BSTree tree = new BSTree();
		final int NUMNUMS=20;
		Random randgen = new Random();
		System.out.printf("Adding %d random numbers to the tree\n",NUMNUMS);
		for(int i=0;i<20;i++) {
			int newint=randgen.nextInt(NUMNUMS);
			System.out.printf("Adding %d\n",newint);
			tree.insert(newint);
		}
		System.out.println("Flattened tree:");
		tree.print();
		for(int i=0;i<NUMNUMS;i++) {
			System.out.println("Removing root.");
			Comparable removed = tree.removeRoot();
			System.out.println(removed.toString()+" was removed.");
			System.out.println("Flattened tree:");
			tree.print();
		}
		System.out.println("Testing empty tree.");
		tree.removeRoot();
		System.out.println("Test successful.");
	}
}