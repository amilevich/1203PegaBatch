package partone;

import java.util.Scanner;

/**
 * Main driver class for the Banking Application. Handles initialization and
 * initial I/O as well as connections between Users and Accounts
 * 
 * @author Karan
 *
 */
public class BankingApp {

	/*
	 * UserAuthorizer: responsible for processing registration & login requests
	 * provides User object on successful login.
	 * Houses the User 'database'
	 */
	private static UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();
	
	/*
	 * mainMenu() function handles initial I/O and interaction with above
	 * structures
	 */
	public static void mainMenu() {
		// Input singleton used for Scanner input
		Input in = Input.getInputSingleton();
		
		System.out.println("Welcome to The First Bank of Karan");
		
		// Flag used to determine if user wants to return to this menu and choose another option or quit
		boolean returnToMenu = true;
		
		while(returnToMenu) {
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
			default:
				System.out.println("Invalid option");
				break;
			}
		}
	}
	
	/**
	 * login()
	 * method used to display io, guiding existing users to log in
	 */
	private static void login() {
		Input in = Input.getInputSingleton();
		
		// boolean flag used to keep user on this menu
		boolean returnToMenu = true;
		
		while(returnToMenu) {
			System.out.println("Login");
			System.out.println("Username:");
			String username = in.get();
			if( userAuth.usernameExists(username) ) {
				System.out.println("Password:");
				String password = in.get();
				User user = userAuth.login(username, password);
				if(user!=null) {
					user.menu();
				}
				else {
					System.out.println("Invalid password.");
				}
			}
			else {
				System.out.println("Username does not exist.");
			}
			System.out.println("1. Login");
			System.out.println("2. Return to main menu");
			int choice = in.getInt();
			switch (choice) {
			// User wants to attempt to login again, do nothing (will refresh current menu)
			case 1:
				break;
			// Send user back to main menu
			case 2:
				returnToMenu = false;
				break;
			// Invalid option, sending user back to main menu 
			default:
				System.out.println("Invalid option, returning to main menu...");
				returnToMenu = false;
				break;
			}
		}
	}
	
	/**
	 * register()
	 * method used to display io, guiding new users to register
	 */
	private static void register() {
		Input in = Input.getInputSingleton();
		
		boolean returnToMenu = true;
		
		// Note: for demonstrating functionality, you can select what type of account to create. 
		while(returnToMenu) {
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
			while(usernameExists) {
				System.out.println("Please enter a username");
				System.out.println("minimum 3 characters");
				String username = in.get();
				if(username.length() < 3) {
					System.out.println("Error: minimum of 3 characters needed");
				}
				else if(userAuth.usernameExists(username)){
					System.out.println("Username already exists, please choose another.");
				}
				else {
					System.out.println("Username available!");
					usernameExists = false;
					
					boolean invalidPassword = true;
					while(invalidPassword) {
						System.out.println("Please enter a password");
						System.out.println("minimum 5 characters");
						String password = in.get();
						if(password.length() < 5) {
							System.out.println("Error: minimum of 5 characters needed");
						}
						else {
							invalidPassword = false;
							// All criteria needed for account creation done!
							userAuth.register(username, password, userType);
							System.out.println("Account has been created!");
							System.out.println("Returning to main menu...");
							return;
						}
					
					}
					
					
				}
			}
			
			
		}
		
		
	}
	
	public static void main(String[] args) {
		// Call the menu method which serves as the I/O handler for the app
		mainMenu();

	}

}
