package com.assignment.bean;

public class Employee {//how many of these variables do we actually need to perform processes?
	//in terms of accessing and manipulating 
	int id;
	String fName, mName, lName; 
	String sAddress, city, state;
	int zipCode;
	long phoneNumber;
	String location;
	int dsID, dhID;
	Double remainingAward;
	String empType;
	String password;
	
	public Employee(int id, String fName, String mName, String lName, String sAddress, String city, String state,
			int zipCode, long phoneNumber, String building, int dsID, int dhID, Double remainingAward, String empType,
			String password) {
		super();
		this.id = id;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.sAddress = sAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.location = building;
		this.dsID = dsID;
		this.dhID = dhID;
		this.remainingAward = remainingAward;
		this.empType = empType;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getsAddress() {
		return sAddress;
	}

	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
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

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBuilding() {
		return location;
	}

	public void setBuilding(String building) {
		this.location = building;
	}

	public int getDsID() {
		return dsID;
	}

	public void setDsID(int dsID) {
		this.dsID = dsID;
	}

	public int getDhID() {
		return dhID;
	}

	public void setDhID(int dhID) {
		this.dhID = dhID;
	}

	public Double getRemainingAward() {
		return remainingAward;
	}

	public void setRemainingAward(Double remainingAward) {
		this.remainingAward = remainingAward;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", sAddress="
				+ sAddress + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", phoneNumber="
				+ phoneNumber + ", building=" + location + ", dsID=" + dsID + ", dhID=" + dhID + ", remainingAward="
				+ remainingAward + ", empType=" + empType + ", password=" + password + "]";
	}
	
}
