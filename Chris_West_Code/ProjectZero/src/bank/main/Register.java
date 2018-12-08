package bank.main;

public class Register {
	String fName;
	String mIntial;
	String lName;
	String address;
	String city, state, zip;
	String phoneNum;
	String accType;
	Boolean newAccount;

	public Register(String fName, String mIntial, String lName, String address, String city, String state, String zip,
			String phoneNum, String accType, Boolean newAccount) {
		super();
		this.fName = fName;
		this.mIntial = mIntial;
		this.lName = lName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNum = phoneNum;
		this.accType = accType;
		this.newAccount = newAccount;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmIntial() {
		return mIntial;
	}

	public void setmIntial(String mIntial) {
		this.mIntial = mIntial;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
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
