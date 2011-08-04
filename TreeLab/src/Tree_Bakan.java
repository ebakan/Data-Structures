
public class Tree_Bakan {
	public static void main(String[] args) throws InterruptedException {
		TreeDisplay display=new TreeDisplay();
		TreeNode tree=TreeUtilities.createDecodingTree(display);
		display.displayTree(tree);
		System.out.println(TreeUtilities.decodeMorse(tree, "-- --- .-. ... . -.-. --- -.. .", display));
	}
}
