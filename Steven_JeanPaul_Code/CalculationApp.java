package com.questions.interestrate;
import java.util.Scanner;
//Author: Steven Jean-Paul
//Q17 - Simple Interest on Principal
public class CalculationApp {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		double principal = 0; //Set variables
		double interestRate = 0.0d;
		double amtOfTime = 0;
		double interest = 0;
		
		System.out.println("Hello!");
		
		System.out.print("Please enter principal balance: ");
		principal = in.nextDouble(); //Take in value from user for principal
		
		System.out.print("Next enter quartley interest rate: ");
		interestRate = in.nextDouble(); //Take in value from user for interest rate
		
		System.out.print("Lastly, enter the amount of time in years to pay: ");
		amtOfTime = in.nextDouble(); //Take in value from user for amount of time to pay the loan.
		
		interest = (principal * interestRate) / amtOfTime; //Calculate the value of the loan with interest included.
		
		System.out.println(interest);
		
		in.close(); //Close scanner.
		
	}

}
