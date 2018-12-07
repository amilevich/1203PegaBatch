package com.assignment_one.question_17;

import java.util.Scanner;

/*
 * Q17. Write a program that calculates the simple interest on the principal, rate of interest and number of years provided by the user. 
 * Enter principal, rate and time through the console using the Scanner class.
 * Interest = Principal * Rate * Time
 */
public class SimpleInterest {

	// Interest = principal * rate * time
	public static double interest(double principal, double rate, int time) {
		// rate is entered as a percentage so must be converted to a decimal before
		// interest is calculated
		rate = rate / 100;
		return principal * rate * time;
	}

	public static void main(String[] args) {

		// Scanner used to read in user input from the console
		Scanner s = new Scanner(System.in);
		System.out.println("Simple Interest Calculator!");
		double principal = 0.0;
		double rate = 0.0;
		int time = 0;

		// Flag used to loop as long as the user has entered invalid input
		boolean invalidInput = true;

		// Loops as long as user hasn't entered in a double for the principal amount
		while (invalidInput) {
			System.out.print("Enter principal: ");
			if (s.hasNextDouble()) {
				principal = s.nextDouble();
				invalidInput = false;
			} else {
				System.out.println("Error: invalid input. Please enter a decimal number.");
				// Pops off what the user typed in the console
				s.next();
			}
		}

		// Flag reset for next input
		invalidInput = true;
		// Loops as long as user hasn't entered in a double for the rate percentage
		while (invalidInput) {
			System.out.print("Enter rate (%): ");
			if (s.hasNextDouble()) {
				rate = s.nextDouble();
				invalidInput = false;
			} else {
				System.out.println("Error: invalid input. Please enter a decimal number.");
				// Pops off what the user typed in the console
				s.next();
			}
		}
		// Flag reset for next input
		invalidInput = true;
		while (invalidInput) {
			System.out.print("Enter number of years: ");
			if (s.hasNextInt()) {
				time = s.nextInt();
				invalidInput = false;
			} else {
				System.out.println("Error: invalid input. Please enter a number.");
				// Pops off what the user typed in the console
				s.next();
			}
		}

		// Outputs the inputs provided as well as the simple interest calculated
		System.out.println("$" + principal + " at " + rate + "%" + " after " + time + " years = $"
				+ interest(principal, rate, time) + " interest");

	}

}
