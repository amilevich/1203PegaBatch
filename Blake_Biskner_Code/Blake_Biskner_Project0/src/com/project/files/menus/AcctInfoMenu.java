package com.project.files.menus;

import java.util.Scanner;

import com.project.files.Customer;
import com.project.files.inputscreens.InputValidation;

public class AcctInfoMenu extends PersonalMenu {
	public AcctInfoMenu(Customer customer) {
		String name = "Account Information";
		String message = "Personal Account Information";
		String[] options = { "Return to Personal Dashboard" };

		setName(name);
		setMessage(message);
		setOptions(options);
	}

	public int displayMenu(Customer customer) {
		this.displayHeader();
		String type = "";
		if (customer.getAcctType() == 'P') {
			type = "Personal";
		} else {
			type = "Joint";
		}
		System.out.println("Account Number " + customer.getAcctNum());
		System.out.println(type + " Account");
		System.out.printf("Account Balance $%.2f", customer.getBalance());
		System.out.print('\n');
		this.displayOptions();
		return getOptions().length;
	}

	public void menuInput(Scanner userIn, int userInput, Customer customer) {
		switch (userInput) {
		case 1:
			CustomerPersonalMenu menu = new CustomerPersonalMenu(customer);
			menu.menuDriver(userIn,customer);
			break;
		}
	}

	public void menuDriver(Scanner userIn, Customer customer) {
		int maxOp = this.displayMenu(customer);
		int userNum = InputValidation.optionValidate(userIn, maxOp);
		this.menuInput(userIn, userNum, customer);
	}
}
