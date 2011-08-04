package stack;
import java.util.LinkedList;
import java.util.EmptyStackException;
/**
 * Basic implementation of a stack using a
 * LinkedList to store data
 * 
 * @author Eric Bakan
 *
 * @param <E> type of object stack holds
 */
class Stack<E> {
	/**
	 * Default constructor, initializes LinkedList
	 */
	public Stack() {
		list=new LinkedList<E>();
	}
	/**
	 * Returns whether stack is empty or not
	 * @return whether stack is empty or not
	 */
	public boolean isEmpty() {
		return list.size()==0;
	}
	/**
	 * Pushes an object to the top of the stack
	 * @param e object to be pushed
	 * @return pushed object
	 */
	public E push( E e ) {
		list.addFirst(e);
		return list.getFirst();
	}
	/**
	 * Pops an object off the top of the stack
	 * @return popped object
	 */
	public E pop() {
		if(isEmpty())
			throw new EmptyStackException();
		return list.removeFirst();
	}
	/**
	 * Returns the object at the top of the stack
	 * without removing it
	 * @return first object on the stack
	 */
	public E peek() {
		if(isEmpty())
			throw new EmptyStackException();
		return list.getFirst();
	}
	private LinkedList<E> list;
}
/**
 * Tester class for Stack
 * @author Eric Bakan
 *
 */
public class Stack_Bakan {
	public static void main(String[] args) {
		Stack<String> dishes = new Stack<String>();
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing H, E, L, L, O");
		dishes.push("H");
		dishes.push("E");
		dishes.push("L");
		dishes.push("L");
		dishes.push("O");

		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()){
			System.out.println("Popping: "+dishes.pop());
		}
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 1");
		dishes.push("1");
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 2, 3, 4, 5");
		dishes.push("2");
		dishes.push("3");
		dishes.push("4");
		dishes.push("5");
		System.out.println("The top element is: " + dishes.peek());
		System.out.println("Removing " + dishes.pop() );
		System.out.println("Removing "+ dishes.pop() );
		System.out.println("Now pushing Last");
		dishes.push("Last");
		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()){
			System.out.println("Popping: " +dishes.pop());
		}
	}
}