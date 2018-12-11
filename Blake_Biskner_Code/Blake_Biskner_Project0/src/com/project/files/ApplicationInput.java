package com.project.files;

import java.util.Scanner;

public class ApplicationInput extends ScreenInput {
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

	public void getInput(Scanner userIn) {
		String name[];
		int userAge=0;
		String userSocSec;
		String username;
		String password;
		for (int i = 0; i < getScreenInput().length; i++) { // Iterates through each field in inputScreenInputArray
			switch (i) { // Provides distinct functionality (ie input validation) for each type of input
			case 0: // Name
				name = InputValidation.nameValidate(userIn);
				// Will Set to Customer Object Field
				break;
			case 1: // Year of birth
				userAge = InputValidation.ageValidate(userIn);
				System.out.println(userAge);
				break;
			case 2: // Social Security
				userSocSec=InputValidation.socSecValidate(userIn);
				System.out.println(userSocSec);
				break;
			case 3: // Username
				username=InputValidation.usernameValidate(userIn);
				System.out.println(username);
				break;
			case 4:
				password=InputValidation.passwordValidate(userIn);
				System.out.println(password);
				break;
			}
		}
	}
}
