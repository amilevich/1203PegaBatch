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
		// rate is entered as a percentage so must be converted to a decimal before interest is calculated
		rate = rate/100;
		return principal * rate * time;
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Simple Interest Calculator!");
		System.out.print("Enter principal: ");
		double principal = s.nextDouble();
		System.out.print("Enter rate (%): ");
		double rate = s.nextDouble();
		System.out.print("Enter time: ");
		int time = s.nextInt();
		
		System.out.println("$"+ principal + " at " + rate + "%" + " after " + time + " increments = $" + interest(principal, rate, time) + " interest");

	}

}
