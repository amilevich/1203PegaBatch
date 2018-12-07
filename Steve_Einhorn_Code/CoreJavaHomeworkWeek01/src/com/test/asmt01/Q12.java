package com.test.asmt01;

public class Q12 {

	public static void main(String[] args) {
		
		int limit = 100;
		int[] numbers = new int[limit];
		
		// load numbers 1 thru (length of 'numbers') into the 'numbers' array.
		for ( int i = 0; i < numbers.length; i++ ) {
			numbers[i] = i + 1;
		}
		
		// print just the even numbers in the 'numbers' array.
		for ( int number : numbers ) {
			if ( number % 2 == 0 ) {
				System.out.println(number);
			}
		}

	}

}
