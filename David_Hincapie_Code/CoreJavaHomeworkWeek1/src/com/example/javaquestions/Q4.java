package com.example.javaquestions;

public class Q4 {
	// Q4. Write a program to compute N factorial.

	public static void main(String[] args) {

		int num = 4;
		System.out.println("Factorial " + num + " : " + factorial(num));
	}

	public static int factorial(int num) {
		int result;
		if (num == 0) {
			result = 1;
		} else {
			result = num * factorial(num - 1);
		}
		return result;
	}
}
