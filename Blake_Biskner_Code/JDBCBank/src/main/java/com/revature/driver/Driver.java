package com.revature.driver;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.revature.bean.Customer;
import com.revature.daoimpl.CustomerDAOImpl;
import com.revature.menu.WelcomeMenu;

/**
 * Bank Application Driver
 * 
 * @author Blake Biskner
 * @version 2.0
 */
public class Driver {

	// Create local instances of the Database tables to reference in the program
	public static Map<String,Customer> customers=new HashMap<String, Customer>();
	// public static HashMap<Integer, Account> accounts;
	// public static HashMap<Integer, Employee> employees;

	public static void main(String[] args) {
		// Variable Initialization
		String exitMessage = "Application Ended";

		// Scanner Instantiation
		Scanner input = new Scanner(System.in);

		// Map Initialization
		pullCustomerMap();
		// pullEmpMap();
		// pullAcctMap();
		// Since this references customers the customer map must be instantiated prior
		// to this call

		// WelcomeMenu Instantiation
		WelcomeMenu menu = new WelcomeMenu();
		menu.menuDriver(input);
		// Update Databases
		// Do this

		// Close Scanner object
		input.close();
		System.out.println(exitMessage);

		Set<String> keys = customers.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			Customer value = customers.get(key);
			System.out.println(value);
		}

	}

	public static void pullCustomerMap() {
		CustomerDAOImpl cdi = new CustomerDAOImpl();
		try {
			cdi.getCustomerMap();
		} catch (SQLException e) {
			System.out.println("Could Not Retrieve Customer Map");
			e.printStackTrace();
		}
	}

	// public static void pullCustomerMap() {
	// customers = FileRead.readCustomerDataBase();
	// }
	//
	// public static void pullEmpMap() {
	// employees = FileRead.readEmployeeDataBase();
	// }
	//
	// public static void pullAcctMap() {
	// accounts = FileRead.readAccountDataBase();
	// }
}
