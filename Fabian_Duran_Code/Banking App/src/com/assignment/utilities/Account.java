package com.assignment.utilities;

import java.util.HashMap;

public class Account {
	
	int ID = 8;//for testing, when loaded with txt or db, will start off at largest known ID
	boolean isOpen = true;
	boolean isApproved = false;
	char accType;
	private static HashMap<Integer, Account> map = new HashMap<>();
	private static int idMaker = 0;
	Double balance = 0.0;
	static boolean aload = false;
	
	static Account a1 = new Account (1, true, true, 'r', 100.00);
	static Account a2 = new Account (2, true, true, 'j', 200.00);
	static Account a3 = new Account (3, true, true, 'r', 300.00);
	static Account a4 = new Account (4, true, true, 'j', 400.00);
	static Account a5 = new Account (5, true, true, 'r', 500.00);
	
	static Account a6 = new Account (6, true, false, 'r', 0.00);
	static Account a7 = new Account (7, false, false, 'r', 0.00);
	static Account a8 = new Account (8, false, true, 'r', 0.00);
	
	
	public Account() {};//default constructor
	
	public Account (int ID, boolean isOpen, boolean isApproved, char accType, Double accAmount) {//to be used for reading in text files? Probably...
		this.ID = ID;
		this.isOpen = isOpen;
		this.isApproved = isApproved;
		this.accType = accType;
		this.balance = accAmount;
	}	
	
	
	@Override
	public String toString() {//reads account information
		return "Account [ID: " + ID + ", accType: " + accType + ", balance: " + balance + "]";
	}
	
	public static void aloaded() {
		map.put(1, a1);
		map.put(2, a2);
		map.put(3, a3);
		map.put(4, a4);
		map.put(5, a5);
		map.put(6, a6);
		map.put(7, a7);
		map.put(8, a8);
		aload = true;
	}
	
	public static boolean getaload() {
		return aload;
	}
	
	public int getID() {
		return ID;
	}

	public void setID() {//will set an ID based on the whatever count the idMaker is on, will increment one for the next account to be made, this way accounts all have unique IDs	
		this.ID = idMaker;
		idMaker++;
	}

	public boolean isOpen() {//state of account
		return isOpen;
	}

	public void setOpen(boolean isOpen) {//true if account is opened, false if account has been cancelled
		this.isOpen = isOpen;
	}

	public boolean isApproved() {//returns state of approval
		return isApproved;
	}

	public void setApproved(boolean isApproved) {//true if account is approved, false if denied
		this.isApproved = isApproved;
	}

	public char getAccType() {//produces account type
		return accType;
	}

	public void setAccType(char accType) {//will set account to 'r' for regular or 'j' for joint
		this.accType = accType;
	}

	public Double getBalance() {
		return balance;
	}
	public Double deposit(Double amount) {
		return this.balance + amount;
	}
	public Double withdraw(Double amount) {//correct amounts are checked in menus class
			return balance - amount;
	}
	
	public void transfer (Double amount, int iD) {//correct amounts are checked in menus class
		this.balance = this.balance - amount;
		Account.pullFromHash(iD).deposit(amount);		
	}
	
	public static void addtoHash(Account toBeAdded, int iD) {
		map.put(iD , toBeAdded);
	}
	public static Account pullFromHash(Integer iD) {
		Account a = map.get(iD);
		return a;
	}
	public static int hashsize() {
		return map.size();
	}
	public static void logout() {//eventually, the hope is that this method will be used to write all data to text file/database prior to exiting, but for now, its this
		System.exit(0);
	}
	
}
