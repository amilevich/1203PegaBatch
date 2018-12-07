package com.homework.questions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Prime Number Printer
 * @author Blake Biskner
 * @version 1.8
 */

public class Question_9{
	
	public static void main(String[] args) {
		// Variable Initialization
		int maxNum=100;
		
		// ArrayList Initialization
		List<Integer> numList=new ArrayList<>();
		for(int i=1;i<=maxNum;i++) {
			numList.add(i);
		}
		primeSieve(numList);
		System.out.println("List of Prime Numbers");
		for(int n:numList) {
			if(n!=0) {
				System.out.print(n+" ");
			}
		}
	}
	
	/**
	 * The following method is an implementation of the Sieve of Eratosthenes; in general, this method takes in an array
	 * of numbers and marks which of these numbers is not prime (in my case I set these numbers to 0); since this array is an object
	 * the changes in this method are made to the passed in array, thus when the call completes the array of the caller is marked
	 * with zeros where the nonprime numbers once were; I declared this as a static method so I could use it again in Question 19
	 * The Sieve of Eratosthenes starts with the first prime number(2); it then multiplies 2 by (2+1), (2+2), etc thus finding all of
	 * the multiples of 2; each multiple is then marked (ie turned to 0) as it is not a prime number (it is divisible by 2); this loop
	 * continues until all multiple of 2s are marked; the next number which was not marked (3) is then selected and the process repeated;
	 * once all prime numbers greater than or equal to the max number (100) all of the nonprime numbers will have been marked (turned to zero)
	 * @param arr is the arrayList to be marked
	 * @return is void, but because arr is an object it will also be changed in the caller to an array with all nonPrime numbers set to zero
	 */
	public static void primeSieve(List<Integer> arr){
		// Variable Declaration and Initialization
		int count, notPrime;
		int primeNum=2;
		int maxNum=arr.size();
		// Iterator Instantiation
		Iterator<Integer> itr=arr.iterator();
		
		// Sieve of Eratosthenes
		while(itr.hasNext()) {
			if(primeNum!=0) { // Number is unmarked
				count=0; // Reset count
				notPrime=1; // Reset notPrime or else will only run one iteration
				while(notPrime<=maxNum) { // Must include maxNum in conditional to ensure it can be set to notPrime
					arr.set(notPrime-1, 0); // Mark value at index of notPrime number by setting it to zero
					notPrime=primeNum*(primeNum+count);
					count++;
				}
			}
			primeNum=itr.next();
		}
	}
}