package com.revature.menus;

import java.util.List;

import com.revature.input.Input;
import com.revature.jdbcbank.TransactionHandler;
import com.revature.jdbcbank.UserAuthorizer;
import com.revature.pojos.Account;
import com.revature.pojos.User;
import com.revature.pojos.User.UserType;

/**
 * Class to house the operations of the superuser
 * 
 * @author karan
 *
 */
public class SuperMenu {

	private User user = null;

	private UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();

	private TransactionHandler tHandler = TransactionHandler.getTHandler();

	public SuperMenu(User user) {
		this.user = user;
	}

	public SuperMenu() {

	}

	public void menu() {
		// Prevents potential failed null checks down the line
		if (user == null) {
			return;
		}
		Input in = Input.getInputSingleton();
		boolean returnToMenu = true;
		while (returnToMenu) {
			System.out.println("Superuser Menu. SUPER Top Secret! Use with caution.");
			System.out.println("-------------------------------");
			System.out.println("1. View User Info");
			System.out.println("2. Update User");
			System.out.println("3. Delete User");
			System.out.println("4. Create User");
			System.out.println("5. Logout");
			int choice = in.getInt();
			switch (choice) {
			case 1:
				viewUserInfo();
				break;
			case 2:
				updateUser();
				break;
			case 3:
				deleteUser();
				break;
			case 4:
				createUser();
				break;
			case 5:
				returnToMenu = false;
				System.out.println("Thank you for tampering with extremely sensitive information.");
				System.out.println("Have a good day.");
				break;
			default:
				System.out.println("Invalid option. USE WITH EXTREME CAUTION!!1!1!!!");
				break;
			}

		}

	}

	private void createUser() {
		Input in = Input.getInputSingleton();

		boolean returnToMenu = true;

		// Note: for demonstrating functionality, you can select what type of account to
		// create.
		while (returnToMenu) {
			System.out.println("Create a new user:");
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
				System.out.println("minimum 3 characters");
				String username = in.get();
				if (username.length() < 3) {
					System.out.println("Error: minimum of 3 characters needed");
				} else if (userAuth.usernameExists(username)) {
					System.out.println("Username already exists, please choose another.");
				} else {
					System.out.println("Username available!");
					usernameExists = false;

					boolean invalidPassword = true;
					while (invalidPassword) {
						System.out.println("Please enter a password");
						System.out.println("minimum 5 characters");
						String password = in.get();
						if (password.length() < 5) {
							System.out.println("Error: minimum of 5 characters needed");
						} else {
							invalidPassword = false;
							// All criteria needed for account creation done!

							User user = new User(username, password, userType);
							userAuth.register(user);
							System.out.println("Account has been created!");
							System.out.println("Returning to secret menu...");
							return;
						}
					}
				}
			}
		}

	}

	/**
	 * Walks a super user through the process of deleting
	 */
	private void deleteUser() {
		// Prevent nullptr exceptions
		/*
		 * if(user==null) { return; }
		 */
		Input in = Input.getInputSingleton();
		System.out.println("Delete User");
		System.out.println("-------------------------------");
		System.out.println("Enter the username of the user you'd like to delete");
		String username = in.get();
		if (!userAuth.usernameExists(username)) {
			System.out.println("Error: specified user does not exist.");
			return;
		} /*
			 * else if(username.equals(user.getUsername())) {
			 * System.out.println("Error: Can't delete yourself."); }
			 */
		// User exists
		List<Account> accounts = tHandler.getAccountsByUsername(username);

		// Only allow deletion if user has no accounts
		if (accounts != null && !accounts.isEmpty()) {
			for (Account a : accounts) {
				if (a.getBalance() > 0) {
					System.out.println("Error: User has active accounts. Cannot delete");
					return;
				}
			}
		}

		// User accounts all have a balance of $0.00
		boolean success = userAuth.removeUser(username);
		if (success) {
			System.out.println("User successfully removed.");
		} else {
			System.out.println("Error attempting to remove user");
		}

	}


	private void updateUser() {
		Input in = Input.getInputSingleton();
		System.out.println("Enter the username of the user you'd like to update:");
		String username = in.get();
		
		User user = userAuth.getUserByUsername(username);
		if(user == null) {
			System.out.println("Error: user not found");
		}else {
			System.out.println(user);

			System.out.println("Enter a new username for the user:");
			String newUsername = in.get();
			System.out.println("Enter a new password for the user:");
			String newPassword = in.get();
			
			if( userAuth.updateUser(username, newUsername, newPassword)) {
				System.out.println("User updated.");
			}
			else {
				System.out.println("Error while attempting update, try again");
			}
			
			
		}
	}

	private void viewUserInfo() {
		Input in = Input.getInputSingleton();
		System.out.println("Enter a username to view that user's information");
		String username = in.get();

		User user = userAuth.getUserByUsername(username);
		if (user == null) {
			System.out.println("Error: user not found.");
		} else {
			System.out.println(user);
		}

	}


}
