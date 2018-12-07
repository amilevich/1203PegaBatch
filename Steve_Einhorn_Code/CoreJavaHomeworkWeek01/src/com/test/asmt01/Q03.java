package com.test.asmt01;

public class Q03 {

	public static void main(String[] args) {

		// the string to be reversed in place.
		String reverseMe = "hello";
		
		// loop thru entire string, 
		for (int i = 0; i < reverseMe.length(); i++) {
			
			// create new 'reverseMe' beginning w current iteration of 0.
			//   1.  string from 2nd position thru 'reverseMe' length minus current iteration.
			//   2.  'reverseMe' char in 1st position.
			//   3.  string from 'reverseMe' length minus current iteration thru length.
			//
			// iteratively, make the 1st char the last, and the rest first.
		    reverseMe = reverseMe.substring(1, reverseMe.length() - i)
		    		  + reverseMe.substring(0, 1)
		    		  + reverseMe.substring(reverseMe.length() - i, reverseMe.length());
		 
		}
		
		System.out.println(reverseMe);
		
	}

}
