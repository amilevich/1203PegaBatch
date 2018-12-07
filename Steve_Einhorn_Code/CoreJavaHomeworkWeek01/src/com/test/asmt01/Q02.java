package com.test.asmt01;

public class Q02 {

	public static void main(String[] args) {

		int numbers = 25;
		int firstNumber = 0;
		int secondNumber = 1;
		
	    System.out.print("First " + numbers + " terms: ");

	    /**
	     * loop sequentially thru numbers, number times, beginning at 0.
	     */
	    for (int i = 1; i <= numbers; ++i) {
	    	
	    	// print 'firstNumber'.
	    	System.out.print(firstNumber + " + ");

	    	// sum the variables, 'firstNumber' and 'secondNumber'.
	        int sum = firstNumber + secondNumber;
	        
	        // set the 'firstNumber' variable to the 'secondNumber' variable.
	        firstNumber = secondNumber;
	        
	        // set the 'secondNumber' variable to the sum of 'firstNumber' and 'secondNumber'.
	        secondNumber = sum;
	    }

	}

}
