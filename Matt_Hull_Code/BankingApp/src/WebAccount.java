
public class WebAccount {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String accountType = "Customer"; //default type is customer, can be changed later
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public void setUsername(String username) {
		this.username = username;
	}
	public WebAccount() {
		
	}
	public WebAccount(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public WebAccount(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public boolean checkPassword(String pw) {
		return password.equals(pw);
	}
	public void setName(String fName, String lName) {//Allowing users to change their names in addition to initially setting them possibly
		firstName = fName;
		lastName = lName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String type) {
		accountType = type;
	}
}
