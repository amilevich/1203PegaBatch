package com.homework.questions;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Scanner Interest Calculator
 * @author Blake Biskner
 * @version 1.17
 */

public class Question_17 {
	
	public static void main(String[] args) {
		// Declare and Initialize Variables
		// Use doubles as any number type value can have a variable of type double
		// Thus the user can enter an int, float, etc without error
		double interest;
		double princ=0;
		double rate=0;
		double time=0;
		// Initialize Scanner Object
		Scanner sc=new Scanner(System.in);
		System.out.println("App to Calculate Simple Interest");
		try { // Use try block to catch if the user enters invalid input
			System.out.print("Enter Principal= ");
			princ=sc.nextDouble();
			System.out.println("Enter Rate= ");
			rate=sc.nextDouble();
			System.out.println("Enter Time= ");
			time=sc.nextDouble();
		} catch(InputMismatchException e) {
			System.out.println("Program Terminated; Please Enter a Number"); //Print message if user enters invalid input
		} finally {
			sc.close();
		}
		// Echo User Input
		System.out.println("Your Information");
		System.out.println("Principal= $"+princ);
		System.out.println("Rate= "+rate+"%");
		System.out.println("Time= "+time+" years");
		System.out.print('\n');
		// Calculate Format and Display Interest
		interest=princ*rate*time;
		System.out.printf("Your Interest Is $%.2f", interest); // Format answer to display as $ amount (ie only 2 decimal places)
		System.out.print('\n');
	}
}

