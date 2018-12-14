package com.project.files.menus;

import java.util.Scanner;

import com.project.files.Admin;
import com.project.files.Customer;
import com.project.files.Driver;
import com.project.files.Employee;
import com.project.files.inputscreens.InputValidation;

public class AdminPersonalMenu extends PersonalMenu{
	public AdminPersonalMenu() {
		String name="Admin Dashboard";
		String message="Welcome Valued BlAnk Member";
		String options[]= {"View Customer Information", "View Account Information", "Review Applications", "Cancel Accounts","View Employees","Edit Accounts","Return to Homepage","Exit"};
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
		Admin admin=(Admin)employee; // Convert the employee to admin to use admin methods
		String custUsername="";
		switch(userInput) {
		case 1:
			admin.customerInfo(userIn);
			menuDriver(userIn,admin);
			break;
		case 2:
			admin.acctInfo(userIn);
			menuDriver(userIn,admin);
			break;
		case 3:
			admin.approveApp(userIn);
			menuDriver(userIn,admin);
			break;
		case 4:
			admin.cancel(userIn);
			menuDriver(userIn,admin);
			break;
		case 5:
			admin.employeeInfo(userIn);
			menuDriver(userIn,admin);
			break;
		case 6:
			custUsername=InputValidation.customerSelectValidate(userIn);
			if(InputValidation.existCustomerValidate(custUsername)==true) {
				Customer acctCust=Driver.customers.get(custUsername);
				AcctPersonalMenu acctMenu=new AcctPersonalMenu();
				acctMenu.menuDriver(userIn,acctCust,admin);
			} else {
				System.out.println("Username Not in Database");
			}
			menuDriver(userIn,admin);
		case 7:
			HomepageMenu homeMenu=new HomepageMenu();
			homeMenu.menuDriver(userIn);
			break;
		case 8:
			break;
		}
	}
	
	public void menuDriver(Scanner userIn, Employee admin) {
		int maxOp=menuDisplay();
		int userNum=InputValidation.optionValidate(userIn, maxOp);
		menuInput(userIn,userNum,admin);
	}
}

