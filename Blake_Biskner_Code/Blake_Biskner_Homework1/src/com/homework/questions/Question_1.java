package com.homework.questions;

/**
 * Bubble Sort
 * 
 * @author Blake Biskner
 * @version 1.1
 */

public class Question_1 {

	public static void main(String[] args) {
		// Variable Declaration and Initialization
		int currentInt, nextInt; // Declare outside loop to save memory
		int array[] = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		int numIter = (int) Math.ceil(array.length / 2.0);
		// Since comparing 1,2 is same as 2,1 we only need to iterate over half of the
		// array
		// If array length is odd we must divide the length by 2 and round up, thus we
		// use the ceil method
		// Since ceil method accepts double and returns double we divide by 2.0 and cast
		// to int respectively
		int modArrayLength = array.length - 1;
		// Reduce array length in for loop to avoid out of bounds error
		// Since we will check the current index and the current index+1

		// For Loop
		for (int i = 1; i <= numIter; i++) { // Iterates over half of the array
			for (int j = 0; j < modArrayLength; j++) {
				currentInt = array[j];
				nextInt = array[j + 1];
				if (currentInt > nextInt) { // Compares adjacent elements and ensures larger value is to the right
					array[j] = nextInt;
					array[j + 1] = currentInt;
				}
			}
		}
		// Display Code
		for (int i : array) {
			System.out.print(i);
		}
	}
}
