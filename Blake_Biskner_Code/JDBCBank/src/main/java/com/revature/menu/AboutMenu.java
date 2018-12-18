package com.revature.menu;

import java.util.Scanner;

import com.revature.util.InputValidation;

/**
 * About Menu Class
 * 
 * @author Blake Biskner
 * @version 2.0
 */

public class AboutMenu extends Menu {
	// Constructor
	public AboutMenu() {
		String name = "About BlAnk";
		String message = "BlAnk was Founded in 2018 to Satisfy Project Requirements";
		String options[] = { "Back to Welcome Page" };
		setName(name);
		setMessage(message);
		setOptions(options);
	}

	/**
	 * Display About Menu
	 * 
	 * @return length of options for input validation
	 */

	@Override
	public int menuDisplay() {
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

	@Override
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

	@Override
	public void menuDriver(Scanner userIn) {
		int maxOp = menuDisplay();
		int userNum = InputValidation.optionValidate(userIn, maxOp); // Call static validation method
		menuInput(userIn, userNum);
	}
}
