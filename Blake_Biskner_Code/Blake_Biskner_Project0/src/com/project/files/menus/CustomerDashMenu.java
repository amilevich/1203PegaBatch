package com.project.files.menus;

import java.util.Scanner;

import com.project.files.inputscreens.*;
import com.project.files.*;

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
			LoginInput logInput = new LoginInput();
			logInput.inputDisplay();
			break;
		case 2:
			ApplicationInput appInput = new ApplicationInput();
			appInput.inputDisplay();
			// Instantiate Customer
			Customer customer=new Customer();
			// Initialize Customer State
			appInput.getInput(userIn,customer);
			// Initialize Customer Account Type
			char acctType = InputValidation.acctTypeValidate(userIn);
			customer.setAcctType(acctType);
			// Account Type Flow
			if(customer.getAcctType()=='J') {
				System.out.println("Enter Information for Joint Holder");
				Customer jointCust=new Customer(customer.getAcctType()); // Instantiate with joint account
				appInput.getInput(userIn, jointCust);
				FileWrite.writeToPending(customer);
				FileWrite.writeToPending(jointCust);
			}else {
				System.out.println("Application Awaiting Approval");
				FileWrite.writeToPending(customer);
			}
			menuDriver(userIn);
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
		int userNum = InputValidation.optionValidate(userIn, maxOp);
		menuInput(userIn, userNum);
	}
}
