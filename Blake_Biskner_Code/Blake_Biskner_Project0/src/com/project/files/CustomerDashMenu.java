package com.project.files;

import java.util.Scanner;

public class CustomerDashMenu extends Menu {

	public CustomerDashMenu() {
		String name = "Customer Dashboard";
		String message = "Welcome to the Customer Dashboard";
		String options[] = { "Sign In", "Apply", "Check Status", "Back to Homepage", "Exit Program" };
		setName(name);
		setMessage(message);
		setOptions(options);
	}

	/**
	 * Displays Customer Dashboard
	 * 
	 * @return returns maximum option value
	 */

	public int menuDisplay() {
		// Menu Display
		displayHeadPrompt();
		displayOptions();

		return getOptions().length;
	}

	/**
	 * Switch method for customer dashboard
	 * 
	 * @param userIn
	 * @param userInput
	 */

	public void menuInput(Scanner userIn, int userInput) {
		switch (userInput) {
		case 1:
			System.out.println("Sign In");
			break;
		case 2:
			System.out.println("Apply");
			break;
		case 3:
			System.out.println("Status");
			break;
		case 4:
			HomepageMenu menu = new HomepageMenu();
			menu.menuDriver(userIn);
			break;
		case 5:
			break;
		}
	}

	/**
	 * Runs all customer dashboard methods
	 * 
	 * @param userIn
	 */

	public void menuDriver(Scanner userIn) {
		int maxOp = menuDisplay();
		int userNum = validateInput(userIn, maxOp);
		menuInput(userIn, userNum);
	}
}
