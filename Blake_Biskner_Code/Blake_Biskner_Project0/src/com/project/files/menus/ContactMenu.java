package com.project.files.menus;

import java.util.Scanner;

import com.project.files.InputValidation;

public class ContactMenu extends Menu {

	// Constructor
	public ContactMenu() {
		String name = "Contact Us";
		String message = "To Contact a Customer Representative Please Email Blake Biskner";
		String options[] = { "Back to Welcome Page" };
		setName(name);
		setMessage(message);
		setOptions(options);
	}

	/**
	 * Display Contact Menu
	 * 
	 * @return length of options for input validation
	 */

	public int menuDisplay() {
		// Menu Display
		displayHeadPrompt();
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
			WelcomeMenu welcMenu = new WelcomeMenu();
			welcMenu.menuDriver(userIn);
			break;
		}
	}

	/**
	 * Runs all contact functions
	 * 
	 * @param userIn
	 */
	public void menuDriver(Scanner userIn) {
		int maxOp = menuDisplay();
		int userNum = InputValidation.optionValidate(userIn, maxOp);
		menuInput(userIn, userNum);
	}
}
