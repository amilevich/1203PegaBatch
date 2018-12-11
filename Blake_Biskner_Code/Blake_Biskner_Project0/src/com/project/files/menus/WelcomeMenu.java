package com.project.files.menus;

import java.util.Scanner;

import com.project.files.inputscreens.InputValidation;

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
			HomepageMenu homeMenu = new HomepageMenu();
			homeMenu.menuDriver(userIn);
			break;
		case 2:
			ContactMenu contMenu=new ContactMenu();
			contMenu.menuDriver(userIn);
			break;
		case 3:
			AboutMenu aboutMenu=new AboutMenu();
			aboutMenu.menuDriver(userIn);
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
		int userNum=InputValidation.optionValidate(userIn, maxOp);
		menuInput(userIn, userNum);
	}
}
