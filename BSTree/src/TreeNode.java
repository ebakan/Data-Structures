/**
 * Represents a node of a binary tree
 * @author ebakan
 *
 */
public class TreeNode {
	/**
	 * Creates a new TreeNode of a given value
	 * Child nodes are initialized to null
	 * @param initValue value of the node
	 */
	@SuppressWarnings("unchecked")
	public TreeNode(Comparable initValue) {
		value = initValue;
		left = null;
		right = null;
	}
	
	/**
	 * Creates a new TreeNode with specific
	 * value and child nodes
	 * @param initValue value of the node
	 * @param initLeft left node
	 * @param initRight right node
	 */
	@SuppressWarnings("unchecked")
	public TreeNode(Comparable initValue, TreeNode initLeft, TreeNode initRight) {
		value = initValue;
		left = initLeft;
		right = initRight;
	}

	/**
	 * Returns value of the node
	 * @return value of the node
	 */
	@SuppressWarnings("unchecked")
	public Comparable getValue() {
		return value;
	}
	
	/**
	 * Returns left child
	 * @return left child
	 */
	public TreeNode getLeft() {
		return left;
	}
	
	/**
	 * Returns right child
	 * @return right child
	 */
	public TreeNode getRight() {
		return right;
	}

	/**
	 * Sets new value of the node
	 * @param theNewValue new value of the node
	 */
	@SuppressWarnings("unchecked")
	public void setValue(Comparable theNewValue) {
		value = theNewValue;
	}
	
	/**
	 * Sets new left child of the node
	 * @param theNewLeft new lift child of the node
	 */
	public void setLeft(TreeNode theNewLeft) {
		left = theNewLeft;
	}
	
	/**
	 * Sets new right child of the node
	 * @param theNewRight new right child of the node
	 */
	public void setRight(TreeNode theNewRight) {
		right = theNewRight;
	}
	
	/**
	 * Data members
	 */
	//value of the node
	@SuppressWarnings("unchecked")
	private Comparable value;
	//left child
	private TreeNode left;
	//right child
	private TreeNode right;
}