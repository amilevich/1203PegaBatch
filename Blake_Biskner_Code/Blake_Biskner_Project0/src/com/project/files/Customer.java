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
	private String firstName;
	private String lastName;
	private int age;
	private String socialSecurity;
	private String username;
	private String password;
	private char acctType;

	// Account Information
	private char acctStatus;
	private int acctNum; // Unique for each account
	private double balance;

	// Class Behavior

	// Constructor
	public Customer() {
		this.balance = 0;
		this.acctStatus = 'R'; // Review
		this.acctNum = FileRead.getAcctNum();
		FileWrite.incrementAcctNum();
	}

	public Customer(int acctNum) { // Use for joint so only way to have same account number
		this.balance = 0;
		this.acctStatus = 'R';
		this.acctNum = acctNum; // Notice does not increment like the no argument constructor
		this.acctType = 'J';
	}

	public Customer(String username, int acctNum, String firstName, String lastName, int age, String socialSecurity, String password,
			char acctType, double balance, char acctStatus) {
		this.username = username;
		this.acctNum = acctNum;
		this.firstName=firstName;
		this.lastName=lastName;
		this.age = age;
		this.socialSecurity = socialSecurity;
		this.password = password;
		this.acctType = acctType;
		this.balance = balance;
		this.acctStatus = acctStatus;
	}

	// Getters and Setters
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
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

	public int getAcctNum() {
		return acctNum;
	}

	public double getBalance() {
		return balance;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAcctType(char acctType) {
		this.acctType = acctType;
	}

	public void setAcctStatus(char acctStatus) {
		this.acctStatus = acctStatus;
	}

	// Mutator Methods
	public void deposit(double num) {
		this.balance += num;
	}
	public void withdraw(double num) {
		double diff=this.balance-num;
		if((diff)<0) {
			System.out.println("Over Drawn by "+ Math.abs(diff));
			System.out.println("Please Enter a Lesser Amount");
		}else {
			this.balance-=num;
		}
	}
	public void transfer(double num, Customer customer) {
		double tmp=this.balance;
		double diff=tmp-this.balance;
		if((tmp-this.balance)<0) {
			System.out.println("Over Drawn by "+ Math.abs(diff));
			System.out.println("Please Enter a Lesser Amount");
		}else {
			this.balance-=num;
			customer.deposit(num);
		}
	}

	// ToString Method
	public String toString() {
		return (username + ":" + acctNum + ":" + firstName + ":" + lastName + ":" + age + ":" + socialSecurity + ":"
				+ password + ":" + acctType + ":" + balance + ":" + acctStatus + "\n");
	}
}
