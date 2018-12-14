package com.project.files.inputscreens;

import java.util.Scanner;

import com.project.files.Customer;
import com.project.files.Driver;
import com.project.files.Employee;
import com.project.files.menus.AdminPersonalMenu;
import com.project.files.menus.CustomerDashMenu;
import com.project.files.menus.CustomerPersonalMenu;
import com.project.files.menus.EmployeeMenu;
import com.project.files.menus.HomepageMenu;

public class LoginInput extends ScreenInput {

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
		char status;
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
				status=customer.getAcctStatus();
				switch(status) {
				case 'R':
					System.out.println("Application Under Review Please Check Back");
					break;
				case 'D': // Deined or cancelled
					System.out.println("Your Account Has Been Cancelled or Denied");
					break;
				case 'A':
					CustomerPersonalMenu menu=new CustomerPersonalMenu(customer);
					menu.menuDriver(userIn,customer);
					break;
				}
			} else { // Wrong password
				System.out.println("You Are a Customer but Your Password is Incorrect");
				CustomerDashMenu custDash=new CustomerDashMenu();
				custDash.menuDriver(userIn);
			}
		} else { // You are not a customer
			System.out.println("You Are Not a Customer");
			CustomerDashMenu custMen=new CustomerDashMenu();
			custMen.menuDriver(userIn);
		}

	}
	
	public void getEmployeeInput(Scanner userIn) {
		Integer id;
		id = InputValidation.existEmployeeIdValidate(userIn);
		System.out.println(id);
		if(Driver.employees.containsKey(id)) {
			Employee employee=Driver.employees.get(id);
			if(employee.getLevel().equals("admin")) { // Admin
				AdminPersonalMenu adMenu=new AdminPersonalMenu();
				adMenu.menuDriver(userIn, employee);
			} else { // Employee
				EmployeeMenu empMenu=new EmployeeMenu();
				empMenu.menuDriver(userIn, employee);
			}
		} else {
			System.out.println("Invalid Employee Number");
			HomepageMenu homepageMenu=new HomepageMenu();
			homepageMenu.menuDriver(userIn);
		}
	}
}
