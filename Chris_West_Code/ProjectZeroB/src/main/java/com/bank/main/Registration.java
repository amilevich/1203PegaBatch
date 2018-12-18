package com.bank.main;

public class Registration {

	String firstName;
	String middleInitial;
	String lastName;
	String address;
	String city, state;
	int zip;
	Long phoneNum;
	String email;
	String passWord;
	String userType;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public Long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "\nRegistration:\nFirst: " + firstName + "\nMiddle Initial: " + middleInitial + "\nLast: " + lastName
				+ "\nAddress: " + address + "\nCity: " + city + "\nState: " + state + "\nPhone: " + phoneNum + "\nZip: "
				+ zip + "\nNew Account: " + newAccount + "\nID: " + id + "\nUser: " + userType + "\nJoint Account: "
				+ jAcc + "\nRegular Account: " + rAcc;
	}
}
