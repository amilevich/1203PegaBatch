package com.revature.account;

import java.util.Scanner;

import com.revature.transactions.Transaction;
import com.revature.transactions.TransactionType;
import com.revature.user.Customer;
//THe main class for sole owner accounts
public class CheckingAccount extends Account {

	private static final double MINIMUN_DEPOSIT = 50.00;
	private Customer customer;

	//The same as other accounts sub classes,this constructor is called when ever a customer request for an account opening
	//The state of the account will be set to PENDING
	public CheckingAccount(Customer customer, Scanner scan) {
		super(customer, scan);
		this.setCustomer(customer);
		this.addAccount(customer);
		this.setCurrentState(AccountState.PENDING);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	//Method that handles the account opening, it has for a minimum deposit which creates an instance of transaction and link it to the account.
	@Override
	public boolean openAccount(Customer customer, Scanner scn) {
		boolean success = false;
		String name;
		String amount2;
		double amount;
		scn.nextLine();
		System.out.print("Fill in the information.\n\nAccount Nickname: ");
		while (!success) {
			if ((name = scn.nextLine()).length() <= 0) {
				System.out.print("\r\rError: Do not leave the Name field blank\nAccount Nickname:");
			} else if (name.length() > 30) {
				System.out.print("\n\n\nSorry, the nickname field can't have more than 30 characters");
			} else
				this.setName(name);
			success = true;
		}

		System.out.print("\nThe minimun deposit is " + MINIMUN_DEPOSIT + "\nAmount: ");
		success = false;
		while (!success) {
			if ((amount2 = scn.nextLine()).length() <= 0) {

			} else if (amount2.matches("/^-?\\d+\\.?\\d*$/")) {
				System.out.print("\r\rError: Please enter the ammount you would like to deposit.");
			} else if ((amount = Double.parseDouble(amount2)) <= MINIMUN_DEPOSIT) {
				System.out.print("\n\n\nSorry, The minimum initial deposit is " + MINIMUN_DEPOSIT + "\nAmount:");
			} else {
				Transaction depo = new Transaction(TransactionType.DEPOSIT, amount, this);
				success = true;
			}
		}
		return false;

	}
	//For linking the customer with account
	public void addAccount(Customer cst) {
		cst.getAccounts().add(this);
	}
}
