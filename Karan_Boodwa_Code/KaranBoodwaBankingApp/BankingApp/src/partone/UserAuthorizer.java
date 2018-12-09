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
		if (users.containsKey(username)) {
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
			newUser = new Employee(username, password);
			break;
		case ADMIN:
			newUser = new Admin(username, password);
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
		return existingUser.getPassword().equals(password) ? existingUser : null;
		
		
	}
	
	/**
	 * Method that prints all of the users in the hashmap, if passed an employee
	 * that is currently in the hashmap (for security reasons)
	 */
	public void printCustomers(String username,String password) {
		User user = login(username, password);
		
		/* 
		 * If a user with the provided credentials does not exist in the database OR
		 * the user provided is not an Employee OR
		 * the user provided is not an Admin
		 * then the user does not have the required credentials to view the list of users
		 * (only admins and employees are 'white-listed'
		 */
		
		if(user != null) {
			String userType = user.getClass().getName();
			if(userType.equals("partone.Employee") || userType.equals("partone.Admin")) {
				// User is authorized to make this method call
				printCustomers();
				return;
			}
		}
		
		// User is not authorized to make this method call:
		System.out.println("ERROR: Invalid Credentials");
		
	}
	
	
	
	/**
	 * private printCustomers method only called by public facing one
	 * to ensure only authorized users can view the list of customers
	 */
	public void printCustomers() {
		
		// Loop through all the users in the hashmap, printing all of the customers
		for(User user : users.values()) {
			if(user.getClass().getName().equals("partone.Customer")) {
				System.out.println(user.toString());
			}
		}
	}
	
	public static void main(String[] args) {
		UserAuthorizer ua = UserAuthorizer.getUserAuthSingleton();
//		
//		// Adding users to the hashmap
		// Customers:
//		ua.register("customer1", "hispassword", UserType.CUSTOMER);
//		ua.register("customer2", "hispassword", UserType.CUSTOMER);
//		ua.register("customer3", "hispassword", UserType.CUSTOMER);
//		ua.register("customer4", "hispassword", UserType.CUSTOMER);
//		ua.register("customer5", "hispassword");
//		ua.register("customer6", "hispassword");
//		
//		// Employees:
//		ua.register("empl1", "emplpass", UserType.EMPLOYEE);
//		Employee e = (Employee) ua.login("empl1", "emplpass");
//		e.menu();
		
		
//		ua.register("empl2", "emplpass", UserType.EMPLOYEE);
//		ua.register("empl3", "emplpass", UserType.EMPLOYEE);
//		ua.register("empl4", "emplpass", UserType.EMPLOYEE);
//		
//		// Admins:
//		ua.register("admin1", "adminpass", UserType.ADMIN);
//		ua.register("admin2", "adminpass", UserType.ADMIN);
//		ua.register("admin3", "adminpass", UserType.ADMIN);
//		
//		ua.printCustomers();
	}

}
