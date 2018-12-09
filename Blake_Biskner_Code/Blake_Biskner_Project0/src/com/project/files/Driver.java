package com.project.files;

import java.util.Scanner;

/**
 * Driver Class for Bank Account Application
 * 
 * @author Blake Biskner
 * @version 1.0
 */

public class Driver {

	public static void main(String[] args) {
		// Variable Declaration and Initialization
		int userIn, maxOption;
		int minOption = 1; // Minimum select value will always be 1
		// Scanner Instantiation
		Scanner input = new Scanner(System.in);
		// Application Execution
		maxOption = welcomeDisplay();
		// Ensure scanner input of correct type and in range
		do {
			while (!input.hasNextInt()) {
				input.next(); // Need this line or else loops infinitely
				System.out.println("Please Enter a Number");
			}
			userIn = input.nextInt();
			welcomeInput(userIn);
		} while ((userIn < minOption) || (userIn > maxOption));
		// Close Scanner object
		input.close();
		System.out.println("End");
	}

	/**
	 * Display Application Welcome Screen
	 * 
	 * @return length of options for input validation
	 */

	public static int welcomeDisplay() {
		// Variable Intialization
		String name = "Welcome Menu";
		String message1 = "Welcome to BlAnk the Blake Bank";
		String message2 = "Proudly Serving ";
		String message3 = "Proudly Employing ";

		// Option Array Initialization
		String options[] = { "Home", "Contact Us", "About" };

		// Instantiate Menu Object
		Menu welcomeMenu = new Menu(name, message1, options);

		// Display Welcome Messages
		welcomeMenu.displayHeader();
		System.out.println(message2 + "n" + " Customers");
		System.out.println(message3 + "n" + " Employees");
		welcomeMenu.displayPrompt();
		welcomeMenu.displayOptions();

		return welcomeMenu.getOptions().length;
	}

	/**
	 * Select Menu Option Based Upon Scanner Input
	 * 
	 * @param userInput
	 */

	public static void welcomeInput(int userInput) {
		switch (userInput) {
		case 1:
			homePageDisplay();
			break;
		case 2:
			System.out.println("Contact");
			break;
		case 3:
			System.out.println("About");
			break;
		default:
			System.out.println("Please Select A Valid Input");
		}
	}
	
	public static int homePageDisplay() {
		// Varaiable Initialization
		String name = "BlAnk HomePage";
		String message = "Welcome to the BlAnk Homepage";

		// Option instatntiation
		String options[] = { "Customer", "Employee", "Back to Welcome Page", "Exit Program" };

		// Menu instantiation
		Menu homepageMenu = new Menu(name, message, options);

		// Menu Display
		homepageMenu.displayHeadPrompt();
		homepageMenu.displayOptions();

		return homepageMenu.getOptions().length;
	}
}