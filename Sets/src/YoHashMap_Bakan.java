/**
 * Tests all the methods of the YoHashMap
 * @author ebakan
 *
 */
public class YoHashMap_Bakan {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		YoHashMap hashmap = new YoHashMap();
		hashmap.put("eric",10);
		hashmap.put("rice",9);
		hashmap.put("nick", 11);
		hashmap.put("bobby",14);
		System.out.println("Hashmap has eric: "+hashmap.containsKey("eric"));
		System.out.println("Hashmap has asdf: "+hashmap.containsKey("asdf"));
		System.out.println("value of eric: "+hashmap.get("eric"));
		System.out.println("value of asdf: "+hashmap.get("asdf"));
		System.out.println("removing eric ");
		hashmap.remove("eric");
		System.out.println("value of eric: "+hashmap.get("eric"));
		System.out.println("size: "+hashmap.size());
		System.out.println("key set: "+hashmap.keySet());
	}

}
