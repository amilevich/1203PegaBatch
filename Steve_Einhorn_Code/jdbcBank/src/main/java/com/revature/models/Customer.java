package com.revature.models;

public class Customer extends User {
	
	String name;
	String phoneNbr;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNbr() {
		return phoneNbr;
	}
	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}
	
	public void makeApplication(String username, String password, double initDeposit) {
		
	}

}
