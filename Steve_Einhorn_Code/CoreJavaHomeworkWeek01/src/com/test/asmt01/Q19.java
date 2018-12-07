package com.test.asmt01;

import java.util.ArrayList;
import java.util.List;

public class Q19 {

	public static void main(String[] args) {
		
		int limit = 10;
		int evenSum = 0;
		int oddSum = 0;
		
		List<Integer> numbers = new ArrayList<>();
		for ( int i = 1; i <= limit; i++ ) {
			numbers.add(i);
		}
		
		for ( int i = 0; i < numbers.size(); i++ ) {
			if ( numbers.get(i) % 2 == 0 ) {
				evenSum += numbers.get(i);
			}
		}
		System.out.println("Even number sum is: " + evenSum);
		
		for ( int i = 0; i < numbers.size(); i++ ) {
			if ( numbers.get(i) % 2 != 0 ) {
				oddSum += numbers.get(i);
			}
		}
		System.out.println("Odd number sum is: " + oddSum);
		
		for ( int i = 0; i < numbers.size(); i++ ) {
			if ( isPrimeNumber(numbers.get(i) ) ) {
				numbers.remove(i);
			}
		}
		System.out.println("ArrayList without Prime Numbers");
		for ( int i = 0; i < numbers.size(); i++ ) {
			System.out.println(numbers.get(i));
		}

	}
	
	static boolean isPrimeNumber(int inputNumber) {
		
	    for ( int i = 2; i < inputNumber; i++ ) {
	        if( inputNumber % i == 0 )
	            return false;
	    }
	    
	    return true;
	    
	}

}
