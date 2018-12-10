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
		// Variable Initialization
		String exitMessage = "Application Ended";
		// Scanner Instantiation
		Scanner input = new Scanner(System.in);
		// Application Execution
		welcomeDriver(input);
		// Close Scanner object
		input.close();
		System.out.println(exitMessage);
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
	 * @param userIn
	 */

	public static void welcomeInput(Scanner userIn, int userInput) {
		switch (userInput) {
		case 1:
			homePageDriver(userIn);
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

	/**
	 * Runs all welcome screen functions
	 * 
	 * @param userIn
	 * @param userNum
	 * @param maxOp
	 */
	public static void welcomeDriver(Scanner userIn) {
		int maxOp = welcomeDisplay();
		int userNum = validateInput(userIn, maxOp);
		welcomeInput(userIn, userNum);
	}

	/**
	 * Validates input for option menu to ensure int and within value range
	 * 
	 * @param userNum
	 * @param maxOp
	 * @return returns the validated input
	 */

	public static int validateInput(Scanner userIn, int maxOp) {
		int userNum = 0;
		String errorMessage = "Please Enter Valid Input";
		int minOp = 1;
		do {
			while (!userIn.hasNextInt()) {
				userIn.next(); // Need this line to get next input or loops infinitely
				System.out.println(errorMessage);
			}
			userNum = userIn.nextInt();
			if ((userNum < minOp) || (userNum > maxOp)) {
				System.out.println(errorMessage);
			}
		} while ((userNum < minOp) || (userNum > maxOp));
		return userNum;
	}

	/**
	 * Displays homepage
	 * 
	 * @return number of options in select menu
	 */

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

	public static void homePageInput(Scanner userIn, int userInput) {
		switch (userInput) {
		case 1:
			System.out.println("Customer");
			break;
		case 2:
			System.out.println("Employee Under Construction");
			break;
		case 3:
			welcomeDriver(userIn);
			break;
		case 4:
			break;
		default:
			System.out.println("Please Select a Valid Input");
		}
	}

	public static void homePageDriver(Scanner userIn) {
		int maxOp = homePageDisplay();
		int userNum = validateInput(userIn, maxOp);
		homePageInput(userIn, userNum);
	}
}