public class LinkedList_Bakan {
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.addFirst(5);
		list.addFirst(4);
		list.addFirst(3);
		list.addFirst(2);
		list.addFirst(1);
		System.out.println(list);
		list.remove(0);
		System.out.println(list);
		list.remove(2);
		System.out.println(list);
		list.add(1,"Blah");
		System.out.println(list);
	}
}
