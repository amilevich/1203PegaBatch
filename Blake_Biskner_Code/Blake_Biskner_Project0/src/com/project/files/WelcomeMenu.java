package com.project.files;

import java.util.Scanner;

public class WelcomeMenu extends Menu {

	// Constructor
	public WelcomeMenu() {
		String name = "Welcome Menu";
		String message = "Welcome to BlAnk the Blake Bank";
		String options[] = { "Homepage", "Contact Us", "About" };
		setName(name);
		setMessage(message);
		setOptions(options);
	}

	/**
	 * Display Application Welcome Screen
	 * 
	 * @return length of options for input validation
	 */

	public int menuDisplay() {
		// Variable Intialization
		String message1 = "Proudly Serving ";
		String message2 = "Proudly Employing ";

		// Display Welcome Messages
		displayHeader();
		System.out.println(message1 + "n" + " Customers");
		System.out.println(message2 + "n" + " Employees");
		displayPrompt();
		displayOptions();

		return getOptions().length;
	}

	/**
	 * Select Menu Option Based Upon Scanner Input
	 * 
	 * @param userInput
	 * @param userIn
	 */

	public void menuInput(Scanner userIn, int userInput) {
		switch (userInput) {
		case 1:
			HomepageMenu menu = new HomepageMenu();
			menu.menuDriver(userIn);
			break;
		case 2:
			System.out.println("Contact");
			break;
		case 3:
			System.out.println("About");
			break;
		}
	}

	/**
	 * Runs all welcome screen functions
	 * 
	 * @param userIn
	 */
	public void menuDriver(Scanner userIn) {
		int maxOp = menuDisplay();
		int userNum = validateInput(userIn, maxOp);
		menuInput(userIn, userNum);
	}
}
