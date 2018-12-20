package com.revature.menus;

import java.text.DecimalFormat;
import java.util.List;

import com.revature.input.Input;
import com.revature.jdbcbank.TransactionHandler;
import com.revature.jdbcbank.UserAuthorizer;
import com.revature.pojos.Account;
import com.revature.pojos.Application;
import com.revature.pojos.Transaction;
import com.revature.pojos.Transaction.operation;
import com.revature.pojos.User;

/*
 * Object that houses customer menu I/O
 */
public class CustomerMenu {

	// UserAuthorizer instance:
	private UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();

	// reference to the singleton instance
	private TransactionHandler tHandler = TransactionHandler.getTHandler();
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CustomerMenu(User user) {
		super();
		this.user = user;
	}

	public CustomerMenu() {
		super();
	}

	public void menu() {
		// used to get user input
		Input input = Input.getInputSingleton();

		// boolean flag used to exit menu:
		boolean returnToMenu = true;
		System.out.println("Welcome back to the first bank of Karan, " + user.getUsername() + "!");

		while (returnToMenu) {
			System.out.println("Main Menu");
			System.out.println("1. Apply for Bank Account");
			System.out.println("2. View Account(s)");
			System.out.println("3. Withdraw");
			System.out.println("4. Deposit");
			System.out.println("5. Transfer");
			System.out.println("6. Logout");

			int choice = input.getInt();
			switch (choice) {

			// Apply for account:
			case 1:
				apply();
				break;
			// View Accounts:
			case 2:
				printaccs();
				break;
			// Withdraw:
			case 3:
				withdraw();
				break;
			// Deposit:
			case 4:
				deposit();
				break;
			// Transfer:
			case 5:
				transfer();
				break;
			// Logout:
			case 6:
				System.out.println("Thank you for banking with us. Have a great day!");
				returnToMenu = false;
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}
		}
	}

	private void transfer() {

		if (user == null) {
			return;
		}
		List<Account> bankAccounts = tHandler.getAccountsByUsername(user.getUsername());
		// If the user has no accounts, say so and return them to the menu that brought
		// them here
		if (bankAccounts.isEmpty()) {
			System.out.println("You have no account to deposit into. Apply for one today!");
			return;
		}
		/*
		 * Menu Flow: ask for account 1 check that acc number is valid & owned by
		 * customer ask for account 2 check that acc number is valid & owned by customer
		 * ask for transfer amt check that it's positive and that there's enough in acc
		 * 1 to transfer technically speaking, a transfer of funds is a withdrawal
		 * followed by a deposit
		 */

		Input in = Input.getInputSingleton();
		System.out.println("Transferring");

		System.out.println("Select account to transfer funds FROM");
		int i = 0;
		for (i = 0; i < bankAccounts.size(); i++) {
			System.out.println(i + 1 + ". " + bankAccounts.get(i));
		}
		int returnToMenuChoice = i + 1;
		System.out.println(returnToMenuChoice + ". Return to menu");

		int choice1 = in.getInt() - 1;

		if (choice1 == returnToMenuChoice - 1) {
			System.out.println("Returning...");
			return;
		}

		if (choice1 < 0 || choice1 >= bankAccounts.size()) {
			System.out.println("Invalid Selection");
			System.out.println("Returning...");
			return;
		}

		// Account number to withdraw from is stored:
		int accFrom = bankAccounts.get(choice1).getAccountNumber();

		System.out.println("Select account to transfer funds TO");

		int j = 0;
		for (j = 0; j < bankAccounts.size(); j++) {
			System.out.println(j + 1 + ". " + bankAccounts.get(j));
		}
		returnToMenuChoice = j + 1;
		System.out.println(returnToMenuChoice + ". Return to menu");

		int choice2 = in.getInt() - 1;

		if (choice2 == returnToMenuChoice - 1) {
			System.out.println("Returning...");
			return;
		}

		// NOTE: Does not allow you to transfer funds between the same account
		if (choice2 < 0 || choice2 >= bankAccounts.size() || choice2 == choice1) {
			System.out.println("Invalid Selection");
			System.out.println("Returning...");
			return;
		}

		int accTo = bankAccounts.get(choice2).getAccountNumber();

		System.out.println("Enter the amount to transfer");
		double amount = in.getDouble();

		if (amount < 0) {
			System.out.println("Error. Invalid amount entered");
			System.out.println("Returning...");
			return;
		}

		if (amount > tHandler.getAccountById(accFrom).getBalance()) {
			System.out.println("Error. Not enough funds available to make transfer");
			System.out.println("Returning...");
			return;
		}

		// All checks passed, prepare transaction and pass to transaction handler to
		// process:
		Transaction transaction = new Transaction(operation.WITHDRAW, accFrom, amount, user.getUsername());
		Transaction transaction2 = new Transaction(operation.DEPOSIT, accTo, amount, user.getUsername());

		boolean success = tHandler.processTransaction(transaction);

		if (success) {
			boolean success2 = tHandler.processTransaction(transaction2);
			if (success2) {
				System.out.println("Transfer approved");
				return;
			}
		}

		System.out.println("Transfer denied");

	}

