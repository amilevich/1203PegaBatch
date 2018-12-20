package com.revature.jdbcbank;

import com.revature.input.Input;
import com.revature.menus.AdminMenu;
import com.revature.menus.CustomerMenu;
import com.revature.menus.EmployeeMenu;
import com.revature.menus.SuperMenu;
import com.revature.pojos.User;
import com.revature.pojos.User.UserType;

/**
 * Main entry point to the JDBC Bank Application Handles initial I/O (logging in
 * / registering) and houses the main menu. Upon login, user experiences are
 * tailored based on the type of user that they are.
 * 
 * @author Karan Boodwa (December, 2018)
 *
 */
public class BankApplication {

	/*
	 * UserAuthorizer: responsible for processing registration & login requests
	 * provides User object on successful login. Houses the User 'database'
	 */
	private static UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();

	/*
	 * Main Menu for the banking application.
	 * Branches out into sub menus based on user type.
	 */
	public static void mainMenu() {
		Input in = Input.getInputSingleton();

		// Welcome message
		System.out.println("Welcome to The First Bank of Karan");

		// Flag used to determine if user wants to return to this menu and choose
		// another option or quit
		boolean returnToMenu = true;

		while (returnToMenu) {
			// Initial options:
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Quit");

			// User choice:
			int choice = in.getInt();

			switch (choice) {
			// Login:
			case 1:
				login();
				break;
			// Register:
			case 2:
				register();
				break;
			// Quit:
			case 3:
				System.out.println("Have a great day!");
				returnToMenu = false;
				break;
			// User enters an integer but it doesn't correspond to a menu choice
			default:
				System.out.println("Invalid option");
				break;
			}
		}
	}

	/**
	 * login() method used to display login I/O. 
	 * Guides existing users to log in
	 */
	private static void login() {
		Input in = Input.getInputSingleton();

		System.out.println("Login");
		System.out.println("Username:");
		String username = in.get();
		if (userAuth.usernameExists(username)) {
			System.out.println("Password:");
			String password = in.get();
			User user = userAuth.login(username, password);
			if (user != null) {
				System.out.println("Login Successful!");
				switch (user.getUserType()) {
				case ADMIN:
					AdminMenu adminMenu = new AdminMenu(user);
					adminMenu.menu();
					break;
				case CUSTOMER:
					CustomerMenu customerMenu = new CustomerMenu(user);
					customerMenu.menu();
					break;
				case EMPLOYEE:
					EmployeeMenu employeeMenu = new EmployeeMenu(user);
					employeeMenu.menu();
					break;
				case SUPER:
					SuperMenu superMenu = new SuperMenu(user);
					superMenu.menu();
					break;
				default:
					break;
				}

			} else {
				System.out.println("Invalid password.");
			}
		} else {
			System.out.println("Username does not exist.");
		}

	}

	/**
	 * register() method used to display registration I/O. Guides new users to register.
	 */
	private static void register() {
		
		Input in = Input.getInputSingleton();

		boolean returnToMenu = true;

		// Note: for demonstrating functionality, you can select what type of account to
		// create.
		while (returnToMenu) {
			System.out.println("Register a new account");
			System.out.println("Select type of account");
			System.out.println("1. Customer");
			System.out.println("2. Employee");
			System.out.println("3. Admin");

			// userType is initially initialized to be a customer
			UserType userType = UserType.CUSTOMER;

			// Get user choice
			int choice = in.getInt();
			switch (choice) {
			// Customer
			case 1:
				// newUser is by default a customer, nothing special needs to be done
				break;
			case 2:
				userType = UserType.EMPLOYEE;
				break;
			case 3:
				userType = UserType.ADMIN;
				break;
			// unused default, listed just in case
			default:
				break;
			}

			// The following menu loop can be broken down into separate methods
			// e.g. getValidUsername() and getValidPassword()
			// flag used to ensure user types in a unique username
			boolean usernameExists = true;
			while (usernameExists) {
				System.out.println("Please enter a username");
				System.out.println("Minimum of 3 characters. Maximum of 15 characters");
				String username = in.get();
				if (username.length() < 3) {
					System.out.println("Error: minimum of 3 characters needed");
				} else if(username.length() > 15) {
					System.out.println("Error: maximum of 15 characters allowed");
				}
				else if (userAuth.usernameExists(username)) {
					System.out.println("Username already exists, please choose another.");
				} else {
					System.out.println("Username available!");
					usernameExists = false;

					boolean invalidPassword = true;
					while (invalidPassword) {
						System.out.println("Please enter a password");
						System.out.println("Minimum 5 characters. Maximum 15 characters");
						String password = in.get();
						if (password.length() < 5) {
							System.out.println("Error: minimum of 5 characters needed");
						} else if(password.length() > 15) {
							System.out.println("Error: maximum of 15 characters allowed");
						}
						else {
							invalidPassword = false;
							// All criteria needed for account creation done!
							User user = new User(username, password, userType);
							userAuth.register(user);
							System.out.println("Account has been created!");
							System.out.println("Returning to main menu...");
							return;
						}
					}
				}
			}
		}

	}

	/**
	 * Entry point for the entirety of the banking application
	 * @param args
	 */
	public static void main(String[] args) {
		// Launches the main menu
		mainMenu();
	}
}
