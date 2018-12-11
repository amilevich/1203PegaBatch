package com.project.files;

/**
 * Customer POJO
 * 
 * @author Blake Biskner
 * @version 1.0
 */

public class Customer {
	// Class State
	
	// Personal Information
	private String name[];
	private int birthDate;
	private int age;
	private String socialSecurity;
	private String username;
	private String password;
	private char acctType;

	// Account Information
	private char acctStatus;
	private int acctNum; // Unique for each account
	private boolean firstTransaction;
	private double balance;

	// Class Behavior

	// Constructor
	public Customer() {
		this.balance = 0;
		this.acctStatus = 'R'; // Review
		this.firstTransaction = true;
		this.acctNum=FileRead.getAcctNum();
		FileWrite.incrementAcctNum();
	}
	public Customer(int acctNum) { // Use for joint so only way to have same account number
		this.balance=0;
		this.acctStatus='R';
		this.firstTransaction=true;
		this.acctNum=acctNum; // Notice does not increment like the no argument constructor
		this.acctType='J';
	}

	// Getters and Setters
	public String[] getName() {
		return name;
	}
	public int getBirthDate() {
		return birthDate;
	}
	public int getAge() {
		return age;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public char getAcctType() {
		return acctType;
	}
	public char getAcctStatus() {
		return acctStatus;
	}
	public boolean getFirstTransaction() {
		return firstTransaction;
	}
	public double getBalance() {
		return balance;
	}
	
	public void setName(String[] name) {
		this.name=name;
	}
	public void setBirthDate(int birthDate) {
		this.birthDate=birthDate;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity=socialSecurity;
	}
	public void setUsername(String username) {
		this.username=username;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public void setAcctType(char acctType) {
		this.acctType=acctType;
	}
	public void setAcctStatus(char acctStatus) {
		this.acctStatus=acctStatus;
	}
	public void setFirstTransaction(boolean firstTransaction) {
		this.firstTransaction=firstTransaction;
	}
	public void setAcctNum() {
		
	}
	
	// ToString Method
	public String toString() {
		return (username+":"+acctNum+":"+name[0]+":"+name[1]+":"+age+":"+socialSecurity+":"+password+":"+acctType+"\n");
	}
}
