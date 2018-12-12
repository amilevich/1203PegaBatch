package bank.main;

public class Registration {

	String firstName;
	String middleInitial;
	String lastName;
	String address;
	String city, state;
	Long phoneNum;
	int zip;
	Boolean newAccount;
	String id;
	String passWord;
	String userType;
	Boolean jAcc;
	Boolean rAcc;

	public Registration(String firstName, String middleInitial, String lastName, String address, String city,
			String state, int zip, Long phoneNum, Boolean rAcc, Boolean jAcc, Boolean newAccount, String id,
			String passWord, String userType) {
		super();
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNum = phoneNum;
		this.zip = zip;
		this.newAccount = newAccount;
		this.id = id;
		this.passWord = passWord;
		this.userType = userType;
		this.rAcc = rAcc;
		this.jAcc = jAcc;
	}

	public Registration() {
		
	}
	public Boolean getrAcc() {
		return rAcc;
	}


	public void setrAcc(Boolean rAcc) {
		this.rAcc = rAcc;
	}


	public Boolean getjAcc() {
		return jAcc;
	}


	public void setjAcc(Boolean jAcc) {
		this.jAcc = jAcc;
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

	public Long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public Boolean getNewAccount() {
		return newAccount;
	}

	public void setNewAccount(Boolean newAccount) {
		this.newAccount = newAccount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
				+ zip + "\nNew Account: " + newAccount + "\nID: " + id + "\nUser: " + userType + "\nJoint Account: " + jAcc
				+ "\nRegular Account: " + rAcc;
	}
}
