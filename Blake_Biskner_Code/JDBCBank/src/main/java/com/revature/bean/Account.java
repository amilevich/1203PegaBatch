package com.revature.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.daoimpl.AccountDAOImpl;
import com.revature.driver.Driver;
import com.revature.util.InputValidation;

/**
 * Account POJO
 *
 * @author Blake Biskner
 * @version 2.0
 * 
 */

public class Account {
	// Class State
	private int id;
	private double balance;
	// Declare an arraylist is it is dynamic so if more than one holder (joint) can
	// add
	// Rather than have every account have a 2 element array (waste of memory)
	private List<Customer> holders = new ArrayList<Customer>();

	// Class Behavior
	// Constructors

	public Account(int id, double balance) {
		this.id = id;
		this.balance = balance;
	}

	public Account(int id, Customer holder) {
		this.id = id;
		this.balance = 0.0;
		this.holders.add(holder);
	}

	public Account(int id, Customer holder1, Customer holder2) {
		this(id, holder1);
		this.holders.add(holder2);
	}

	public Account(int id, Double balance, Customer holder1) {
		this.id = id;
		this.balance = balance;
		this.holders.add(holder1);
	}

	public Account(int id, Double balance, Customer holder1, Customer holder2) {
		this(id, balance, holder1);
		this.holders.add(holder2);
	}

	// Getters and Setters
	public double getBalance() {
		return balance;
	}

	public List<Customer> getHolders() {
		return holders;
	}

	public int getAcctNum() {
		return id;
	}

	public void addHolder(Customer customer) {
		holders.add(customer);
	}

	// Bank Transaction Methods

	/**
	 * Deposit Method
	 * 
	 * @param money
	 * 
	 */
	public void deposit(Double money) {
		balance += money;
		System.out.println("New Balance is " + balance);
	}

	/**
	 * Withdraw Method
	 * 
	 * @param money
	 */
	public void withdraw(Double money) {
		balance -= money;
		System.out.println("New Balance is " + balance);
	}

	/**
	 * Transfer Method
	 * 
	 * @param money
	 * @param acct
	 * @param userIn
	 * 
	 */

	public void transfer(Double money, Account acct, Scanner userIn) {
		AccountDAOImpl adi = new AccountDAOImpl();
		int transferNum = InputValidation.acctNumValidate(userIn);
		boolean acctExists = InputValidation.acctExistValidate(transferNum);
		if (acctExists == true) { // Account number is valid
			Account transferAcct = Driver.accounts.get(transferNum);
			// Withdraw from calling account
			this.withdraw(money);
			// Deposit into recipient
			transferAcct.deposit(money);
			try {
				adi.updateAccount(this);
				adi.updateAccount(transferAcct);
			} catch(SQLException e) {
				System.out.println("Exception Transferring");
				e.printStackTrace();
			}
		} else { // Account number is not valid
			System.out.println("Transfer Account Does Not Exist");
		}
		System.out.println("New Balance is " + balance);
	}

	@Override
	public String toString() {
		int personal = 1;
		int joint = 2;
		int holder = 0;
		int jtHolder = 1;
		if ((this.getHolders()).size() == personal) {
			return (id + ":" + balance + ":" + (holders.get(holder).getUserName()));
		} else if ((this.getHolders()).size() == joint) {
			return (id + ":" + balance + ":" + (holders.get(holder)).getUserName() + ":"
					+ (holders.get(jtHolder).getUserName()));
		} else { // No holders (ie unapproved)
			return (id + ":" + balance);
		}
	}
}
