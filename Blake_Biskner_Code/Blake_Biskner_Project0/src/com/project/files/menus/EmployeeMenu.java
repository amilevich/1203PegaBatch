package com.project.files.menus;

import java.util.Scanner;

import com.project.files.Employee;
import com.project.files.inputscreens.InputValidation;

public class EmployeeMenu extends PersonalMenu {
	public EmployeeMenu() {
		String name="Employee Dashboard";
		String message="Welcome Valued BlAnk Member";
		String[] options= {"View Customer Information", "View Account Information", "Review Applications", "Return to Homepage","Exit"};
		setName(name);
		setMessage(message);
		setOptions(options);
		}

	public int menuDisplay() {
		displayHeader();
		displayOptions();
		return getOptions().length;
	}

	public void menuInput(Scanner userIn, int userInput, Employee employee) {
		switch (userInput) {
		case 1:
			employee.customerInfo(userIn);
			menuDriver(userIn,employee);
			break;
		case 2:
			employee.acctInfo(userIn);
			menuDriver(userIn,employee);
			break;
		case 3:
			employee.approveApp(userIn);
			menuDriver(userIn, employee);
			break;
		case 4:
			HomepageMenu homeMenu = new HomepageMenu();
			homeMenu.menuDriver(userIn);
			break;
		case 5:
			break;
		}
	}

	public void menuDriver(Scanner userIn, Employee employee) {
		int maxOp = menuDisplay();
		int userNum = InputValidation.optionValidate(userIn, maxOp);
		menuInput(userIn, userNum, employee);
	}
}