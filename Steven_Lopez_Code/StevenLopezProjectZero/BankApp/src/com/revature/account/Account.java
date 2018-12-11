package com.revature.account;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.revature.transactions.Transaction;
import com.revature.user.Admin;
import com.revature.user.Customer;
import com.revature.user.Employee;
import com.revature.user.User;
//Super Account class, holds the majority of the properties of its subclasses
public abstract class Account implements Serializable{
	
	/**
	 * 
	 */
	protected static final long serialVersionUID = -4098372291143478754L;
	static AtomicInteger nextId = new AtomicInteger();
	private int id;
	private String accountNumber;
	private double currentBalance;
	private double availableBalance;
	private String name;
	private ArrayList<Transaction> transactions = new ArrayList<>();
	private User aprovedBy;
	private LocalDate aprovedDate;
	private AccountState CurrentState;
	
	//Constructors called whenever a customer request for an application(for opening an account)
	public Account(Customer customer, Scanner scan) {
		this.setId(nextId.incrementAndGet());
		openAccount(customer, scan);
		this.setAccountNumber();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	//Generate random numbers to assign to the AccountNumber property
	private void setAccountNumber() {
		Random rand = new Random();
	    String card = "10";
	    for (int i = 0; i < 14; i++)
	    {
	        int n = rand.nextInt(10) + 0;
	        card += Integer.toString(n);
	    }
	    this.accountNumber = card.toString();
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	public User getAprovedBy() {
		return aprovedBy;
	}

	public void setAprovedBy(User aprovedBy) {
		this.aprovedBy = aprovedBy;
	}

	public LocalDate getAprovedDate() {
		return aprovedDate;
	}

	public void setAprovedDate(LocalDate aprovedDate) {
		this.aprovedDate = aprovedDate;
	}

	public AccountState getCurrentState() {
		return CurrentState;
	}

	public void setCurrentState(AccountState currentState) {
		CurrentState = currentState;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", currentBalance=" + currentBalance
				+ ", availableBalance=" + availableBalance + ", name=" + name + ", transactions=" + transactions
				+ ", aprovedBy=" + aprovedBy + ", aprovedDate=" + aprovedDate + ", CurrentState=" + CurrentState + "]";
	}
	

	public abstract boolean openAccount(Customer customer, Scanner scan);	
}
