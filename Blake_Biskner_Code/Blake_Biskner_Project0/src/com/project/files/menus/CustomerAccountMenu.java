package com.project.files.menus;

import java.util.Scanner;

import com.project.files.Customer;
import com.project.files.Driver;
import com.project.files.FileWrite;
import com.project.files.inputscreens.InputValidation;

public class CustomerAccountMenu {
	String menuName;
	String menuMessage="Welcome to Your Account Menu";
	String menuOptions[]={ "Account Actions", "Account Information", "Personal Information","Return to HomePage","Exit" };
	private String menuPrompt = "Please Select an Option By Pressing the Indicated Key"; // Has an initial newline
	// Constructor
	public CustomerAccountMenu(Customer customer) {
		this.menuName = ((customer.getFirstName()+" "+customer.getLastName()+" Account Menu"));
	}
	
	public void displayHeader() {
		System.out.println(menuName);
		// Print a line of = under each character of the menu name
		for (int i = 1; i <= menuName.length(); i++) {
			System.out.print("=");
		}
		System.out.print('\n');
		System.out.println(menuMessage);
	}

	public void displayPrompt() {
		System.out.println(menuPrompt);
	}

	public void displayHeadPrompt() {
		this.displayHeader();
		this.displayPrompt();
	}

	public void displayOptions() {
		for (int i = 0; i < menuOptions.length; i++) {
			System.out.println("[" + (i + 1) + "] " + this.menuOptions[i]);
		}
	}

	/**
	 * Display Screen
	 * 
	 * @return length of options for input validation
	 */

	public int menuDisplay() {
		// Display Welcome Messages
		displayHeadPrompt();
		displayOptions();

		return menuOptions.length;
	}

	/**
	 * Select Menu Option Based Upon Scanner Input
	 * 
	 * @param userInput
	 * @param userIn
	 */

	public void menuInput(Scanner userIn, int userInput,Customer customer) {
		int maxOp=3;
		double money=0;
		String transferName;
		switch (userInput) {
		case 1:
			System.out.println("Account Options Menu \n[1] Deposit\n[2] Withdraw\n[3]Transfer");
			int userNum=InputValidation.optionValidate(userIn, maxOp);
			switch(userNum) {
			case 1:
				System.out.println("Please Enter How Much to Deposit");
				money=InputValidation.moneyValidate(userIn); // Fix
				customer.deposit(money);
				break;
			case 2:
				System.out.println("Please Enter How Much to Withdraw");
				money=InputValidation.moneyValidate(userIn);
				customer.withdraw(money);
				break;
			case 3:
				System.out.println("Please Enter How Much to Transfer");
				money=InputValidation.moneyValidate(userIn);
				System.out.println("Please Enter Username of Transfer Account");
				transferName=InputValidation.transferValidate(userIn);
				Customer transferCustomer=Driver.customers.get(transferName);
				transferCustomer.deposit(money);
				break;
			}
			break;
		case 2: // View Account Information
			System.out.println("Account Number= "+customer.getAcctNum());
			System.out.print("Account Type= ");
			switch(customer.getAcctType()){
				case 'P':
					System.out.println("Personal Account");
					break;
				case 'J':
					System.out.println("Joint Account");
					break;
			}
			System.out.println("Account Balance= "+customer.getBalance());
			menuDriver(userIn,customer);
			break;
		case 3: // View Personal Information
			System.out.println("Name= "+(customer.getFirstName()+" "+customer.getLastName()));
			System.out.println("Age= "+customer.getAge());
			System.out.println("Username= "+customer.getUsername());
			System.out.println("Password= "+customer.getPassword());
			menuDriver(userIn,customer);
			break;
		case 4: // Return to Homepage
			HomepageMenu homepage=new HomepageMenu();
			homepage.menuDriver(userIn);
			break;
		case 5: // End Program
			break;
		}
	}
	
	/**
	 * Runs all welcome screen functions
	 * 
	 * @param userIn
	 */
	public void menuDriver(Scanner userIn, Customer customer) {
		int maxOp = menuDisplay();
		int userNum=InputValidation.optionValidate(userIn, maxOp);
		menuInput(userIn, userNum, customer);
	}
}

