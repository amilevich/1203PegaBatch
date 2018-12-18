package com.revature.bean;

/**
 * Customer POJO
 * 
 * @author Blake Biskner
 * @version 2.0
 */

public class Customer {
	// Class State
	private String firstName;
	private String lastName;
	private int age;
	private String socialSecurity;
	private String username;
	private String password;
	private int acctType;
	private int acctStatus;
	private int acctNum;

	// Class Behavior
	// Constructors
	public Customer() {
		this.acctStatus = 1;
		// DAO Select
	}

	public Customer(int acctNum) { // Joint Account Holder
		this.acctNum = acctNum;
		this.acctStatus = 1;
		this.acctType = 2; // Joint
	}

	public Customer(String username, int acctNum, String firstName, String lastName, int age, String socialSecurity,
			String password, char acctType, char acctStatus) {
		this.username = username;
		this.acctNum = acctNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.socialSecurity = socialSecurity;
		this.password = password;
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

	public String getUserName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getAcctType() {
		return acctType;
	}

	public int getAcctStatus() {
		return acctStatus;
	}

	public int getAcctNum() {
		return acctNum;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public void setAcctType(int acctType) {
		this.acctType = acctType;
	}

	public void setAcctStatus(int acctStatus) {
		this.acctStatus = acctStatus;
	}

	// ToString
	@Override
	public String toString() {
		return (username + ":" + acctNum + ":" + firstName + ":" + ":" + lastName + ":" + age + ":" + socialSecurity
				+ ":" + password + ":" + acctType + ":" + acctStatus + "\n");
	}
}
