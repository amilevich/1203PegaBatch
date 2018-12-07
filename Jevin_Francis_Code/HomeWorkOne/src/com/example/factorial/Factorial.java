// Write a program to compute N factorial.

package com.example.factorial;

import java.util.Scanner;

public class Factorial {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		//Getting input from the user
		System.out.print("Enter a Number: ");
		
		//Checking to see if input is an int
		if(scanner.hasNextInt()) {
			int n = scanner.nextInt();
			int f = 1;
			//Finding the Factorial
			for (int x = n; x > 0; x--) {
				f = f * x;
			}
			//printing out the factorial
			System.out.println(n + " Factorial is: " + f);
		}
		else {
			System.out.println("Input Must be of type Int!");
		}
		//closing the scanner
		scanner.close();
	}
}
