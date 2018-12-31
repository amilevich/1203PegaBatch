package com.bank.beans;

public class Customer {
	private int customerId;
	private String firstName;	
	private String lastName;
	private int streetNum;
	private String streetName;
	private String city;
	private String cState;
	private int zipCode; 
	private long phone;
	private String email;
	private int accountId;
	private int pin;
	private String userName;
	private String passWd;

	public Customer() { //Dummy constructor for comparison.	
	}
	
	public Customer(String fn, String ln, int streetNum, String streetName, String city, String cstate, int zipcode, long phone, String email, int accid, int pin, String un, String pw){
		this.firstName = fn;	
		this.lastName = ln;	
		this.streetNum = streetNum;
		this.streetName = streetName;
		this.city = city;
		this.cState = cstate;
		this.zipCode = zipcode;
		this.phone = phone;
		this.email = email;
		this.accountId = accid;
		this.pin = pin;
		this.userName = un;
		this.passWd = pw;
	}
	public int getCustomerID() {
		return customerId;
	}

	public void setCustomerID(int customerID) {
		this.customerId = customerID;
	}
	public void setAccountID(int accid) {
		this.accountId = accid;
	}
	
	public int getAccountID(){
		return accountId;
	}
	
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	public int getPin(){
		return pin;
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
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return passWd;
	}

	public void setPassword(String password) {
		this.passWd = password;
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

	public void setState(String cstate) {
		this.cState = cstate;
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

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		String details = "New user account created.\nDetails are: First Name: " + this.firstName + " Last Name:" +
							this.lastName + " CustomerID: " + this.customerId + " Account #" + this.accountId + " Username: " + this.userName + " Password: " + this.passWd;
		return details;
	}
}
