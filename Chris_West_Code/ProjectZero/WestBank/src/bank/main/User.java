package bank.main;

public class User {
	String id;
	String passWord;
	String user;
	//enum userType {CUSTOMER, EMPLOYEE, ADMIN};
	
	
	
	public User(String id, String passWord, String user) {
		super();
		this.id = id;
		this.passWord = passWord;
		this.user = user;
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
