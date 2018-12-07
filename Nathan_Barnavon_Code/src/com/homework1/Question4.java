package com.homework1;

public class Question4 {
	
	public static int computeFactorial(int n) {
		int toReturn = 1;
		
		// loop through all numbers up to n and multiply them together
		for (int i=n; i>0; i--) {
			toReturn = toReturn * i;
		}
		
		return toReturn;
	}
	
	public static void main(String[] args) {
		System.out.println(computeFactorial(6));
	}

}
