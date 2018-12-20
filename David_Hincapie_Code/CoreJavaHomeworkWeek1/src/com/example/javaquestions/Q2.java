package com.example.javaquestions;

public class Q2 {
	// Q2. Write a program to display the first 25 Fibonacci numbers beginning at 0.

	public static void main(String[] args) {

		for (int i = 0; i < 25; i++) {

			System.out.println(fib(i));
		}
	}

	static int fib(int n) {
		if (n <= 1) {
			return n;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}

}
