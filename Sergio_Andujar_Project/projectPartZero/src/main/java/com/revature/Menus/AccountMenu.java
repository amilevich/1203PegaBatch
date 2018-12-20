package com.revature.Menus;

import com.revature.classes.Account;
import com.revature.classes.Customer;

import projectPartZero.Main;

public class AccountMenu {
	
	public static int getAccountIndex(Customer aCustomer) {
		System.out.println("Which of the following accounts will you be interacting with?");
		int counter = 0;
		int response = 0;
		for(Account account : aCustomer.getAccounts()) {
			System.out.println(counter + ": " + "account" + "-" + "Balance: " + account.getBalance());
			++counter;
		}
		response = Main.input.nextInt();
		return response;
		
	}

	public static Account choiceAccount(Customer aCustomer) {
		
		System.out.println("Which of the following accounts will you be interacting with?");
		int counter = 0;
		for(Account account : aCustomer.getAccounts()) {
			System.out.println(counter + ": " + "account" + "-" + "Balance: " + account.getBalance());
			++counter;
		}
		int response = 0;
		response = Main.input.nextInt();
		return aCustomer.getAccount(response);
			
	}

}
