package com.revature.account;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.transactions.Transaction;
import com.revature.transactions.TransactionType;
import com.revature.user.Customer;

public class JointAccount extends Account {

	private static final double MINIMUN_DEPOSIT = 50.00;
	ArrayList<Customer> customers = new ArrayList<>();
	//Class that handles Joint Accounts, the same as other accounts but customer is an ArrayList
	public JointAccount(Customer customer, Scanner scan, Customer associate) {
		super(customer, scan);
		this.getCustomers().add(customer);
		this.getCustomers().add(associate);
		this.addAccount(customer);
		this.addAccount(associate);
		this.setCurrentState(AccountState.PENDING);
	}
	

	public ArrayList<Customer> getCustomers() {
		return customers;
	}


	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	//Method for request an application for an account
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
	//Use in the constructor to add the account to the Customer's object(links them together)
	public void addAccount(Customer cst) {
		cst.getAccounts().add(this);
	}

}