	private void deposit() {
		// Avoid nullpointerexceptions by checking to ensure that user isn't null before
		// proceeding:
		if (user == null) {
			return;
		}
		List<Account> bankAccounts = tHandler.getAccountsByUsername(user.getUsername());
		// If the user has no accounts, say so and return them to the menu that brought
		// them here
		if (bankAccounts.isEmpty()) {
			System.out.println("You have no account to deposit into. Apply for one today!");
			return;
		}

		/*
		 * Menu flow: ask for acc number check that acc number is valid & owned by
		 * customer ask for amount to deposit check that amount is positive (valid)
		 * prepare transaction object send deposit to transaction handler to process
		 */

		boolean returnToMenu = true;
		Input in = Input.getInputSingleton();
		while (returnToMenu) {
			System.out.println("Deposit");
			System.out.println("Select account:");

			// Note: list index is 1 more than loop index
			// i declared and initialized outside of loop to be used after to display a
			// return to main menu option
			int i = 0;
			for (i = 0; i < bankAccounts.size(); i++) {
				System.out.println(i + 1 + ". " + bankAccounts.get(i));
			}
			int returnToMenuChoice = i + 1;

			System.out.println(returnToMenuChoice + ". Return to Main Menu");

			// later offset by 1 to account for different number displayed to user
			int accountChoice = in.getInt();

			// If they chose to return to the main menu, do so before checking anything else
			if (accountChoice == returnToMenuChoice) {
				System.out.println("Returning to Main Menu...");
				returnToMenu = false;
				break;
			}

			accountChoice--;

			// Check if they selected an invalid entry on the list and refresh current menu
			if (accountChoice < 0 || accountChoice > bankAccounts.size() - 1) {
				System.out.println("Invalid account selected");
				continue;
			}

			// List option is valid, prepare a deposit transaction
			else {

				// Get the account number from the customers list of accounts
				int accountNumber = bankAccounts.get(accountChoice).getAccountNumber();

				// Get amount to deposit from user:
				System.out.println("Enter amount to deposit");
				Double depositAmount = in.getDouble();

				if (depositAmount < 0) {
					System.out.println("Error. Invalid deposit amount entered.");
				}

				else {
					// Send transaction to the transaction handler to be processed
					boolean success = tHandler.processTransaction(
							new Transaction(operation.DEPOSIT, accountNumber, depositAmount, user.getUsername()));

					if (success) {
						System.out.println("Transaction approved");
						returnToMenu = false;
					} else {
						System.out.println("Transaction declined");
						// Returns user to current menu so they can attempt deposit again
					}
				}

			}

		}

	}

