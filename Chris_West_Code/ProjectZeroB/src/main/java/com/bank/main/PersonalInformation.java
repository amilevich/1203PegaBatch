package com.bank.main;

public class PersonalInformation {
	int userID;
	String firstName;
	String middleInitial;
	String lastName;
	String address;
	String city, state;
	String id;
	int zip;
	Long phoneNum;

	public PersonalInformation(int userID, String firstName, String middleInitial, String lastName, String address, String city,
			String state, String id, int zip, Long phoneNum) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.id = id;
		this.zip = zip;
		this.phoneNum = phoneNum;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "\n\nPersonal Information \nFirst: " + firstName + "\nMiddle: " + middleInitial + "\nLast: " + lastName
				+ "\nAddress: " + address + "\nCity:" + city + "\nState: " + state + "\nZip: " + zip
				+ "\nPhone number: " + phoneNum + "\nID: ";
	}

}
