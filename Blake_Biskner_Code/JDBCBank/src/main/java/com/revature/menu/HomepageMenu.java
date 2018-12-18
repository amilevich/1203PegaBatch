package com.revature.menu;

import java.util.Scanner;

import com.revature.util.InputValidation;


/**
 * Homepage Class
 * 
 * @author Blake Biskner
 * @version 2.0
 */

public class HomepageMenu extends Menu{

	// Constructor
	public HomepageMenu() {
		String name = "BlAnk HomePage";
		String message = "Welcome to the BlAnk Homepage";
		String options[] = { "Customer", "Employee", "Back to Welcome Page", "Exit Program" };
		setName(name);
		setMessage(message);
		setOptions(options);
	}

	/**
	 * Displays homepage
	 * 
	 * @return number of options in select menu
	 */
	
	@Override
	public int menuDisplay() {
		displayHeadPrompt();
		displayOptions();

		return getOptions().length;
	}

	/**
	 * Switch method for homepage
	 * 
	 * @param userIn
	 * @param userInput
	 */
	
	@Override
	public void menuInput(Scanner userIn, int userInput) {
		switch (userInput) {
		case 1:
//			CustomerDashMenu custMenu = new CustomerDashMenu();
//			custMenu.menuDriver(userIn);
			break;
		case 2:
//			String[] screenInput= {"Employee Id"};
//			LoginInput empMenu= new LoginInput(screenInput);
//			empMenu.displayHeader();
//			empMenu.getEmployeeInput(userIn);
			break;
		case 3:
			WelcomeMenu welcMenu = new WelcomeMenu();
			welcMenu.menuDriver(userIn);
			break;
		case 4:
			break;
		}
	}

	/**
	 * Runs all homepage methods
	 * 
	 * @param userIn
	 */
	
	@Override
	public void menuDriver(Scanner userIn) {
		int maxOp = menuDisplay();
		int userNum=InputValidation.optionValidate(userIn, maxOp);
		menuInput(userIn, userNum);
	}

}
