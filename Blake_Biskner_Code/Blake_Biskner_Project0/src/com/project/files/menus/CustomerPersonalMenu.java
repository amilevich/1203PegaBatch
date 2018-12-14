package com.project.files.menus;

import java.util.Scanner;

import com.project.files.Customer;
import com.project.files.inputscreens.InputValidation;

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
			AcctPersonalMenu acctMenu=new AcctPersonalMenu();
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
			System.out.println("hi");
			break;
		}
	}

	public void menuDriver(Scanner userIn, Customer customer) {
		int maxOp = this.displayMenu();
		int userNum = InputValidation.optionValidate(userIn, maxOp);
		this.menuInput(userIn, userNum, customer);
	}
}
