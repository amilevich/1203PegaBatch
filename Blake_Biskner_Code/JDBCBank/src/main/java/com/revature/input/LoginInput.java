package com.revature.input;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.bean.Customer;
import com.revature.bean.Employee;
import com.revature.daoimpl.AdminDAOImpl;
import com.revature.driver.Driver;
import com.revature.menu.CustomerDashMenu;
import com.revature.menu.HomepageMenu;
import com.revature.menu.personal.AdminPersonalMenu;
import com.revature.menu.personal.CustomerPersonalMenu;
import com.revature.menu.personal.EmployeeMenu;
import com.revature.util.InputValidation;

/**
 * Login Screen Class
 * 
 * @author Blake Biskner
 * @version 2.0
 * 
 */

public class LoginInput extends ScreenInput {
	// Class Constants
	public static final int REVIEW = 1;
	public static final int APPROVED = 2;
	public static final int DENIED = 3;

	public static final int ADMIN = 1;

	// Constructor
	public LoginInput(String[] inputScreenInput) {
		String name = "Login Information Screen";
		setName(name);
		setScreenInput(inputScreenInput);
	}

	public void inputDisplay() {
		displayHeader();
	}

	public void getCustomerInput(Scanner userIn) {
		String username = "";
		String password = "";
		int status;
		// Display and get input for the username and password of the user
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
			if (customer.getPassword().equals(password)) { // You are a customer
				status = customer.getAcctStatus();
				switch (status) {
				case REVIEW:
					System.out.println("Application Under Review Please Check Back");
					CustomerDashMenu custDash = new CustomerDashMenu();
					custDash.menuDriver(userIn);
					break;
				case APPROVED:
					CustomerPersonalMenu menu = new CustomerPersonalMenu(customer);
					menu.menuDriver(userIn, customer);
					break;
				case DENIED:
					System.out.println("Your Account has Been Cancelled or Denied");
					// Once Customer has Viewed this Message Delete From Accounts
					AdminDAOImpl addi=new AdminDAOImpl();
					try {
						addi.deleteAccount(customer);
					} catch(SQLException e) {
						System.out.println("Error Deleting Account from Database");
						e.printStackTrace();
					}
					// Get Updated Versions of Maps without Deleted User
					Driver.pullCustomerMap();
					Driver.pullAccountMap();
					CustomerDashMenu custDashDeny = new CustomerDashMenu();
					custDashDeny.menuDriver(userIn);
					break;
				}
			} else { // Wrong password
				System.out.println("You Are a Customer but Your Password is Incorrect");
				CustomerDashMenu custDash = new CustomerDashMenu();
				custDash.menuDriver(userIn);
			}
		} else { // You are not a customer
			System.out.println("You Are Not a Customer");
			CustomerDashMenu custMen = new CustomerDashMenu();
			custMen.menuDriver(userIn);
		}

	}

	public void getEmployeeInput(Scanner userIn) {
		Integer id;
		id = InputValidation.existEmployeeIdValidate(userIn);
		System.out.println(id);
		if (Driver.employees.containsKey(id)) {
			Employee employee = Driver.employees.get(id);
			if (employee.getLevel() == ADMIN) {
				AdminPersonalMenu adMenu = new AdminPersonalMenu();
				adMenu.menuDriver(userIn, employee);
			} else { // Employee
				EmployeeMenu empMenu = new EmployeeMenu();
				empMenu.menuDriver(userIn, employee);
			}
		} else {
			System.out.println("Invalid Employee Number");
			HomepageMenu homepageMenu = new HomepageMenu();
			homepageMenu.menuDriver(userIn);
		}
	}
}
