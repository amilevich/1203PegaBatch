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
	
	public static void main(String[] args) {
		// Variable Initialization
		String exitMessage = "Application Ended";
		// Scanner Instantiation
		Scanner input = new Scanner(System.in);
		// Map Initialization
		pullCustomerMap();
		// WelcomeMenu Instantiation
		WelcomeMenu menu = new WelcomeMenu();
		menu.menuDriver(input);
		// Close Scanner object
		input.close();
		System.out.println(exitMessage);
	}
	
	public static void pullCustomerMap(){
		customers=FileRead.readCustomerDataBase();
	}
}