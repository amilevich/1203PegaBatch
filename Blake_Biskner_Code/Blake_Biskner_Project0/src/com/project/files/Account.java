package com.project.files;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.files.inputscreens.InputValidation;

public class Account {
	// Class State
	private int id;
	private double balance;
	private ArrayList<Customer> holders=new ArrayList<Customer>(); 
	// Use arraylist as it is dynamic so if more than one holder (joint) can add
	// Rather than have every account have a 2 element array (waste of memory)
	
	public Account(int id, Customer holder) {
		this.id=id;
		this.balance=0.0;
		this.holders.add(holder);
	}
	
	public Account(int id, Customer holder1, Customer holder2) { // Joint Account
		this(id, holder1);
		this.holders.add(holder2);
	}
	
	public Account(int id, Double balance, Customer holder1) {
		this.id=id;
		this.balance=balance;
		this.holders.add(holder1);
	}
	public Account(int id, Double balance, Customer holder1, Customer holder2) {
		this(id, balance,holder1);
		this.holders.add(holder2);
	}
	public double getBalance() {
		return balance;
	}
	public ArrayList<Customer> getHolders(){
		return holders;
	}
	public int getAcctNum() {
		return id;
	}
	public void addHolder(Customer customer) {
		holders.add(customer);
	}
	
	public void deposit(Double money) {
		balance+=money;
		System.out.println("New Balance is "+balance);
	}
	public void withdraw(Double money) {
		balance-=money;
		System.out.println("New Balance is "+balance);
	}
	public void transfer(Double money, Account acct, Scanner userIn) {
		int transferNum=InputValidation.acctNumValidate(userIn);
		boolean exists=InputValidation.acctExistValidate(transferNum);
		if(exists) {
			Account transferAcct=Driver.accounts.get(transferNum);
			this.withdraw(money);
			transferAcct.deposit(money);
		}
		else {
			System.out.println("Transfer Account Does Not Exist");
		}
		System.out.println("New Balance is "+balance);
	}
	public String toString() {
		String toStr=(id+":"+balance+":"+(holders.get(0)).getUsername());
		if(holders.size()==2) {
			String joint=(":"+(holders.get(1)).getUsername());
			toStr=toStr+joint;
		}
		return(toStr+'\n');
	}
}
