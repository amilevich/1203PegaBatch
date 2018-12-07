package com.test.asmt01;

import java.util.Scanner;

public class Q17 {

	public static void main(String[] args) {
		
		double principal;
		double rate;
		int time;
		double interest;
		
		Scanner scanner = new Scanner(System.in);
		
		// Prompt user for entering Principal.
		// Upon <enter>, save the Principal.
		System.out.printf("Please enter the Principal:");
		principal = scanner.nextDouble();
		
		// Prompt user for entering Rate.
		// Upon <enter>, save the Rate.
		System.out.printf("Please enter the Rate:");
		rate = scanner.nextDouble();

		// Prompt user for entering Number Of Years.
		// Upon <enter>, save the Number Of Years.
		System.out.printf("Please enter the Number of Years");
		time = scanner.nextInt();
		
		// Calculate and save the Interest.
		interest = (double) principal * rate * time;
		
		// Display the Interest to the console.
		System.out.printf("Your calculated Interest is %,.2f", interest);

	}

}
