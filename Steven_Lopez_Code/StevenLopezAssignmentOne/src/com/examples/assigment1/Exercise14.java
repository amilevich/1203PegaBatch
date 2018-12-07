package com.examples.assigment1;

import java.time.LocalDate;

public class Exercise14 {
	// Q14.
	// Write a program that demonstrates the switch case. Implement the following
	// functionalities in the cases:java
	// Case 1: Find the square root of a number using the Math class method.
	// Case 2: Display today’s date.
	// Case 3: Split the following string and store it in a string array.
	// “I am learning Core Java”

	public static void main(String[] args) {

		double n = 100;
		int choice = 3;
		switch (choice) {
		case 1:
			//Case one: the square root of n is found by using the Match class method sqrt(n).
			System.out.println("The square root of " + n + " is " + Math.sqrt(n));
			break;
		case 2:
			//Case two: Java 8's LocalDate class is used to print the current date. 
			System.out.println("Today's date is " + LocalDate.now());
			break;
		case 3:
			//The string is split by converting it into a String array
			String arr = "I am learning Core Java";
			String[] str = arr.split(" ");
			System.out.println("\"I am learning Core Java\" in a String array: ");
			for(String s : str) {
				System.out.println(s);
			}
			break;
		default:
			System.out.println("Does not do anything");

		}
	}
}
