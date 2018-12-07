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

		/*
		 * try-with-resources used with scanner as the user's input can't be guaranteed
		 * to be numbers, causing an exception to be thrown Generally speaking unchecked
		 * exceptions should not be caught but in this case it makes sense to catch
		 * rather than code around
		 */
		try (Scanner s = new Scanner(System.in)) {
			System.out.println("Simple Interest Calculator!");
			System.out.print("Enter principal: ");
			double principal = s.nextDouble();
			System.out.print("Enter rate (%): ");
			double rate = s.nextDouble();
			System.out.print("Enter number of years: ");
			int time = s.nextInt();
			System.out.println("$" + principal + " at " + rate + "%" + " after " + time + " years = $"
					+ interest(principal, rate, time) + " interest");

		} catch (Exception e) {
			System.err.println("Invalid Input. Please enter numbers.");
		}

	}

}
