package com.test.asmt01;

public class Q06 {

	public static void main(String[] args) {

		int n = 100;
		int m = 0;

		// set 'm' to the outcome of ('n'/2)*2 Not Rounded.
		m = ( n/2 ) * 2;
		
		// if 'm' equals 'n', then it's an even number.
		if ( m == n ) {
			System.out.println(n + " is an even number.");
		} else {
			System.out.println(n + " is an odd number.");
		}
		
	}

}
