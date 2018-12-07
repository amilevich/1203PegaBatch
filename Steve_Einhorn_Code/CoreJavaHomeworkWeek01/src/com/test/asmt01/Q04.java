package com.test.asmt01;

public class Q04 {

	public static void main(String[] args) {
		
		// number to be factorialized.
		int n = 4;
		
		// loop thru numbers decrementally, starting at 'n'.
		for (int i=n; i-1>=1; i--) {
			
			// multiply current n by n-1.
			n = n * (i-1);
			
		}
		
		System.out.println(n);

	}

}
