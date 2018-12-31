package com.bank.beans;

public class Employee {
	private int employeeId;
	private String userName;
	private String passWd;
	private int accountId;
	private int balance;
	private int pin;
	private String jobLevel = "Employee";
	private int managerId;
	private String department;
	private String firstName;
	private String lastName;
	private int streetNum;
	private String streetName;
	private String city;
	private String cState;
	private int zipCode;
	private long phone;
	private String email;

	public int getEmployeeID() {
		return employeeId;
	}

	public void setEmployeeID(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return passWd;
	}

	public void setPassword(String passWd) {
		this.passWd = passWd;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountid(int accountId) {
		this.accountId = accountId;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getJoblevel() {
		return jobLevel;
	}

	public void setJoblevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	public int getManagerID() {
		return managerId;
	}

	public void setManagerID(int managerId) {
		this.managerId = managerId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public int getStreetNum() {
		return streetNum;
	}

	public void setStreetNum(int streetNum) {
		this.streetNum = streetNum;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return cState;
	}

	public void setState(String state) {
		this.cState = state;
	}

	public int getZipcode() {
		return zipCode;
	}

	public void setZipcode(int zipcode) {
		this.zipCode = zipcode;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
