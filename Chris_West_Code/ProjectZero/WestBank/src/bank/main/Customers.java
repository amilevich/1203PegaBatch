package bank.main;

public class Customers extends User {
	String firstName;
	String middleInitial;
	String lastName;
	String address;
	String city, state;
	Long phoneNum;
	int zip;
	String accType;
	Boolean newAccount;
	String id;
	String passWord;
	double balance;

	public Customers(String id, String passWord, String user, String firstName, String middleInitial, String lastName,
			String address, String city, String state, Long phoneNum, int zip, String accType, Boolean newAccount
			, double balance) {
		super(id, passWord, user);
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
		this.balance = balance;
	}

//	public Customers(String id, String passWord, String user) {
//		super(id, passWord, user);
//		// TODO Auto-generated constructor stub
//	}

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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
