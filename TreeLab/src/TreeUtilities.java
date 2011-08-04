import java.io.*;
/*
 * Eric Bakan
 * This project was relatively easy and fun to do. It helped
 * reinforce all the major concepts of binary trees, including
 * different traversal methods and algorithms (recursive and
 * iterative) for finding various properties of them. The only
 * major difficulty I found while doing the second part of this
 * project was I couldn't come up with a way to decode an entire
 * morse code message using only one recursive method. The difficulty
 * was that upon each recursion the treeNode was set to a new value,
 * thus eliminating any way of going back to the top of the tree
 * to correctly decode the next character. I was able to circumvent
 * this by using a helper method, but it feels inelegant.
 */
//A container for useful static methods that operate on TreeNode objects.
public class TreeUtilities
{
	//the random object used by this class
	private static java.util.Random random = new java.util.Random();

	//used to prompt for command line input
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	//precondition:  t is non-empty
	//postcondition: returns the value in the leftmost node of t.
	public static Object leftmost(TreeNode t)
	{
		while(t.getLeft()!=null)
			t=t.getLeft();
		return t.getValue();
	}

	//precondition:  t is non-empty
	//postcondition: returns the value in the rightmost node of t.
	public static Object rightmost(TreeNode t)
	{
		if(t.getRight()==null)
			return t.getValue();
		return rightmost(t.getRight());
	}

	//postcondition: returns the maximum depth of t, where an empty tree
	//               has depth 0, a tree with one node has depth 1, etc
	public static int maxDepth(TreeNode t)
	{
		if(t==null)
			return 0;
		int leftDepth=maxDepth(t.getLeft());
		int rightDepth=maxDepth(t.getRight());
		return 1+(leftDepth>rightDepth?leftDepth:rightDepth);
	}

	//postcondition: each node in t has been lit up on display
	//               in a pre-order traversal
	public static void preOrder(TreeNode t, TreeDisplay display)
	{
		if(t!=null) {
			display.visit(t);
			preOrder(t.getLeft(),display);
			preOrder(t.getRight(),display);
		}
	}

	//postcondition: each node in t has been lit up on display
	//               in an in-order traversal
	public static void inOrder(TreeNode t, TreeDisplay display)
	{
		if(t!=null) {
			inOrder(t.getLeft(),display);
			display.visit(t);
			inOrder(t.getRight(),display);
		}
	}

	//postcondition: each node in t has been lit up on display
	//               in a post-order traversal
	public static void postOrder(TreeNode t, TreeDisplay display)
	{
		if(t!=null) {
			postOrder(t.getLeft(),display);
			postOrder(t.getRight(),display);
			display.visit(t);
		}
	}

	//useful method for building a randomly shaped
	//tree of a given maximum depth
	public static TreeNode createRandom(int depth)
	{
		if (random.nextInt((int)Math.pow(2, depth)) == 0)
			return null;
		return new TreeNode(new Integer(random.nextInt(10)),
			createRandom(depth - 1),
			createRandom(depth - 1));
	}

	//returns the number of nodes in t
	public static int countNodes(TreeNode t)
	{
		if(t==null)
			return 0;
		return 1+countNodes(t.getLeft())+countNodes(t.getRight());
	}

	//returns the number of leaves in t
	public static int countLeaves(TreeNode t)
	{
		if(t==null)
			return 0;
		if(t.getLeft()==null && t.getRight()==null)
			return 1;
		return countLeaves(t.getLeft())+countLeaves(t.getRight());
	}

	//precondition:  all values in t are Integer objects
	//postcondition: returns the sum of all values in t
	public static int sum(TreeNode t)
	{
		if(t==null)
			return 0;
		return (Integer) t.getValue()+sum(t.getLeft())+sum(t.getRight());
	}

	//postcondition:  returns a new tree, which is a complete copy
	//                of t with all new TreeNode objects pointing
	//                to the same values as t (in the same order, shape, etc)
	public static TreeNode copy(TreeNode t)
	{
		if(t==null)
			return t;
		TreeNode out=new TreeNode(t.getValue());
		out.setLeft(copy(t.getLeft()));
		out.setRight(copy(t.getRight()));
		return out;
	}

	//postcondition:  returns true if t1 and t2 have the same
	//                shape (but not necessarily the same values);
	//                otherwise, returns false
	public static boolean sameShape(TreeNode t1, TreeNode t2)
	{
		if((t1==null && t2!=null) ||(t2==null && t1!=null))
			return false;
		if(t1==null && t2==null)
			return true;
		return sameShape(t1.getLeft(),t2.getLeft()) && sameShape(t1.getRight(),t2.getRight());
	}

