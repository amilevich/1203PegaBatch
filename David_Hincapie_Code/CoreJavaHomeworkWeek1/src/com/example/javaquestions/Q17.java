package com.example.javaquestions;

import java.util.Scanner;

public class Q17 {
	// Q17. Write a program that calculates the simple interest on the principal,
	// rate of interest and number of years provided by the user. Enter principal,
	// rate and time through the console using the Scanner class.
	// Interest = Principal* Rate* Time
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double principal = 0;
		double rate = 0;
		double time = 0;
		double simpleInterest = 0;

		System.out.print("Enter the principal: ");
		principal = scanner.nextDouble();
		System.out.print("\nEnter the rate: ");
		rate = scanner.nextDouble();
		System.out.print("\nEnter time: ");
		time = scanner.nextDouble();
		simpleInterest = principal * rate * time;
		System.out.println("The simple interest is: " + simpleInterest);
		
		scanner.close();
	}

}
