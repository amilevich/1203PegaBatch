package com.revature.menu;

import java.util.Scanner;

import com.revature.util.InputValidation;

/**
 * Welcome Menu Class
 * 
 * @author Blake Biskner
 * @version 2.0
 */

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

	@Override
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

	@Override
	public void menuInput(Scanner userIn, int userInput) {
		switch (userInput) {
		case 1: // Homepage
			HomepageMenu homeMenu = new HomepageMenu();
			homeMenu.menuDriver(userIn);
			break;
		case 2: // Contact Us
			ContactMenu contMenu = new ContactMenu();
			contMenu.menuDriver(userIn);
			break;
		case 3: // About
			AboutMenu aboutMenu = new AboutMenu();
			aboutMenu.menuDriver(userIn);
			break;
		}
	}

	/**
	 * Runs all welcome screen functions
	 * 
	 * @param userIn
	 */
	@Override
	public void menuDriver(Scanner userIn) {
		int maxOp = menuDisplay();
		int userNum = InputValidation.optionValidate(userIn, maxOp);
		menuInput(userIn, userNum);
	}
}
