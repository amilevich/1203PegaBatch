package com.assignment.weekone;

import java.util.Scanner;

public class Q17 {

	public static void main(String[] args) {
		Scanner scanner =  new Scanner (System.in);//allows inputs from user
		double principal = 0;//variables to be used!
		double interestRate = 0;
		double years = 0;
		double interestAmount = 0;
		
		System.out.print("Please enter your Principal amount: ");//prompt
		principal = scanner.nextDouble();//takes in principal
		System.out.print("Please enter your yearly interest rate percent amount: ");
		interestRate =  scanner.nextDouble();//takes in interest rate (percent number)
		System.out.print("Please enter how many years will pass: "); 
		years = scanner.nextDouble();//years
		interestAmount = principal*years*(interestRate*0.01); //I'm taking interest rate at percent whole number value and then converting it to decimal
		System.out.print("Your interest gained in " + years +" years from a Principal of $" +principal + " at a yearly rate of " + interestRate + "% is: $"+ interestAmount);
		scanner.close();//close out scanner

	}

}
