package com.test.asmt01;

public class Q09 {

	public static void main(String[] args) {
		
		int limit = 101;
		
		for ( int i = 1; i <= limit; i++ ) {
			
			boolean isPrime;
			isPrime = checkIfPrime(i);
			
			if (isPrime) {
				System.out.print(i + ", ");
			}
			
		}

	}
	
	private static boolean checkIfPrime(int currentNbr) {
		
		for( int i = 2; i < currentNbr; i++ ) {
			
	        if( currentNbr % i == 0 ) {
	            return false;
	        }
	        
	    }
		
	    return true;
	}

}
