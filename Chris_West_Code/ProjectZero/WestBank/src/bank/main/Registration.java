package bank.main;

public class Registration {
	String firstName;
	String middleInitial;
	String lastName;
	String address;
	String city, state;
	int phoneNum;
	int zip;
	String accType;
	Boolean newAccount;
	String id;
	String passWord;

	public Registration(String firstName, String middleInitial, String lastName, String address, String city,
			String state, int phoneNum, int zip, String accType, Boolean newAccount, String id, String passWord) {
		super();
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phoneNum = phoneNum;
		this.zip = zip;
		this.accType = accType;
		this.newAccount = newAccount;
		this.id = id;
		this.passWord = passWord;
	}

	public Registration() {

	}

	public String getfName() {
		return firstName;
	}

	public void setfName(String fName) {
		this.firstName = fName;
	}

	public String getmIntial() {
		return middleInitial;
	}

	public void setmIntial(String mIntial) {
		this.middleInitial = mIntial;
	}

	public String getlName() {
		return lastName;
	}

	public void setlName(String lName) {
		this.lastName = lName;
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

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public Boolean getNewAccount() {
		return newAccount;
	}

	public void setNewAccount(Boolean newAccount) {
		this.newAccount = newAccount;
	}

}
