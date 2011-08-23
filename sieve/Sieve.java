/*Sieve of Eratosthenes by Eric Bakan
 * Data Structures P3
 * 8/31/10
 */
package sieve;

public class Sieve {
	public static void main (String[] args) {
		//array
		boolean[] array = new boolean[1000];
		//set values to true (ignore elements 0 and 1)
		for (int i=2;i<array.length;i++) {
			array[i]=true;
		}
		//loop through each element up to sqrt(length) for effiency
		for (int i=2;i<Math.pow(array.length,.5);i++) {
			//only care about primes for efficiency
			if (array[i]) {
				//set all multiples of primes to false
				for (int j=i*2;j<array.length;j+=i) {
					array[j]=false;
				}
			}
		}
		//print the primes
		for (int i=2;i<array.length;i++) {
			if (array[i]) System.out.println(i);
		}
	}
}