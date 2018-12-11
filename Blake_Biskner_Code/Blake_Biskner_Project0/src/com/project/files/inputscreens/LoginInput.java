package com.project.files.inputscreens;

import java.util.Scanner;

import com.project.files.Customer;
import com.project.files.Driver;

public class LoginInput extends ScreenInput {

	// Constructor
	public LoginInput() {
		String name = "Login Information Screen";
		String screenInput[] = { "Username", "Password" };

		setName(name);
		setScreenInput(screenInput);
	}

	public void inputDisplay() {
		displayHeader();
	}

	public void getInput(Scanner userIn) {
		String username = "";
		String password = "";
		for (int i = 0; i < getScreenInput().length; i++) {
			switch (i) {
			case 0:
				username = InputValidation.existUserNameValidate(userIn);
				break;
			case 1:
				password = InputValidation.existPasswordValidate(userIn);
				break;
			}
		}
		if (Driver.customers.containsKey(username)) {
			Customer customer = Driver.customers.get(username);
			if (customer.getPassword().equals(password)) {
				System.out.println("Customer");
			} else {
				System.out.println("You Are a Customer but Your Password is Incorrect");
			}
		} else {
			System.out.println("You Are Not a Customer");
		}
	}
}
