package com.project.files;

import java.util.Scanner;

/**
 * Driver Class for Bank Account Application
 * 
 * @author Blake Biskner
 * @version 1.0
 */

public class Driver {

	public static void main(String[] args) {
		// Variable Initialization
		String exitMessage = "Application Ended";
		// Scanner Instantiation
		Scanner input = new Scanner(System.in);
		// WelcomeMenu Instantiation
		WelcomeMenu menu = new WelcomeMenu();
		menu.menuDriver(input);
		// Close Scanner object
		input.close();
		System.out.println(exitMessage);
	}
}