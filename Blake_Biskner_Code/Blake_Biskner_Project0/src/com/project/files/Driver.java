package com.project.files;

import java.util.HashMap;
import java.util.Scanner;


// Import menu classes
import com.project.files.menus.WelcomeMenu;

/**
 * Driver Class for Bank Account Application
 * 
 * @author Blake Biskner
 * @version 1.0
 */

public class Driver {
	
	public static HashMap<String,Customer> customers;
	public static HashMap<Integer,Account> accounts;
	public static HashMap<Integer,Employee> employees;
	
	public static void main(String[] args) {
		// Variable Initialization
		String exitMessage = "Application Ended";
		// Scanner Instantiation
		Scanner input = new Scanner(System.in);
		// Map Initialization
		pullCustomerMap();
		pullEmpMap();
		pullAcctMap(); // Since this references customers the customer map must be instantiated prior to this call
		// WelcomeMenu Instantiation
		WelcomeMenu menu = new WelcomeMenu();
		menu.menuDriver(input);
		// Update Databases
		FileWrite.updateAccountDataBase();
		FileWrite.updateCustomerDataBase();
		// Close Scanner object
		input.close();
		System.out.println(exitMessage);
		
//		// Test Set Manipulation
//		Set<Integer> keys=accounts.keySet();
//		Iterator<Integer> itr=keys.iterator();
//		while(itr.hasNext()) {
//			Integer key=itr.next();
//			System.out.println(accounts.get(key));
//		}
//		Set<String> val=customers.keySet();
//		Iterator<String> iter=val.iterator();
//		while(iter.hasNext()) {
//			String value=iter.next();
//			System.out.println(customers.get(value));
//		}
//		
		
	}
	
	public static void pullCustomerMap(){
		customers=FileRead.readCustomerDataBase();
	}
	
	public static void pullEmpMap() {
		employees=FileRead.readEmployeeDataBase();
	}
	
	public static void pullAcctMap() {
		accounts=FileRead.readAccountDataBase();
	}
}