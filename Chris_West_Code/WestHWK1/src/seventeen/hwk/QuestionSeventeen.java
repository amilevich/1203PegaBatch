package seventeen.hwk;

import java.util.Scanner;

public class QuestionSeventeen {
	/*
	 * The problem is Question 17:
	 * 
	 * Write a program that calculates the simple interest on the principal, rate of
	 * interest and number of years provided by the user. Enter principal, rate and
	 * time through the console using the Scanner class. Interest = Principal* Rate*
	 * Time
	 * 
	 */

	/*
	 * (1) In the main method I have a user entering in the input as a number that's
	 * taken in as a double in-case the user enters in a decimal.
	 * 
	 * (2) Then I have a method called calculateInterest that times the principal,
	 * rate, and time(year's) together to get the value. Then it's returns the value
	 * to where the method was called.
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) { // Look at (1)
		Scanner input = new Scanner(System.in);
		double principal, rate, time;
		System.out.print("Enter principal: ");
		principal = input.nextDouble();

		System.out.print("\nEnter rate: ");
		rate = input.nextDouble();

		System.out.print("\nEnter number of years: ");
		time = input.nextDouble();
		input.close();
		System.out.print("\nInterest: " + calculateInterest(principal, rate, time));

	}

	public static double calculateInterest(double p, double r, double t) { // Look at (2)
		return p * r * t;
	}

}
