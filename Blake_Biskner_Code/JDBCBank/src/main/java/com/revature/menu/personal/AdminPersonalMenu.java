package com.revature.menu.personal;

import java.util.Scanner;

import com.revature.bean.Admin;
import com.revature.bean.Customer;
import com.revature.bean.Employee;
import com.revature.driver.Driver;
import com.revature.menu.HomepageMenu;
import com.revature.util.InputValidation;

/**
 * Personal Menu Class for Admin
 * 
 * @author Blake Biskner
 * @version 2.0
 * 
 */

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
		Admin admin=new Admin(employee.getId(),employee.getLevel()); // Create Admin using passed in information
		String custUsername="";
		switch(userInput) {
		case 1: // Customer Info
			admin.customerInfo(userIn);
			menuDriver(userIn,admin);
			break;
		case 2: // Account Info
			admin.acctInfo(userIn);
			menuDriver(userIn,admin);
			break;
		case 3: // Approve Applications
			admin.approveApp(userIn);
			menuDriver(userIn,admin);
			break;
		case 4: // Cancel Applications
			admin.cancel(userIn);
			menuDriver(userIn,admin);
			break;
		case 5: // Employee Info
			admin.employeeInfo(userIn);
			menuDriver(userIn,admin);
			break;
		case 6: // Edit Accounts
			custUsername=InputValidation.customerSelectValidate(userIn);
			if(InputValidation.existCustomerValidate(custUsername)==true) {
				Customer acctCust=Driver.customers.get(custUsername);
				AcctPersonalMenu acctMenu=new AcctPersonalMenu();
				acctMenu.menuDriver(userIn,acctCust,admin);
			} else {
				System.out.println("Username Not in Database");
			}
			menuDriver(userIn,admin);
			break;
		case 7: // Homepage
			HomepageMenu homeMenu=new HomepageMenu();
			homeMenu.menuDriver(userIn);
			break;
		case 8: // Exit
			break;
		}
	}
	
	public void menuDriver(Scanner userIn, Employee admin) {
		int maxOp=menuDisplay();
		int userNum=InputValidation.optionValidate(userIn, maxOp);
		menuInput(userIn,userNum,admin);
	}	
}
