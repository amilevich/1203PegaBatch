package com.homework1;

import java.util.ArrayList;
import java.util.Iterator;

public class Question19 {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		
		// loop through integers 1-10 and store in the arraylist
		for(int i=1; i<=10; i++) {
			nums.add(i);
		}
		// display the arraylist
		System.out.println(nums.toString());

		// loop through the arraylist and accumulate the sum in evenSum variable
		int evenSum = 0;
		for (int x : nums) {
			if (x % 2 == 0)
				evenSum = evenSum + x;
		}
		System.out.println(evenSum);
		
		// loop through the arraylist and accumulate the sum in oddSum variable
		int oddSum = 0;
		for (int x : nums) {
			if (x % 2 == 1)
				oddSum = oddSum + x;
		}
		System.out.println(oddSum);
		
		/*
		 * The method commented below was the first attempt;
		 * however, removing and accessing an index in a loop
		 * can result in some values remaining unchecked
		 */
		
//		for (int i=0; i<nums.size(); i++) {
//			if (Question9.isPrime(nums.get(i)))
//				nums.remove(i);
//		}
		
		// Use an iterator over the list because its remove method is safe
		Iterator<Integer> it = nums.iterator();
		while(it.hasNext()) {
			
			// 
			if(Question9.isPrime(it.next()))
				it.remove();
		}
		
		System.out.println(nums.toString());
	}
	
	
}
