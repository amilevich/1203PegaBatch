package com.homework1;

// I assume the triangle alternates between adding 1's and 0's
// onto the left and right side.

public class Question13 {
	
	public static void main(String[] args) {
		
		// base case start with 0
		String lineInTriangle = "0";
		System.out.println(lineInTriangle);
		
		int desiredLines = 4;

		// variable i calculates where to add the number
		// variable j calculates which number to add
		for (int i=1, j=1; i<desiredLines; i++) {
			
			if (i % 2 == 1) {
				if (j == 1) {
					lineInTriangle = "1".concat(lineInTriangle);
					System.out.println(lineInTriangle);
				} else {
					lineInTriangle = "0".concat(lineInTriangle);
					System.out.println(lineInTriangle);
				}
			} 
			
			// after adding the number to the right, make sure to switch the number added
			else {
				if (j == 1) {
					lineInTriangle = lineInTriangle.concat("1");
					System.out.println(lineInTriangle);
					j = 0;
				} else {
					lineInTriangle = lineInTriangle.concat("0");
					System.out.println(lineInTriangle);
					j = 1;
				}
			}
		}
	}
	
}
