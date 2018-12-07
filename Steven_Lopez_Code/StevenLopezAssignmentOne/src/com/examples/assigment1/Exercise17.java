package com.examples.assigment1;

import java.util.Scanner;

public class Exercise17 {
	// Q17. Write a program that calculates the simple interest on the principal,
	// rate of interest and number of years provided by the user. Enter principal,
	// rate and time through the console using the Scanner class.
	// Interest = Principal* Rate* Time

	public static void main(String[] args) {
		boolean numeric;
		double rate = 0;
		int years = 0;
		double principal = 0;
		Scanner scan = new Scanner(System.in);
		//do-while with try-catch to validate for correct user input
		do {
			numeric = true;
			try {
				System.out.print("Principal(P): $");
	            principal = Double.parseDouble(scan.next());
	        } catch (NumberFormatException ex) {
	            numeric = false;
	            System.out.println("Error: Invalid input!");
	        }
		} while (!numeric);
		
		//do-while with try-catch to validate for correct user input
		do {
			numeric = true;
			try {
				System.out.print("Rate (R): %");
	            rate = Double.parseDouble(scan.next());
	        } catch (NumberFormatException ex) {
	            numeric = false;
	            System.out.println("Error: Invalid input!");
	        }
		} while (!numeric);
		
		//do-while with try-catch to validate for correct user input
		do {
			numeric = true;
			try {
				System.out.print("Time (t), years:");
				years = Integer.parseInt(scan.next());
	        } catch (NumberFormatException ex) {
	            numeric = false;
	            System.out.println("Error: Invalid input!");
	        }
		} while (!numeric);
		
		scan.close();
		//Calculate the interest and present it to the user
		System.out.println("The interest is $" + principal * (rate/100) * years);
	}
}
