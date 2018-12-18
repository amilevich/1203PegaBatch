package com.revature.input;

import java.util.Scanner;

import com.revature.bean.Customer;
import com.revature.util.InputValidation;



/**
 * Customer Application Class
 *
 * @author Blake Biskner
 * @version 2.0
 */

public class ApplicationInput extends ScreenInput{
	// Constructor
	public ApplicationInput() {
		String name = "Application Information";
		String screenInput[] = { "Name", "Year of Birth", "Social Security Number","UserName","Password"};

		setName(name);
		setScreenInput(screenInput);
	}

	public void inputDisplay() {
		displayHeader();
	}

	public void getInput(Scanner userIn, Customer newCust) {
		String[] name;
		int userAge=0;
		String userSocSec;
		String username;
		String password;
		for (int i = 0; i < getScreenInput().length; i++) { // Iterates through each field in inputScreenInputArray
			switch (i) { // Provides distinct functionality (ie input validation) for each type of input
			case 0: // Name
				name = InputValidation.nameValidate(userIn);
				newCust.setFirstName(name[0]);
				newCust.setLastName(name[1]);
				break;
			case 1: // Year of birth
				userAge = InputValidation.ageValidate(userIn);
				newCust.setAge(userAge);
				break;
			case 2: // Social Security
				userSocSec=InputValidation.socSecValidate(userIn);
				newCust.setSocialSecurity(userSocSec); // Maybe change to be more secure
				break;
			case 3: // Username
				username=InputValidation.usernameValidate(userIn);
				newCust.setUsername(username);
				break;
			case 4: // Password
				password=InputValidation.passwordValidate(userIn);
				newCust.setPassword(password);
				break;
			}
		}
	}	
}