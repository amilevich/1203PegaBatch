package com.projectz.bankapp;

public class Customer {
	private String firstName;	
	private String lastName;
	private int accountid;
	private String username;
	private String password;

	public Customer(String fn, String ln, int accid, String un, String pw){
		this.firstName = fn;	
		this.lastName = ln;	
		this.accountid = accid;
		this.username = un;
		this.password = pw;
	}
	
	public void setAccountID(int accid) {
		this.accountid = accid;
	}
	
	public int getAccountID(){
		return accountid;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		String details = "New user account created.\nDetails are: " + this.firstName + " " +
							this.lastName + " " + this.accountid + " " + this.username + " " + this.password;
		return details;
	}
}
