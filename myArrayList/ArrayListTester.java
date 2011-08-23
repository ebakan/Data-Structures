package myArrayList;

public class ArrayListTester
{
	public static void main(String[] args){
		
		MyArrayList<Integer> t = new MyArrayList<Integer>();
		System.out.println(t.size()); 
		for(int i = 0; i<100000; i++ )
		t.add(i); 
		System.out.println(t.size()); 
		t.remove(2); 
		t.remove(20000); 
		t.set(20,10); 
		t.add(40,20); 
		System.out.println(t.size()); 
		for(int i = 0; i<42; i++ ) 
		System.out.println(t.get(i));
	}
}