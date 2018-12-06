package com.assignment_one.question_4;

// Q4. Write a program to compute N factorial.
public class Factorial {

	// factorial() returns n factorial
	// Precondition: n >= 0 as per the defined domain of factorial
	// for n<0, 1 is returned as a 'default' value
	public static long factorial(int n) {
		// Default value for the running factorial is 1, as 0 would result in 
		// 0 as the result of consecutive products 
		long factorial = 1;
		
		// i loops from n to 1, decrementing after each iteration to emulate
		// the common factorial notation
		// n * n-1 * n-2 * ... * 3 * 2 * 1
		for (int i = n; i > 0; i--) {
			factorial = factorial * i;
		}
		
		// The resulting factorial is returned
		return factorial;
	}

	public static void main(String[] args) {

		// Prints n factorial
		int n = 4;
		System.out.println(factorial(n));
	}

}
