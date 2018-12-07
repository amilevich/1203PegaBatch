// Find the minimum of two numbers using ternary operators.

package com.example.ternaryoperators;

import java.util.Scanner;

public class TernaryOperators {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Minimum Finder\n");
		//Asking for user input
		System.out.println("Enter the First Number: ");
		int a = scanner.nextInt();
		System.out.println("Enter the Second Number: ");
		int b = scanner.nextInt();

		//Comparing two values
		int min = (a < b) ? a : b;
		System.out.println("The minimum of " + a + " and " + b + " is: " + min);
		scanner.close();
	}
}
