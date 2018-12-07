package com.test.asmt01;

public class Q13 {

	public static void main(String[] args) {
		
		int numRows = 4;
		int rowPtr = 1;
		int x = 0;
		int lineChars = 1;
		int lineCharPtr = 0;
		
		// Create 'numRows' rows.
		while ( rowPtr < numRows ) {
			
			lineCharPtr = 0;
			
			// Create 'lineChars' characters in each row.
			while ( lineCharPtr <= lineChars ) {

				// Print either a '0' or '1', depending on the remainder of 'x'.
				// Print on a new line if 'lineCharPtr' is 0.
				if ( x % 2 == 0) {
					if (lineCharPtr == 0) {
						System.out.println("0 ");
					} else {
						System.out.print("0 ");
					}
				} else {
					if (lineCharPtr == 0) {
						System.out.println("1 ");
					} else {
						System.out.print("1 ");
					}
				}
				
				x++;
				lineCharPtr++;
				
			}

			lineChars = lineCharPtr;
			rowPtr++;
			
		}

	}

}
