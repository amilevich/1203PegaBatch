package partone;

import java.util.HashMap;

enum UserType {
	CUSTOMER, EMPLOYEE, ADMIN;
}

/*
 * UserAuthorizer singleton class for managing users
 */
public class UserAuthorizer {

	private static UserAuthorizer userAuth = new UserAuthorizer();
	private HashMap<String, User> users = new HashMap<String, User>();

	/**
	 * UserAuthorizer() Private default constructor
	 */
	private UserAuthorizer() {

	}

	/**
	 * getUserAuthSingleton()
	 * 
	 * @return singleton instance of this class
	 */
	public static UserAuthorizer getUserAuthSingleton() {
		return userAuth;
	}

	/**
	 * usernameExists(String)
	 * 
	 * @param username - username to check
	 * @return - true if a user with the given username exists, false otherwise
	 */
	public boolean usernameExists(String username) {
		return users.containsKey(username) ? true : false;
	}

	/**
	 * registers a customer user with given username and password if the username is
	 * not already in use
	 * 
	 * @param username - username of account to register
	 * @param password - password of account to register
	 * @return true if user was created, false if username was in use
	 */
	public boolean register(String username, String password) {
		// If a user already exists with the provided username, return false
		if (!users.containsKey(username)) {
			return false;
		}

		// Create a new Customer account
		Customer newCustomer = new Customer(username, password);

		// Store the customer account in the users hashmap
		users.put(username, newCustomer);
		return true;

	}

	/**
	 * registers a customer user with given username and password if the username is
	 * not already in use
	 * 
	 * @param username - username of account to register
	 * @param password - password of account to register
	 * @param type     - type of account to create (CUSTOMER, EMPLOYEE, or ADMIN)
	 * @return true if user was created, false if username was in use
	 */
	public boolean register(String username, String password, UserType type) {
		// If a user already exists with the provided username, return false
		if (users.containsKey(username)) {
			return false;
		}

		// User object to hold the new account, Customer by default
		User newUser = new Customer(username, password);

		// Create a new Customer account based on the type provided
		switch (type) {
		case CUSTOMER:
			// customer created by default, nothing needs to be done
			break;
		case EMPLOYEE:
			// newUser = new Employee(username, password);
			break;
		case ADMIN:
			// newUser = new Admin(username, password);
			break;
		// default just in case
		default:
			break;
		}

		// Store the customer account in the users hashmap
		users.put(username, newUser);
		return true;
	}

	/**
	 * login(String, String) method for users to login
	 * 
	 * @param username
	 * @param password
	 * @return corresponding user object, null if password was incorrect or
	 *         specified user does not exist
	 */
	public User login(String username, String password) {
		// If a corresponding user is not in the hashmap, returns null
		if(!users.containsKey(username)) {
			return null;
		}
		
		// If an existing user was found and the password is correct, return the user
		// otherwise, return null
		User existingUser = users.get(username);
		return existingUser.getPassword() == password ? existingUser : null;
		
		
	}

}
