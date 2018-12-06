package com.assignment_one.question_6;

//  Q6. Write a program to determine if an integer is even without using the modulus operator (%)
public class IsEven {

	// isEven determines if the given integer n is even without using mod
	public static boolean isEven(int n) {

		/*
		 * n is bit-wise and'ed with the integer 1 as a mask if the result is 1 then n
		 * is odd (has a 1 in the 1s place binary) else, n is even (has a 0 in 1s place
		 * binary)
		 */
		return (n & 1) == 1 ? false : true;
	}

	public static void main(String[] args) {
		// n is the number to be evaluated (even or odd)
		int n = 303;

		if (isEven(n)) {
			System.out.println(n + " is even");
		} else {
			System.out.println(n + " is odd");
		}
	}

}
