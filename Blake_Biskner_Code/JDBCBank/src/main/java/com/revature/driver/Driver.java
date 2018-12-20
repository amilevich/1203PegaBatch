package com.revature.driver;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.revature.bean.Account;
import com.revature.bean.Customer;
import com.revature.bean.Employee;
import com.revature.daoimpl.AccountDAOImpl;
import com.revature.daoimpl.CustomerDAOImpl;
import com.revature.daoimpl.EmployeeDAOImpl;
import com.revature.menu.WelcomeMenu;

/**
 * Bank Application Driver
 * 
 * @author Blake Biskner
 * @version 2.0
 */
public class Driver {

	// Create local instances of the Database tables to reference in the program
	public static Map<String, Customer> customers = new HashMap<String, Customer>();
	public static Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
	public static Map<Integer, Account> accounts = new HashMap<Integer, Account>();

	public static void main(String[] args) {
		// Variable Initialization
		String exitMessage = "Application Ended";

		// Scanner Instantiation
		Scanner input = new Scanner(System.in);

		// Map Initialization
		pullCustomerMap();
		pullEmployeeMap();
		pullAccountMap();

		// WelcomeMenu Instantiation
		WelcomeMenu menu = new WelcomeMenu();
		menu.menuDriver(input);

		// Close Scanner object
		input.close();
		System.out.println(exitMessage);
	}

	/**
	 * Method to Update Customer HashMap with RDS Data
	 */

	public static void pullCustomerMap() {
		CustomerDAOImpl cdi = new CustomerDAOImpl();
		try {
			cdi.getCustomerMap();
		} catch (SQLException e) {
			System.out.println("Could Not Retrieve Customer Map");
			e.printStackTrace();
		}
	}

	/**
	 * Method to Update Employee HashMap with RDS Data
	 */

	public static void pullEmployeeMap() {
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		try {
			edi.getEmployeeMap();
		} catch (SQLException e) {
			System.out.println("Could Not Retrieve Employee Map");
			e.printStackTrace();
		}
	}

	/**
	 * Method to Transfer Account DB Data into Local HashMap
	 * 
	 */

	public static void pullAccountMap() {
		// Variable Declaration and Instantiation
		AccountDAOImpl adi = new AccountDAOImpl();
		List<String> holders; // List of usernames from database
		Customer customer;
		int approved = 2;
		try {
			// Initialize Account Numbers and Balances
			adi.getAccountMap();
			// Initialize Holder List
			Set<Integer> keys = accounts.keySet();
			Iterator<Integer> itr = keys.iterator();
			while (itr.hasNext()) {
				Integer key = itr.next();
				Account account = accounts.get(key);
				// Once have an account selected from map add its holders
				holders = adi.getAccountHolders(key);
				for (String username : holders) { // Iterate through each customer tied to account via junction table
					customer = customers.get(username); // Retieve customer object with given username
					if (customer.getAcctStatus() == approved) { // Only add holders to account if it has been approved
						account.addHolder(customer); // Add the customer to the account arraylist
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Could Not Instatiate Account Map");
			e.printStackTrace();
		}
	}
}
