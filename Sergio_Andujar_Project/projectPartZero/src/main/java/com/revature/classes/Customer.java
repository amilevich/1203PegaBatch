package com.revature.classes;

import java.util.ArrayList;

public class Customer implements User{
	
	private int id;
	private String firstName;
	private String lastName;
	private ArrayList<Account> ownedAccounts = new ArrayList<Account>();
	
	public Customer() {
		
	}
	
	public Customer(int id, String firstName, String lastName){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
		
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	@Override
	public String getName() {
		return this.firstName + this.lastName;
		
	}
	
	@Override
	public String toString() {
		return "Greetings:" + this.firstName + " " + this.lastName;
	}
	
	public int getNumberOfAccounts() {
		return this.ownedAccounts.size();
	}
	
	public void displayAccounts() {
		for(Account acc: this.ownedAccounts) {
			System.out.println(acc);
		}
		
	}

	public int getId() {
		return this.id;
	}
	
	public ArrayList<Account> getAccounts() {
		return this.ownedAccounts;
	}
	
	public void storeAccount(Account aAccount) {
		this.ownedAccounts.add(aAccount);
	}

	public Account getAccount(int index) {
		return this.ownedAccounts.get(index);
	}
	
	public void setAccounts(ArrayList<Account> accounts){
		this.ownedAccounts = accounts;
	}
	
}
