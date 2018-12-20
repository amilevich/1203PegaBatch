package com.revature.menu.personal;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.bean.Account;
import com.revature.bean.Customer;
import com.revature.bean.Employee;
import com.revature.daoimpl.AccountDAOImpl;
import com.revature.util.InputValidation;

/**
 * Class That Allows User to Interact with Account
 * 
 * @author Blake Biskner
 * @version 2.0
 * 
 */

public class AcctPersonalMenu extends PersonalMenu {
	public AcctPersonalMenu() {
		String name = "Account Dashboard";
		String message = "Welcome to your Account Dashboard";
		String[] options = { "Deposit", "Withdraw", "Transfer", "Return to Personal Menu", "Exit" };
		setName(name);
		setMessage(message);
		setOptions(options);
	}

	public int menuDisplay() {
		displayHeader();
		displayOptions();
		return getOptions().length;
	}

	public void menuInput(Scanner userIn, int userInput, Customer customer) {
		AccountDAOImpl adi = new AccountDAOImpl();
		Double money;
		Account userAcct;
		switch (userInput) {
		case 1: // Deposit
			money = InputValidation.amountValidate(userIn);
			userAcct = customer.getAcct();
			userAcct.deposit(money);
			// Put here so transfer does not update 3 times
			try {
				adi.updateAccount(userAcct);
			} catch (SQLException e) {
				System.out.println("Exception Depositing");
				e.printStackTrace();
			}
			menuDriver(userIn, customer);
			break;
		case 2: // Withdraw
			money = InputValidation.enoughMoneyValidate(userIn, customer);
			userAcct = customer.getAcct();
			userAcct.withdraw(money);
			// Put here so transfer does not update 3 times
			try {
				adi.updateAccount(userAcct);
			} catch (SQLException e) {
				System.out.println("Exception Withdrawing");
				e.printStackTrace();
			}
			menuDriver(userIn, customer);
			break;
		case 3: // Transfer
			money = InputValidation.enoughMoneyValidate(userIn, customer);
			userAcct = customer.getAcct();
			userAcct.transfer(money, userAcct, userIn);
			menuDriver(userIn, customer);
			break;
		case 4:
			CustomerPersonalMenu custMenu = new CustomerPersonalMenu(customer);
			custMenu.menuDriver(userIn, customer);
			break;
		case 5:
			break;
		}
	}

	// Overridden function for admin
	public void menuInput(Scanner userIn, int userInput, Customer customer, Employee admin) {
		AccountDAOImpl adi = new AccountDAOImpl();
		Double money;
		Account userAcct;
		switch (userInput) {
		case 1:
			money = InputValidation.amountValidate(userIn);
			userAcct = customer.getAcct();
			userAcct.deposit(money);
			try {
				adi.updateAccount(userAcct);
			} catch (SQLException e) {
				System.out.println("Exception Depsoiting");
				e.printStackTrace();
			}
			menuDriver(userIn, customer, admin);
			break;
		case 2:
			money = InputValidation.enoughMoneyValidate(userIn, customer);
			userAcct = customer.getAcct();
			userAcct.withdraw(money);
			menuDriver(userIn, customer, admin);
			try {
				adi.updateAccount(userAcct);
			} catch (SQLException e) {
				System.out.println("Exception Withdrawing");
				e.printStackTrace();
			}
			break;
		case 3:
			money = InputValidation.enoughMoneyValidate(userIn, customer);
			userAcct = customer.getAcct();
			userAcct.transfer(money, userAcct, userIn);
			menuDriver(userIn, customer, admin);
			break;
		case 4:
			// Only difference in code used to put admin back on their menu
			AdminPersonalMenu adminMenu = new AdminPersonalMenu();
			adminMenu.menuDriver(userIn, admin);
			break;
		case 5:
			break;
		}
	}

	public void menuDriver(Scanner userIn, Customer customer) {
		int maxOp = menuDisplay();
		int userNum = InputValidation.optionValidate(userIn, maxOp);
		menuInput(userIn, userNum, customer);
	}

	// Overridden to support admin menuInput
	public void menuDriver(Scanner userIn, Customer customer, Employee admin) {
		int maxOp = menuDisplay();
		int userNum = InputValidation.optionValidate(userIn, maxOp);
		menuInput(userIn, userNum, customer, admin);
	}
}
