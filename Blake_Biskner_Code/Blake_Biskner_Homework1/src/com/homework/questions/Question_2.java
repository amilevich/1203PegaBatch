package com.homework.questions;

/**
 * Fibonacci Numbers
 * 
 * @author Blake Biskner
 * @version 1.2
 */

public class Question_2 {
	// Class Constants
	private static final int FIB_NUMBERS = 25;
	private static final int NUM_1 = 0;
	private static final int NUM_2 = 1;

	/**
	 * Fibonacci calculator which adds to provided numbers
	 * 
	 * @param num1
	 *            is the smaller number
	 * @param num2
	 *            is the larger
	 * @param i
	 *            is the counter stating which Fibonacci number is being calculated
	 * @return
	 */
	public static void fib(int num1, int num2, int i) {
		if (i <= FIB_NUMBERS) { // Calculates up to the 25th Fibonacci number
			int fibNum = num1 + num2;
			System.out.println(fibNum);
			i++;
			fib(num2, fibNum, i);
		}
	}

	public static void main(String[] args) {
		int count = 3; // Begins counter at third Fibonacci number sibce the first two (0 and 1) are
						// provided
		System.out.println(NUM_1);
		System.out.println(NUM_2);
		fib(NUM_1, NUM_2, count);
	}
}
