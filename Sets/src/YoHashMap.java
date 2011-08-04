import java.util.LinkedList;
import java.util.ListIterator;
import java.util.HashSet;
import java.util.Set;

/**
 * Implements a basic chaining hash map
 * with Strings for keys and Integers
 * for values
 * @author Eric Bakan
 *
 */
public class YoHashMap {
	/**
	 * Default constructor, uses array
	 * of length 10
	 */
	@SuppressWarnings("unchecked")
	public YoHashMap() {
		array=new LinkedList[10];
		for(int i=0;i<10;i++)
			array[i]=new LinkedList<Entry>();
	}
	/**
	 * Creates array of variable size
	 * @param arraySize size of array
	 */
	@SuppressWarnings("unchecked")
	public YoHashMap(int arraySize) {
		array=new LinkedList[arraySize];
		for(int i=0;i<arraySize;i++)
			array[i]=new LinkedList<Entry>();
	}
	
	/**
	 * Returns the set of keys
	 * @return set of keys
	 */
	public Set<String> keySet() {
		Set<String> s = new HashSet<String>();
		for(int i=0;i<array.length;i++)
			for(Entry e : array[i])
				s.add(e.getKey());
		return s;
	}
	
	/**
	 * Returns whether the hash map contains a key
	 * @param key key to check
	 * @return whether the hash map contains the key
	 */
	public boolean containsKey(String key) {
		LinkedList<Entry> l=getLinkedList(key);
		for(Entry e : l)
			if(e.getKey().equals(key))
				return true;
		return false;
	}
	
	/**
	 * Gets value of given key
	 * @param key key to get value for
	 * @return value of key if it is contained, null otherwise
	 */
	public Integer get(String key) {
		Integer out=null; //null if the key is not contained
		if(containsKey(key)) {
			LinkedList<Entry> l=getLinkedList(key);
			for(Entry e : l) {
				if(e.getKey().equals(key)) {
					out=e.getValue();
					break;
				}
			}
		}
		return out;
	}
	
	/**
	 * Adds a new entry to the hash map
	 * @param key new key
	 * @param value new value
	 * @return value of the entry added
	 */
	public Integer put(String key, Integer value) {
		LinkedList<Entry> l=getLinkedList(key);
		//see if we already have the key
		Integer out=null;
		if(containsKey(key)) {
			for(Entry e : l) {
				if(e.getKey().equals(key)) {
					out=e.getValue();
					e.setValue(value);
					return out;
				}
			}
		}
		//add a new entry if not found
		l.add(new Entry(key,value));
		return out;
	}
	
	/**
	 * Removes the entry for string key
	 * @param key key of entry to be removed
	 * @return value of the key removed, null
	 * if key not in hash map
	 */
	public Integer remove(String key) {
		Integer out=0;
		if(!containsKey(key))
			return null;
		LinkedList<Entry> l=getLinkedList(key);
		ListIterator<Entry> i = l.listIterator();
		while(i.hasNext()) {
			Entry e = (Entry) i.next();
			if(e.getKey().equals(key)) {
				out=e.getValue();
				i.remove();
			}
		}
		return out;
	}
	
	/**
	 * Returns number of entries in the hash map
	 * @return number of entries in the hash map
	 */
	public int size() {
		int s=0;
		for(int i=0;i<array.length;i++)
			s+=array[i].size();
		return s;
	}
	
	/**
	 * Helper method to calculate the hash value and
	 * return the linked list the key is in or would
	 * be in
	 * @param key key to hash
	 * @return the linked list the key is in or would
	 * be in
	 */
	private LinkedList<Entry> getLinkedList(String key) {
		return array[key.length()%array.length];
	}
	
	/**
	 * Represents a key-value pair of a String
	 * and in Integer
	 */
	class Entry {
		/**
		 * Constructs an entry with a given key and value
		 * @param key key of entry
		 * @param value value of entry
		 */
		public Entry(String key, Integer value) {
			this.key=key;
			this.value=value;
		}
		/**
		 * Key of entry
		 */
		String key;
		/**
		 * Value of entry
		 */
		Integer value;
		
		/**
		 * Returns the entry's key
		 * @return the entry's key
		 */
		public String getKey() {
			return key;
		}
		
		/**
		 * Returns the entry's value
		 * @return the entry's value
		 */
		public Integer getValue() {
			return value;
		}
		
		/**
		 * Sets the entry's key
		 * @param key key to set
		 */
		public void setKey(String key) {
			this.key=key;
		}
		
		/**
		 * Sets the entry's value
		 * @param value value to set
		 */
		public void setValue(Integer value) {
			this.value=value;
		}
	}
	
	/**
	 * Array of linked lists behind the hash map
	 */
	LinkedList<Entry>[] array;
}
