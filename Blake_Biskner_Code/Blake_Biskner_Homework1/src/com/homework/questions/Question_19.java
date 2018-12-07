package com.homework.questions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Integer ArrayList Manipulator
 * @author Blake Biskner
 * @version 1.19
 */

public class Question_19{
	
	public static void main(String[] args) {
		// Variable Initialization
		int maxNum=10; // Set arrayList size
		int evenCount=0;
		int oddCount=0;
		// ArrayList Instantiation and Initialization
		List<Integer> arrList=new ArrayList<>();
		for(int i=1;i<=10;i++) {
			arrList.add(i);
		}
		System.out.println(arrList);
		// Instantiate Iterator
		Iterator<Integer> itr=arrList.iterator();
		// Determine sum of odd and even numbers in ArrayList
		for(Integer i:arrList) {
			if((i%2)==0) { // Integer element is even
				evenCount+=i;
			} else { // Integer is odd
				oddCount+=i;
			}
		}
		System.out.println("Sum of Even Numbers= "+evenCount);
		System.out.println("Sum of Odd Numbers= "+oddCount);
		// Employ Sieve of Eratosthenes with method from Question_9
		Question_9.primeSieve(arrList);
		// Iterate through ArrayList (since I am removing from ArrayList while iterating I must use iterator)
		// If use for loop will get ConcurrentModificationException
		while(itr.hasNext()) {
			if(itr.next()==0) {
				itr.remove(); // Remove nonprime
			}
		}
		System.out.println(arrList);
	}
}
		
