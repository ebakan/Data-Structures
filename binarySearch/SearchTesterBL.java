package binarySearch;

//SearchTester

//@author Lindemann

import java.util.Scanner;

public class SearchTesterBL {

	public static void main(String[] args) {
		// Ask the user for search string
		Scanner myScan = new Scanner(System.in);
		System.out.println("Enter name: ");
		String input = myScan.nextLine();
		int index = binSearch( getList(), input );
		System.out.println(input + " found in list at index: " + index);
	}
	public static String[] getList() {
		String[] names = { "Adams", "Amarillas", "Baxter", "Duong", "Giradaux", 
                                          "Gonzalez", "Hansbrough", "Kniffen", 
                                          "Lambert", "Mathurin", "McCullough", "Molina", 
                                          "Reyerson", "Ward", "Wolf", "Wong", "Xu", "Zabinski" };
		return names;
	}
	public static int binSearch(String[] array, String input) {
		int low=0, high=array.length, mid=(high+low)/2;
		int result = -1;
		while (mid < array.length && mid >= 0) {
			int compare = array[mid].compareTo(input);
			if (compare == 0) {
				return mid;
			} else if (compare > 0) {
				high=mid;
			} else {
				low=mid+1;
			}
			mid=(high+low)/2;
		}
		return result;
		/*
		if(array == null || array.length<=1) return -1;
		int mid = array.length / 2;
		int comparison=array[mid].compareTo(input);
		if (comparison==0) {
			return mid;
		} if (comparison < 0) {
			return mid + binSearch(divide(array, mid, array.length), input);
		} else{
			return binSearch(divide(array, 0, mid), input);
		}*/
		
	}
	public static String[] divide(String[] array, int min, int max) {
		//if(min-max==0) return null;
		String temp[]=new String[max-min];
		for(int i=min;i<max;i++) {
			temp[i-min]=array[i];
		}
		return temp;
	}
}