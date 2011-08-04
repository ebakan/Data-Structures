import java.util.ArrayList;
/**
 * Heap implementation
 * Provides basic adding, root-removing, and toString methods
 * Uses an ArrayList to store the heap
 * NOTE: this *could* be implemented as an array,
 * but an ArrayList was used for the sake of extensibility
 * To use an array, a method for expanding and contracting
 * the array, including copying the array's elements
 * would have to be implemented.
 * @author Eric Bakan
 *
 */
public class Heap {
	/**
	 * Default constructor
	 */
	@SuppressWarnings("rawtypes")
	public Heap() {
		this.array=new ArrayList<Comparable>();
	}
	/**
	 * Allows for a default size of the ArrayList
	 * @param size default ArrayList size
	 */
	@SuppressWarnings("rawtypes")
	public Heap(int size) {
		this.array=new ArrayList<Comparable>(size);
	}
	/**
	 * Expands the ArrayList and adds a Comparable
	 * to the end, then fixes the heap
	 * @param c Comparable to add
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void add(Comparable c) {
		array.add(c);
		int childIndex=getLastIndex();
		int parentIndex=getParentIndex(childIndex);
		while(parentIndex>=0 && array.get(parentIndex).compareTo(array.get(childIndex))<0) {
			swap(parentIndex, childIndex);
			childIndex=parentIndex;
			parentIndex=getParentIndex(childIndex);
		}
	}
	/**
	 * Removes the root and maintains the max-heap property
	 * @return the value at the root
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Comparable removeRoot() {
		if(isEmpty())
			return null;
		if(array.size()==1)
			return array.remove(0);
		Comparable root=array.get(0);
		array.set(0,array.remove(getLastIndex()));
		int parent=0;
		while(true) {
			int leftChild=getLeftChildIndex(parent);
			int rightChild=getRightChildIndex(parent);
			if(leftChild>=array.size())
				break;
			int maxChild=leftChild;
			if(rightChild<array.size() && array.get(rightChild).compareTo(array.get(leftChild))>0)
				maxChild=rightChild;
			if(array.get(parent).compareTo(array.get(maxChild))<0) {
				swap(parent,maxChild);
				parent=maxChild;
			}
			else
				break;
			
		}
		return root;
	}
	/**
	 * Returns a string representation of
	 * the heap
	 * NOTE: only prints the first four lines
	 * because of certain walls hit trying to 
	 * evenly space anything more than that.
	 * Instead I fine-tuned the four-line
	 * printing. 
	 * @return String representation of object
	 */
	public String toString() {
		int currLevel=0;
		int currIndex=0;
		String out="";
		while(currIndex<array.size()) {
			if(Math.pow(2,currLevel)<=currIndex+1) {
				out+="\n";
				currLevel++;
			}
			out+=array.get(currIndex)+" ";
			currIndex++;
		}
		return out;
	}
	/**
	 * Returns whether the heap is empty
	 * @return whether the heap is empty or not
	 */
	public boolean isEmpty() {
		return array.size()==0;
	}
	/**
	 * Returns the last index of the array
	 * @return the last index of the array
	 */
	private int getLastIndex() {
		return array.size()-1;
	}
	/**
	 * Returns the index of the first child
	 * of the object at array parentIndex
	 * @param parentIndex index of the parent
	 * @return index of the first child
	 */
	private int getLeftChildIndex(int parentIndex) {
		return parentIndex*2+1;
	}
	/**
	 * Returns the index of the second child
	 * of the object at array parentIndex
	 * @param parentIndex index of the parent
	 * @return index of the second child
	 */
	private int getRightChildIndex(int parentIndex) {
		return parentIndex*2+2;
	}
	/**
	 * Returns the index of the parent of a
	 * child index
	 * @param childIndex the index of the child
	 * @return the index of the parent
	 */
	private int getParentIndex(int childIndex) {
		return (childIndex-1)/2;
	}
	/**
	 * Helper method to swap the objects at two
	 * given indices
	 * @param first first index
	 * @param second second index
	 */
	@SuppressWarnings("rawtypes")
	private void swap(int first, int second) {
		Comparable tmp=array.get(first);
		array.set(first,array.get(second));
		array.set(second,tmp);
	}
	/**
	 * ArrayList that stores the Heap
	 */
	@SuppressWarnings("rawtypes")
	private ArrayList<Comparable> array;
}
