package com.revature.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.DAO.AccountDaoImpl;
import com.revature.DAO.CustomerDaoImpl;
import com.revature.DAO.TransactionDaoImpl;
import com.revature.DAO.UserDaoImpl;
import com.revature.enums.Account_State;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.User;
import com.revature.util.Authentication;
import com.revature.util.Validation;
import com.revature.views.MenuForm;

public class UserManager {

	public void mainMenu() {
		boolean success = false;
		User user = new User();
		int choice = 0;
		MenuForm menu = new MenuForm();
		do {
			menu.showBankAppIntro();
			System.out.print("\n	1. Register" + "\r	2. Sign In	\n	0. Close Application\r\rSelect option: ");

			choice = Validation.getIntInput();
			if (choice == 1) {
				user = new Authentication().register();
				switch (user.getRole()) {
				case "CUSTOMER":
					new UserManager().customerMenu(new CustomerDaoImpl().getCustomerById(user.getId()));
					continue;
				}
			} else if (choice == 2) {
				user = new Authentication().signIn();
				switch (user.getRole()) {
				case "CUSTOMER":
					new UserManager().customerMenu(new CustomerDaoImpl().getCustomerById(user.getId()));
					continue;

				case "EMPLOYEE":
					new UserManager().employeeMenu(new UserDaoImpl().getUserById(user.getId()));
					continue;

				case "ADMIN":
					new UserManager().adminMenu(new UserDaoImpl().getUserById(user.getId()));
					continue;
				}
			}

			else if (choice == 0) {

				System.out.println("\n\n\n\nThanks for bankign with us, have a nice day!");
				success = true;
			} else {
				System.out.println("\r\r\rPlease select one of the following available choices.");
				success = false;
			}

		} while (!success);

	}

	// The main menu for customers where they can access their accounts, make
	// deposit, transfers,
	// withdrawals and request to open another account
	public void customerMenu(Customer customer) {
		MenuForm menu = new MenuForm();
		Account account = new Account();
		int choice = 0;
		do {
			menu.showCustomerMenu();
			choice = Validation.getIntInput();
			switch (choice) {
			case 1:
				choice = menu.showOpenAccount();

				switch (choice) {

				case 1: // Open Sole Owner Account
					new AccountManager().SoleAccountAppForm(customer.getId());
					break;

				case 2: // Open Joint Account
					new AccountManager().JointAccountAppForm(customer.getId());
					break;
				}
			case 2:// View Accounts Balances
				List<Account> accList = new ArrayList<Account>();
				menu.showAccountBalances(new AccountDaoImpl().getAccountsByUserId(customer.getId()));
				break;

			case 3:// Perform Withdrawal
				account = menu.showCustomerAccountsC(new AccountDaoImpl().getAccountsByUserId(customer.getId()));
				if (account != null)
					menu.showWithdrawal(account, customer.getId());
				else
					System.out.println("Account not available!");
				break;

			case 4:// Perform Deposit
				account = menu.showCustomerAccountsC(new AccountDaoImpl().getAccountsByUserId(customer.getId()));
				menu.showDeposit(account, customer.getId());
				Validation.getPause();
				break;

			case 5:// Perform Transfer
				account = menu.showCustomerAccountsC(new AccountDaoImpl().getAccountsByUserId(customer.getId()));
				menu.showTransfer(account, customer.getId());
				Validation.getPause();
				break;

			case 6:// View an Account Transactions
				account = menu.showCustomerAccountsC(new AccountDaoImpl().getAccountsByUserId(customer.getId()));
				menu.showAccountTransactions(new TransactionDaoImpl().getAllAccountTransactions(account.getId()));
				Validation.getPause();
				break;

			case 7:// Delete an Account
				account = menu.showCustomerAccountsC(new AccountDaoImpl().getAccountsByUserId(customer.getId()));
				account.setState(Account_State.CANCELED);
				new AccountDaoImpl().updateAccount(account);
				System.out.println("Account " + account.getId() + " Closed");
				Validation.getPause();
				break;

			case 8:// View Personal Information
				menu.showPersonalInformation(customer);
				Validation.getPause();
				break;

			case 0:
				break;
			}
		} while (choice != 0);
	}

