package com.homework1;

import java.util.ArrayList;

public class Question9 {
	
	public static boolean isPrime(int x) {
		
		// handle '2' base case
		if (x == 2)
			return true;
		
		// loop from 1 to (num/2) and check if the number passed in 
		// is divisible by the number in the current iteration
		for(int i=2; i<(x/2 + 1); i++) {
			if (x % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		ArrayList<Integer> storeInts = new ArrayList<Integer>();
		
		for(int i=1; i<=100; i++) {
			storeInts.add(i);
			if (isPrime(i))
				System.out.print(i + ", ");
		}
	}
	
}