	private void withdraw() {
		// Avoid nullpointerexceptions by checking to ensure that user isn't null before
		// proceeding:
		if (user == null) {
			return;
		}
		List<Account> bankAccounts = tHandler.getAccountsByUsername(user.getUsername());
		// If the user has no accounts, say so and return them to the menu that brought
		// them here
		if (bankAccounts == null || bankAccounts.isEmpty()) {
			System.out.println("You have no account to withdraw from. Apply for one today!");
			return;
		}

		/*
		 * Menu flow: ask for acc number check that acc number is valid & owned by
		 * customer ask for amount to withdraw check that amount is positive (valid & <=
		 * account balance) prepare transaction object send deposit to transaction
		 * handler to process
		 */

		boolean returnToMenu = true;
		Input in = Input.getInputSingleton();
		while (returnToMenu) {
			System.out.println("Withdraw");
			System.out.println("Select account:");

			// Note: list index is 1 more than loop index
			// i declared and initialized outside of loop to be used after to display a
			// return to main menu option
			int i = 0;
			for (i = 0; i < bankAccounts.size(); i++) {
				System.out.println(i + 1 + ". " + bankAccounts.get(i));
			}
			int returnToMenuChoice = i + 1;

			System.out.println(returnToMenuChoice + ". Return to Main Menu");

			// later offset by 1 to account for different number displayed to user
			int accountChoice = in.getInt();

			// If they chose to return to the main menu, do so before checking anything else
			if (accountChoice == returnToMenuChoice) {
				System.out.println("Returning to Main Menu...");
				returnToMenu = false;
				break;
			}

			accountChoice--;

			// Check if they selected an invalid entry on the list and refresh current menu
			if (accountChoice < 0 || accountChoice > bankAccounts.size() - 1) {
				System.out.println("Invalid account selected");
				continue;
			}

			// List option is valid, prepare a withdraw transaction
			else {

				// Get the account number from the customers list of accounts
				int accountNumber = bankAccounts.get(accountChoice).getAccountNumber();

				// Get amount to withdraw from user:
				System.out.println("Enter amount to withdraw");
				Double amount = in.getDouble();
				Double balance = tHandler.getAccountById(accountNumber).getBalance();

				if (amount < 0 || amount > balance) {
					System.out.println("Error. Invalid withdrawal amount entered.");
				}

				else {
					// Send transaction to the transaction handler to be processed
					boolean success = tHandler.processTransaction(
							new Transaction(operation.WITHDRAW, accountNumber, amount, user.getUsername()));

					if (success) {
						System.out.println("Transaction approved");
						returnToMenu = false;
					} else {
						System.out.println("Transaction declined");
						// Returns user to current menu so they can try to withdraw again
					}
				}

			}

		}

	}

	/**
	 * Prints out the users accounts in a nice 'tabular' fashion
	 */
	private void printaccs() {
		List<Account> accounts = tHandler.getAccountsByUsername(user.getUsername());
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

	/**
	 * Walks a user through the process of applying for a bank account and files
	 * their application to be reviewed by an employee or admin
	 */
	private void apply() {
		Input in = Input.getInputSingleton();
		System.out.println("Applying for a Bank Application");

		Application app = new Application(user.getId());

		boolean returnToMenu = true;

		while (returnToMenu) {
			System.out.println("Is this a joint account?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.println("3. Return to Main Menu (Cancels application)");

			int choice = in.getInt();

			switch (choice) {
			case 1:
				System.out.println("Enter the username of the second account holder");
				String secondHolder = in.get();
				if (userAuth.usernameExists(secondHolder) && !user.getUsername().equals(secondHolder)) {
					app.setUser_id2(userAuth.getUserId(secondHolder));

					returnToMenu = false;
				} else {
					System.out.println("Invalid second account holder.");
				}

				break;
			case 2:
				returnToMenu = false;
				break;
			case 3:
				returnToMenu = false;
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}
		}
		// Submits the bank application
		if (userAuth.submitApplication(app) == true) {
			System.out.println("Application submitted!");
		} else {
			System.out.println("Application not submitted.");
		}

	}

}