	public void employeeMenu(User user) {

		MenuForm menu = new MenuForm();
		Account account = new Account();
		int choice = 0;
		int customer = 0;
		menu.showAllCustomers(new CustomerDaoImpl().getAllCustomers());
		System.out.print(
				"\n\nWelcome Back to the Bank App, fellow employee! ");
		do {
		System.out.print("\n\nSelect a customer by using the ID.\nSelect: ");
		customer = Validation.getIntInput();
		}while(new CustomerDaoImpl().getCustomerById(customer) == null);
		do {
			
			menu.showEmployeeMenu(customer);
			choice = Validation.getIntInput();
			switch (choice) {
			case 1:// Approve applications
				account = menu.showCustomerAccountsE(new AccountDaoImpl().getAccountsByUserId(customer));
				if (account != null) {
					if (account.getState() == Account_State.PENDING) {
						String nick = "";
						System.out.print("Would you like to Approve(a) or Deny(d) this application? ");
						Validation.getPause();
						nick = Validation.getStringInput();
						if (nick.contains("d")) {
							
							account.setState(Account_State.DENIED);
							account.setApprovedBy(user.getId());
							account.setApprovedDate(LocalDate.now());
							new AccountDaoImpl().updateAccount(account);
							System.out.println("Account denied succesfully");
							
						} else if (nick.contains("a")) {
							
							account.setState(Account_State.APPROVED);
							account.setApprovedBy(user.getId());
							account.setApprovedDate(LocalDate.now());
							new AccountDaoImpl().updateAccount(account);
							System.out.println("Account approved succesfully");
						} else
							System.out.println("Invalid selection.");
					} else
						System.out.println("Invalid selection.");
				}
				Validation.getPause();
				break;
			case 2: // show account balances
				menu.showAccountBalances(new AccountDaoImpl().getAccountsByUserId(customer));
				Validation.getPause();
				break;

			case 3:// View Personal Information
				menu.showPersonalInformation(new CustomerDaoImpl().getCustomerById(customer));
				Validation.getPause();
				break;

			case 4://View Transactions
				account = menu.showCustomerAccountsE(new AccountDaoImpl().getAccountsByUserId(customer));
				if(account != null)
					menu.showAccountTransactions(new TransactionDaoImpl().getAllAccountTransactions(account.getId()));
				Validation.getPause();
				break;
				
			case 0:
				break;
			}
		} while (choice != 0);
	}

	public void adminMenu(User user) {
		
		MenuForm menu = new MenuForm();
		Account account = new Account();
		int choice = 0;
		int customer = 0;
		System.out.println("\n\nAdmin: " + user.getUsername());
		menu.showAllCustomers(new CustomerDaoImpl().getAllCustomers());
		do {
		System.out.print("\n\nSelect a customer by using the ID.\nSelect: ");
		customer = Validation.getIntInput();
		
		}while(new CustomerDaoImpl().getCustomerById(customer) == null);
		do {
			Customer cust = new CustomerDaoImpl().getCustomerById(customer);
			System.out.println("\n\nAdmin: " + user.getUsername());
			menu.showAdminMenu(customer);
			choice = Validation.getIntInput();
			switch (choice) {
			case 1:// Approve applications
				account = menu.showCustomerAccountsA(new AccountDaoImpl().getAccountsByUserId(customer));
				if (account != null) {
					if (account.getState() == Account_State.PENDING) {
						String nick = "";
						System.out.print("Would you like to Approve(a) , Deny(d) or Close(c) this application? ");
						Validation.getPause();
						nick = Validation.getStringInput();
						if (nick.contains("d")) {
							
							account.setState(Account_State.DENIED);
							account.setApprovedBy(user.getId());
							account.setApprovedDate(LocalDate.now());
							new AccountDaoImpl().updateAccount(account);
							System.out.println("Account denied succesfully");
							
						} else if (nick.contains("a")) {
							
							account.setState(Account_State.APPROVED);
							account.setApprovedBy(user.getId());
							account.setApprovedDate(LocalDate.now());
							new AccountDaoImpl().updateAccount(account);
							System.out.println("Account approved succesfully");
							
						} else if (nick.contains("c")) {
							account.setState(Account_State.CANCELED);
							account.setApprovedBy(user.getId());
							account.setApprovedDate(LocalDate.now());
							new AccountDaoImpl().updateAccount(account);
						} else
							System.out.println("Invalid selection.");
					} else
						System.out.println("Invalid selection.");
				}
				Validation.getPause();
				break;
			case 2: // show account balances
				menu.showAccountBalances(new AccountDaoImpl().getAccountsByUserId(customer));
				Validation.getPause();
				break;

			case 4:// View Personal Information
				menu.showPersonalInformation(new CustomerDaoImpl().getCustomerById(customer));
				Validation.getPause();
				break;

			case 3://View Transactions
				account = menu.showCustomerAccountsE(new AccountDaoImpl().getAccountsByUserId(customer));
				if(account != null)
					menu.showAccountTransactions(new TransactionDaoImpl().getAllAccountTransactions(account.getId()));
				Validation.getPause();
				break;
				
			case 5:// Perform Withdrawal
				account = menu.showCustomerAccountsA(new AccountDaoImpl().getAccountsByUserId(cust.getId()));
				if (account != null)
					menu.showWithdrawal(account, cust.getId());
				else
					System.out.println("Account not available!");
				break;

			case 6:// Perform Deposit
				account = menu.showCustomerAccountsA(new AccountDaoImpl().getAccountsByUserId(cust.getId()));
				menu.showDeposit(account, cust.getId());
				Validation.getPause();
				break;

			case 7:// Perform Transfer
				account = menu.showCustomerAccountsA(new AccountDaoImpl().getAccountsByUserId(cust.getId()));
				menu.showTransfer(account, cust.getId());
				Validation.getPause();
				break;
			case 0:
				break;
			}
		} while (choice != 0);
		
	}

}
