package myArrayList;

/**
 * Basic implementation of an ArrayList
 * (Does not extend however)
 * @author Eric Bakan
 * Last Edited 9/23/10
 */
public class MyArrayList<T> {
	
	/**
	 * Default constructor: size 1024
	 */
	public MyArrayList() {
		this(1024);
	}
	
	/**
	 * Set custom size
	 * @param len custom length of array
	 */
	public MyArrayList(int len) {
		array=new Object[len];
	}
	
	/**
	 * Append a number to the end of the array
	 * @param num number to be appended to the array
	 */
	public void add(T val) {
		checkExtension();
		array[length++]=val;
	}
	
	/**
	 * Inserts a number at a certain index, move all the
	 * other values up one index
	 * @param index index to add the number at
	 * @param num value to be inserted
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T val) {
		checkIndex(index);
		checkExtension();
		for(int i=length++;i>=index;i--)
			array[i+1]=(T)array[i];
		array[index]=val;
	}
	
	/**
	 * Checks to see if elem is in the array
	 * @param elem element to be checked
	 * @return true if in array, else false
	 */
	@SuppressWarnings("unchecked")
	public boolean contains(T obj) {
		for(int i=0;i<length;i++)
			if (obj.equals((T)array[i]))
				return true;
		return false;
	}

	/**
	 * Return the value of the given index
	 * @param index index to be retrieved
	 * @return value of index
	 */
	@SuppressWarnings("unchecked")
	public T get(int index) {
		checkIndex(index);
		return (T) array[index];
	}
	
	/**
	 * Finds index of an object
	 * returns index or -1 if not found
	 * @param obj object to be found
	 * @return index or -1 if not found
	 */
	@SuppressWarnings("unchecked")
	public int indexOf(T obj) {
		if(contains(obj))
			for(int i=0;i<length;i++)
				if(obj.equals((T)array[i]))
					return i;
		return -1;
	}
	
	/**
	 * Remove value at index, shift all
	 * values down one
	 * @param index index to be removed
	 * @return removed value
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		T out = get(index);
		for(int i=index;i<length-1;i++)
			set(i,(T) array[i+1]);
		length--;
		return out;
	}
	
	/**
	 * Returns true if the object is present
	 * and removed, false if it isn't present
	 * If object found, removes the first
	 * occurrence of it
	 * @param obj object to be found and removed
	 * @return true if found and removed, else false
	 */
	public boolean remove(T obj) {
		int i=indexOf(obj);
		if(i<0) return false;
		remove(i);
		return true;
	}
	
	/**
	 * Set an index to a value
	 * @param index index to be changed
	 * @param num value for index to be changed to
	 */
	public void set(int index, T val) {
		checkIndex(index);
		array[index]=val;
	}

	/**
	 * Return size of array
	 * @return size of array
	 */
	public int size() {
		return length;
	}
	
	/**
	 * Double size of array
	 */
	private void extend() {
		Object[] newArray = new Object[array.length*2];
		for(int i=0;i<array.length;i++)
			newArray[i]=array[i];
		array=newArray;
	}
	
	/**
	 * Check to see if an index is valid
	 * @param index index to be checked
	 */
	private void checkIndex(int index) {
		if (index<0 || index>=length) throw new IndexOutOfBoundsException(String.format("%d out of bounds",index));
	}
	
	/**
	 * Checks to see if the array should be extended
	 * Used when adding elements
	 */
	private void checkExtension() {
		if(length==array.length)extend(); 
	}

	private Object[] array;
	private int length; //visible length of array
}
