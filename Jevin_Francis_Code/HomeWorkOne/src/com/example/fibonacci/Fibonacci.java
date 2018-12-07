//Write a program to display the first 25 Fibonacci numbers beginning at 0.

package com.example.fibonacci;

public class Fibonacci {
	public static void main(String[] args) {
		// First 2 values
		int z = 0;
		int o = 1;
		int num = 1;
		System.out.print("Fibonacci Numbers:" + z + " " + o + " ");

		// rest if the numbers
		for (int x = 0; x < 23; x++) {
			// Printing the number
			System.out.print(num + " ");
			// swapping data and updating new values
			z = num;
			num += o;
			o = z;
		}
	}
}
