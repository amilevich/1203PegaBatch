package com.revature.menu;

import java.util.Scanner;

import com.revature.input.ApplicationInput;
import com.revature.util.InputValidation;

/**
 * Customer Dashboard Class
 *
 * @author Blake Biskner
 * @version 2.0
 */

public class CustomerDashMenu extends Menu {

	public CustomerDashMenu() {
		String name = "Customer Dashboard";
		String message = "Welcome to the Customer Dashboard";
		String options[] = { "Sign In", "Apply", "Back to Homepage", "Exit Program" };
		setName(name);
		setMessage(message);
		setOptions(options);
	}

	/**
	 * Displays Customer Dashboard
	 * 
	 * @return returns maximum option value
	 */

	@Override
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

	@Override
	public void menuInput(Scanner userIn, int userInput) {
		switch (userInput) {
		case 1:
			String[] screenInput = { "Username", "Password" };
			// LoginInput logInput = new LoginInput(screenInput);
			// logInput.inputDisplay();
			// logInput.getCustomerInput(userIn);
			break;
		case 2: // Sign Up
			 ApplicationInput appInput = new ApplicationInput();
			 appInput.inputDisplay();
			// Instantiate Customer
			// Customer customer=new Customer();
			// Initialize Customer State
			// appInput.getInput(userIn,customer);
			// Initialize Customer Account Type
			// char acctType = InputValidation.acctTypeValidate(userIn);
			// customer.setAcctType(acctType);
			// Account Type Flow
			// if(customer.getAcctType()=='J') {
			// FileWrite.writeToCustomerDataBase(customer);
			// Driver.pullCustomerMap();
			// Must make new copy of map with first joint holder
			// Else both holders may have same username
			// System.out.println("Enter Information for Joint Holder");
			// Customer jointCust=new Customer(customer.getAcctNum()); // Instantiate with
			// joint account
			// appInput.getInput(userIn, jointCust);
			// FileWrite.writeToCustomerDataBase(jointCust);
			// }else {
			// System.out.println("Application Awaiting Approval");
			// FileWrite.writeToCustomerDataBase(customer);
			// }
			menuDriver(userIn);
			break;
		case 3:
			HomepageMenu menu = new HomepageMenu();
			menu.menuDriver(userIn);
			break;
		case 4:
			break;
		}
	}

	/**
	 * Runs all customer dashboard methods
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