package com.assignment_one.question_2;

// Q2. Write a program to display the first 25 Fibonacci numbers beginning at 0.
public class Fibonacci {

	// fib() method prints the first n Fibonacci numbers starting with a and b
	public static void fib(int a, int b, int n){
		// If n is <= 0, stop the function early (done)
		if(n <= 0)
			return;
		
		// Print the first Fibonacci number
		System.out.println(a);
		
		// if n is 1, stop the function early (done)
		if(n == 1) 
			return;
		
		System.out.println(b);
		
		// Loop from 3 to n (already printed numbers 1 and 2)
		for(int i = 3; i <= n; i++) {
			// Sum of the 2 current numbers is the next Fibonacci number
			int c = a+b;
			System.out.println(c);
			
			// Advance a and b to the next numbers in the sequence
			a = b;
			b = c;
		}
	}
	
	public static void main(String[] args) {
		// Prints the first 25 Fibonacci numbers starting with 0
		fib(0,1,25);

	}

}
