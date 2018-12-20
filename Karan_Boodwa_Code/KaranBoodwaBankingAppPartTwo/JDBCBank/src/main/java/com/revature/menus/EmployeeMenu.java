package com.revature.menus;

import java.text.DecimalFormat;
import java.util.List;

import com.revature.input.Input;
import com.revature.jdbcbank.TransactionHandler;
import com.revature.jdbcbank.UserAuthorizer;
import com.revature.pojos.Account;
import com.revature.pojos.Application;
import com.revature.pojos.User;

public class EmployeeMenu {
	// UserAuthorizer instance:
	private UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();

	// reference to the singleton instance
	protected TransactionHandler tHandler = TransactionHandler.getTHandler();

	protected User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EmployeeMenu(User user) {
		super();
		this.user = user;
	}

	public EmployeeMenu() {
		super();
	}

	public void menu() {
		// Input singleton used to get user input
		Input in = Input.getInputSingleton();

		boolean returnToMenu = true;

		while (returnToMenu) {

			System.out.println("Welcome back to The First Bank of Karan, valued employee!");
			System.out.println("1. Customers");
			System.out.println("2. Bank Accounts");
			System.out.println("3. Approve/Deny Pending Accounts");
			System.out.println("4. Logout");

			// Get user input
			int choice = in.getInt();

			switch (choice) {
			// Customer Information:
			case 1:
				// Print the customers using the method in the UserAuthorizer, passing in
				// employee credentials
				printCustomers();
				break;
			// Bank Account Information:
			case 2:
				// Print a list of bank accounts
				// Ask for a user to print account information:
				System.out.println("Enter a username to see bank account information");
				String user = in.get();

				// Lookup the users accounts and print the account information
				if (userAuth.usernameExists(user)) {
					printAccounts(user);
				} else {
					System.out.println("Username not found");
				}

				break;
			// Approve/Deny Pending Accounts
			case 3:
				approveOrDeny();
				break;
			// Logout:
			case 4:
				System.out.println("Thank you for working with us. Have a great day!");
				returnToMenu = false;
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}

		}

	}

	private void approveOrDeny() {
		Input in = Input.getInputSingleton();
		List<Application> apps = userAuth.getPendingApplications();
		if (apps == null || apps.isEmpty()) {
			System.out.println("No Pending Applications");
			return;
		}

		System.out.println("Account Applications:");
		for (Application app : apps) {
			System.out.println(app);
		}
		System.out.println("Enter ID to review:");
		int app_id = in.getInt();

		Application app = userAuth.getApplicationById(app_id);

		if (app == null) {
			System.out.println("Error: Invalid application id");
			return;
		} else {
			System.out.println("1. Approve");
			System.out.println("2. Deny");
			System.out.println("3. Return to menu");

			int choice = in.getInt();

			switch (choice) {
			case 1:

				// Step 1: create account with details provided:
				if (!userAuth.usernameExists(app.getUsername_1())) {
					return;
				}

				int accNum = tHandler.createAccount(app.getUsername_1());

				if (accNum == -1) {
					System.out.println("Error occured while creating account. Please try again");
				}

				else if (!app.getUsername_2().equals("") && userAuth.usernameExists(app.getUsername_2())) {
					tHandler.addOwner(accNum, app.getUsername_2());
				
				}

				if (userAuth.removeApplication(app.getApp_id())) {
					System.out.println("Account created!");
				} else {
					System.out.println("Error occured while attempting to process application. Please try again");
				}

				break;
			case 2:
				if (userAuth.removeApplication(app.getApp_id())) {
					System.out.println("Application Denied.");
				} else {
					System.out.println("Error occured while attempting to deny application. Please try again.");
				}
				break;
			case 3:
				System.out.println("Returning to Menu...");
				break;
			default:
				System.out.println("Invalid choice");
				System.out.println("Returning to Menu...");
				break;
			}
		}

	}

	private void printAccounts(String user) {
		List<Account> accounts = tHandler.getAccountsByUsername(user);
		if (user == null || accounts == null || accounts.isEmpty()) {
			System.out.println("No accounts to display.");
			return;
		}
		System.out.format("%-10s%-11s%-20s%n", "Account ", "Balance", "Holders");
		System.out.println("--------- ---------- ----------------------");
		for (Account account : accounts) {
			int accNum = account.getAccountNumber();
			Double balance = (account.getBalance());
			DecimalFormat df = new DecimalFormat("#.00");
			System.out.format("%-10d$%-10s%-20s%n", accNum, df.format(balance), account.getAccountHolders());
		}
	}

	private void printCustomers() {
		List<String> customers = userAuth.getCustomers();
		if(customers==null || customers.isEmpty()) {
			System.out.println("No customers to display");
			return;
		}else {
			System.out.println("Customers:");
			for(int i = 0; i < customers.size(); i++) {
				System.out.println(i+1+". " + customers.get(i));
			}
			System.out.println("---------------------");
		}
	}

}
