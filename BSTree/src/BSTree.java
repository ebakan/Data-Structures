/**
 * Represents a basic Binary Search Tree
 * @author ebakan
 *
 * NOTES:
 * Everything worked as hoped without any errors
 * I accurately tested all potential cases and
 * the program passed with flying colors.
 */
public class BSTree {
	
	//no special constructor needed
	
	/**
	 * Inserts a value into the tree
	 * @param c value to be inserted
	 */
    @SuppressWarnings("unchecked")
	public void insert(Comparable c) {
    	root=insert(c,root);
    }
    
    /**
     * Helper method to insert a value
     * at a specific node
     * Traverses the tree recursively until
     * the proper position is found and
     * then occupies it
     * Returns the root node of the updated tree
     * @param c value to be inserted
     * @param tree the current node to begin the search from
     * @return the new tree
     */
	@SuppressWarnings("unchecked")
	private TreeNode insert(Comparable c, TreeNode tree) {
    	if(tree==null)
    		tree = new TreeNode(c);
    	else if(c.compareTo(tree.getValue())<0)
    		tree.setLeft(insert(c,tree.getLeft()));
    	else
    		tree.setRight(insert(c,tree.getRight()));
    	return tree;
    }
	
	/**
	 * Removes the node at the root of the tree
	 * @return value of node removed
	 */
	@SuppressWarnings("unchecked")
	public Comparable removeRoot() {
		if(root==null)
			return null;
		Comparable returnVal=root.getValue();
		root=delete(root.getValue(),root);
		return returnVal;
	}
	
	/**
	 * Finds the location of a node
	 * enclosing c and deletes it, returning
	 * the node of the root of the now-updated
	 * tree
	 * @param c object to look for
	 * @param node starting point
	 * @return node at the root of the updated tree
	 */
	@SuppressWarnings("unchecked")
	private TreeNode delete(Comparable c, TreeNode node) {
		if(node==null)
			return null;
		int comparison=node.getValue().compareTo(c);
		if(comparison<0) {
			node.setRight(delete(c,root.getRight()));
			return node;
		}
		else if(comparison>0) {
			node.setLeft(delete(c,root.getLeft()));
			return node;
		}
		else {
			if(node.getLeft()==null)
				return node.getRight();
			else if(node.getRight()==null)
				return node.getLeft();
			else {
				if(node.getLeft().getRight()==null) {
					node.setValue(node.getLeft().getValue());
					node.setLeft(node.getLeft().getLeft());
					return node;
				}
				else {
					node.setValue(largestChild(node.getLeft()));
					return node;
				}
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private Comparable largestChild(TreeNode node) {
		if(node.getRight().getRight()==null) {
			Comparable returnVal = node.getRight().getValue();
			node.setRight(node.getRight().getLeft());
			return returnVal;
		}
		else
			return largestChild(node.getRight());
	}

	
	/**
	 * Flattens and prints the tree
	 */
    public void print() {
    	print(root);
    }
    
    /**
     * Traverses the tree in order at 
     * TreeNode node, printing each TreeNode's
     * data member along the way
     * @param node starting node to recursively traverse from
     */
    private void print(TreeNode node) {
    	if(node!=null) {
    		print(node.getLeft());
    		System.out.println(node.getValue());
    		print(node.getRight());
    	}
    }
    
    /**
     * Data members
     */
    //root of the tree
	private TreeNode root;
}
