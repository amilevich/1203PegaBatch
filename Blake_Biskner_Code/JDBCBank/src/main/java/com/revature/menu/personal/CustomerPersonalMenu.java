package com.revature.menu.personal;

import java.util.Scanner;

import com.revature.bean.Customer;
import com.revature.menu.HomepageMenu;
import com.revature.util.InputValidation;

/**
 * Class for Object Specific Customer Menu
 *
 * @author Blake Biskner
 * @version 2.0
 * 
 */

public class CustomerPersonalMenu extends PersonalMenu {
	public CustomerPersonalMenu(Customer customer) {
		String name = "Dashboard";
		String message = "Welcome to Your Personal DashBoard";
		String options[] = { "Account Actions", "Account Information", "Personal Information", "Return to Homepage",
				"Exit" };

		setName(customer.getFirstName() + " " + customer.getLastName() + " " + name);
		setMessage(message);
		setOptions(options);
	}

	public int displayMenu() {
		this.displayHeader();
		this.displayOptions();
		return this.getOptions().length;
	}

	public void menuInput(Scanner userIn, int userInput, Customer customer) {
		switch (userInput) {
		case 1:
			AcctPersonalMenu acctMenu = new AcctPersonalMenu();
			acctMenu.menuDriver(userIn, customer);
			break;
		case 2:
			AcctInfoMenu infoMenu = new AcctInfoMenu(customer);
			infoMenu.menuDriver(userIn, customer);
			break;
		case 3:
			PersonalInfoMenu persMenu = new PersonalInfoMenu();
			persMenu.menuDriver(userIn, customer);
			break;
		case 4:
			HomepageMenu menu = new HomepageMenu();
			menu.menuDriver(userIn);
			break;
		case 5:
			break;
		}
	}

	public void menuDriver(Scanner userIn, Customer customer) {
		int maxOp = this.displayMenu();
		int userNum = InputValidation.optionValidate(userIn, maxOp);
		this.menuInput(userIn, userNum, customer);
	}
}