	//postcondition:  plays a game of twenty questions
	public static void twentyQuestions()
	{
		try
		{
			TreeDisplay display = new TreeDisplay();
			TreeNode knowledge = new TreeNode("Mr. Feinberg");
			display.displayTree(knowledge);
			while (true)
			{
				System.out.println("\nThink of a person or thing, and press enter");
				br.readLine();
				twentyQuestionsRound(knowledge, display);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	//postcondition:  plays a round of twenty questions, asking the user questions as it
	//                walks down the given knowledge tree, lighting up the display as it goes;
	//                modifies the tree to include information learned.
	private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
		throws IOException
	{
		throw new Error("Implement me!");
	}

	//postcondition:  returns a tree for decoding Morse code
	public static TreeNode createDecodingTree(TreeDisplay display)
	{
		TreeNode tree = new TreeNode("Morse Tree");
		display.displayTree(tree);
		insertMorse(tree, "a", ".-", display);
		insertMorse(tree, "b", "-...", display);
		insertMorse(tree, "c", "-.-.", display);
		insertMorse(tree, "d", "-..", display);
		insertMorse(tree, "e", ".", display);
		insertMorse(tree, "f", "..-.", display);
		insertMorse(tree, "g", "--.", display);
		insertMorse(tree, "h", "....", display);
		insertMorse(tree, "i", "..", display);
		insertMorse(tree, "j", ".---", display);
		insertMorse(tree, "k", "-.-", display);
		insertMorse(tree, "l", ".-..", display);
		insertMorse(tree, "m", "--", display);
		insertMorse(tree, "n", "-.", display);
		insertMorse(tree, "o", "---", display);
		insertMorse(tree, "p", ".--.", display);
		insertMorse(tree, "q", "--.-", display);
		insertMorse(tree, "r", ".-.", display);
		insertMorse(tree, "s", "...", display);
		insertMorse(tree, "t", "-", display);
		insertMorse(tree, "u", "..-", display);
		insertMorse(tree, "v", "...-", display);
		insertMorse(tree, "w", ".--", display);
		insertMorse(tree, "x", "-..-", display);
		insertMorse(tree, "y", "-.--", display);
		insertMorse(tree, "z", "--..", display);
		System.out.println("DONE!");
		return tree;
	}

	//postcondition:  inserts the given letter into the decodingTree,
	//                in the appropriate position, as determined by
	//                the given Morse code sequence; lights up the display
	//                as it walks down the tree
	private static void insertMorse(TreeNode decodingTree, String letter,
									String code, TreeDisplay display)
	{
		display.visit(decodingTree);
		if(code.length()==0) {
			decodingTree.setValue(letter);
			System.out.println("Placed "+letter);
		}
		else {
			char direction=code.charAt(0);
			if(direction=='.') {
				if(decodingTree.getLeft()==null)
					decodingTree.setLeft(new TreeNode("null"));
				insertMorse(decodingTree.getLeft(),letter,code.substring(1),display);
			}
			else if(direction=='-') {
				if(decodingTree.getRight()==null)
					decodingTree.setRight(new TreeNode("null"));
				insertMorse(decodingTree.getRight(),letter,code.substring(1),display);
			}
			else
				throw new IllegalStateException("STATE IMPOSSIBLE!");
		}
	}

	//precondition:  ciphertext is Morse code, consisting of dots, dashes, and spaces
	//postcondition: uses the given decodingTree to return the decoded message;
	//               lights up the display as it walks down the tree
	public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
	{
		display.visit(decodingTree);
		String[] letters = cipherText.split(" ");
		String out="";
		for(String s : letters) {
			System.out.println("DECODING: "+s);
			out+=decodeMorseHelper(decodingTree,s,display);
			System.out.println("DECODED: "+out);
		}
		return out;
	}
	
	//precondition:  cipherText is valid morse code for one character
	//postcondition: helps the decodeMorse program by using a binary
	//               tree to decode one character
	private static String decodeMorseHelper(TreeNode decodingTree, String cipherText, TreeDisplay display)
	{
		display.visit(decodingTree);
		if(cipherText.length()==0)
			return (String) decodingTree.getValue();
		else {
			char direction=cipherText.charAt(0);
			if(direction=='.')
				return decodeMorse(decodingTree.getLeft(), cipherText.substring(1), display);
			else if(direction=='-')
				return decodeMorse(decodingTree.getRight(), cipherText.substring(1), display);
			else
				throw new IllegalArgumentException("Invalid character: "+direction);
		}

	}

	//precondition:  expTree is an expression tree consisting of Integer objects
	//               joined by "+" and "*" operators
	//postcondition: returns the value of the expression tree
	public static int eval(TreeNode expTree)
	{
		throw new Error("Implement me!");
	}

	//precondition:  exp represents an arithmetic expression,
	//               consisting of "+", "*", paretheses and numbers
	//postcondition: returns an expression tree to represent this arithmetic expression
	public static TreeNode createExpressionTree(String exp)
	{
		throw new Error("Implement me!");
	}
}
