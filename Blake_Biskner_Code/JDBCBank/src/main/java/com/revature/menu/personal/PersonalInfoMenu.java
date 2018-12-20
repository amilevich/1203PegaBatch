package com.revature.menu.personal;

import java.util.Scanner;

import com.revature.bean.Customer;
import com.revature.util.InputValidation;

/**
 * Method Which Allows User to View Personal Customer Information
 * 
 * @author Blake Biskner
 * @version 2.0
 */

public class PersonalInfoMenu extends PersonalMenu{
	public PersonalInfoMenu() {
		String name = "Personal Information";
		String message = "Account Holder Personal Information";
		String[] options = { "Return to Personal Dashboard" };

		setName(name);
		setMessage(message);
		setOptions(options);
	}

	public int displayMenu(Customer customer) {
		displayHeader();
		System.out.println("Name= " + customer.getFirstName() + " " + customer.getLastName());
		System.out.println("Age= " + customer.getAge());
		System.out.println("Username= " + customer.getUserName());
		System.out.println("Password= " + customer.getPassword());
		displayOptions();
		return getOptions().length;
	}

	public void menuInput(Scanner userIn, int userInput, Customer customer) {
		switch (userInput) {
		case 1:
			CustomerPersonalMenu menu = new CustomerPersonalMenu(customer);
			menu.menuDriver(userIn, customer);
			break;
		}
	}

	public void menuDriver(Scanner userIn, Customer customer) {
		int maxOp = this.displayMenu(customer);
		int userNum = InputValidation.optionValidate(userIn, maxOp);
		this.menuInput(userIn, userNum, customer);
	}
}
