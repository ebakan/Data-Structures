import java.util.NoSuchElementException;

/**
A linked list is a sequence of nodes with efficient
element insertion and removal. This class 
contains a subset of the methods of the standard
java.util.LinkedList class.
 */
public class LinkedList {

	private Node first;

	private class Node { 
		public Object data;
		public Node next;
	}

	/**
	 * Constructs an empty linked list.
	 */
	public LinkedList() {
		first = null;
	}

	public Object remove(int index) {
		if(index<0)
			throw new IllegalArgumentException();
		try {
			if(index==0) {
				first=first.next;
				return first;
			}
			else {
				Node n=first;
				for(int i=1;i<index;i++)
					n=n.next;
				n.next=n.next.next;
				return n;
			}
		}
		catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public boolean remove(Object o) {
		try {
			Node n=first;
			if(n.data.equals(o))
				n.next=n.next.next;
			else {
				while(!n.next.data.equals(o))
					n=n.next;
				n.next=n.next.next;
			}
			return true;
		}
		catch (NullPointerException e) {
			return false;
		}
	}
	
	public void add(int index, Object o) {
		if(index<0)
			throw new IllegalArgumentException();
		if(index==0) {
			Node n=first;
			first=new Node();
			first.data=o;
			first.next=n;
		}
		else {
			Node n=first;
			for(int i=1;i<index;i++)
				n=n.next;
			Node newNode=new Node();
			newNode.data=o;
			newNode.next=n.next;
			n.next=newNode;
		}
		
	}

	/**
	 * Returns the first element in the linked list.
	 * @return the first element in the linked list
	 */
	public Object getFirst() {
		if (first == null)
			throw new NoSuchElementException();
		return first.data;
	}

	/**
	 * Removes the first element in the linked list.
	 * @return the removed element
	 */
	public Object removeFirst() {
		if (first == null)
			throw new NoSuchElementException();
		Object element = first.data;
		first = first.next;
		return element;
	}

	/**
	 * Adds an element to the front of the linked list.
	 * @param element the element to add
	 */
	public void addFirst(Object element) {
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = first;
		first = newNode;
	}
	
	public String toString() {
		Node n=first;
		String s="";
		while(n!=null) {
			s+=n.data.toString()+"\n";
			n=n.next;
		}
		return s;
	}

}