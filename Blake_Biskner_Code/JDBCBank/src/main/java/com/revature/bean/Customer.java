package com.revature.bean;

/**
 * Customer POJO
 * 
 * @author Blake Biskner
 * @version 2.0
 */

public class Customer {
	// Class Constants
	public static final int PERSONAL = 1;
	public static final int JOINT = 2;
	public static final int REVIEW = 1;

	// Class State
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private String socialSecurity;
	private int acctType;
	private int acctStatus;
	private int acctNum;
//	private Account account=null;

	// Class Behavior
	// Constructors
	public Customer() {
		this.acctStatus = REVIEW;
		// DAO Select
	}

	public Customer(int acctNum) { // Joint Account Holder
		this.acctNum = acctNum; // Set by initial holder
		this.acctStatus = REVIEW;
		this.acctType = JOINT;
	}

	public Customer(String username, String password, String firstName, String lastName, int age, String socialSecurity,
			int acctType, int acctStatus, int acctNum) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.socialSecurity = socialSecurity;
		this.acctType = acctType;
		this.acctStatus = acctStatus;
		this.acctNum = acctNum;
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

	public String getSocialSecurity() {
		return socialSecurity;
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
	
//	public Account getAcct() {
//		return account;
//	}

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
	
//	public void setAcct(Account account) {
//		this.account=account;
//	}

	// ToString
	@Override
	public String toString() {
		return (username + ":" + password + ":" + firstName + ":" + lastName + ":" + age + ":" + socialSecurity + ":"
				+ acctType + ":" + acctStatus + ":" + acctNum + "\n");
	}
}
