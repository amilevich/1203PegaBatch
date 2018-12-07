//Write a program that calculates the simple interest on the principal, 
//rate of interest and number of years provided by the user. Enter principal, 
//rate and time through the console using the Scanner class.
//Interest = Principal* Rate* Time

package com.example.simpleinetrest;

import java.util.Scanner;

public class SimpleIntereset {
	public static void main(String[] args) {
		// Getting input from user
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the Principal Amount: ");
		double principal = scanner.nextDouble();
		// Getting input from user
		System.out.print("Enter the Rate of Interest: ");
		double rate = scanner.nextDouble();
		// Getting input from User
		System.out.print("Enter Number of Years: ");
		double time = scanner.nextDouble();

		scanner.close();
		//Calculating interest and printing it out
		double simpleInterest = principal * rate * time;
		System.out.println("The Simple Interest is: " + simpleInterest);
	}
}
