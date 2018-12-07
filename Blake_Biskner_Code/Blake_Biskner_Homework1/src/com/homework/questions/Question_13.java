package com.homework.questions;

/**
 * Binary Triangle Printer
 * @author Blake Biskner
 * @version 1.13
 */

public class Question_13{
	
	public static void main(String[] args) {
		// Variable Initialization
		int count=1;
		int numRows=4; // Number of rows in triangle
		
		// Triangle Printing
		for(int i=1;i<=numRows;i++) {
			for(int j=1;j<=i;j++) {
				if((count%2)==0) { // If count is even
					System.out.print("1");
				} else { // Count is odd
					System.out.print("0");
				}
				if(j<i) {
					System.out.print(" "); // Print a space between each element but not at end of row
				}
				count++;
			}
			if(i<numRows) {
				System.out.print('\n'); // Print a newline after each row except for the last one
			}
		}
	}
}