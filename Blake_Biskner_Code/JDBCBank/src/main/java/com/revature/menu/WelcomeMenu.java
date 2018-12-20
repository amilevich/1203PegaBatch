package com.revature.menu;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.revature.bean.Customer;
import com.revature.driver.Driver;
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
		int numEmp = numEmployees();
		int custNum = numCustomers();
		String message1 = "Proudly Serving ";
		String message2 = "Proudly Employing ";

		// Display Welcome Messages
		displayHeader();
		System.out.println(message1 + custNum + " Customers");
		System.out.println(message2 + numEmp + " Employees");
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

	/**
	 * Method to Get Number of Employees
	 * 
	 * @return number of employees
	 * 
	 */

	private int numEmployees() {
		return Driver.employees.size();
	}

	/**
	 * Method to Return Active Number of Customers
	 * 
	 * @return the number of approved customers
	 */

	private int numCustomers() {
		// Variable Declaration and Initialization
		int count = 0;
		int approved = 2;
		Customer customer;
		// Iterator
		Set<String> keys = Driver.customers.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			customer = Driver.customers.get(key);
			if ((customer.getAcctStatus()) == approved) {
				count++;
			}
		}
		return count;
	}
}
