
public class BankUser {
	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private int accountType;
	//because accountType starts at 1, have a dummy in the array to make it simpler to reference.
	private static final String[] ACCOUNTDESCRIPTIONS = {"Dummy","Customer","Employee","Admin","Superuser"};
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public BankUser(String firstname, String lastname, String username, String password, int accountType) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.accountType = accountType;
	}
	public BankUser(String firstname, String lastname, String username, String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.accountType = 1;
	}
	public BankUser() {
		
	}
	public BankUser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String toString() {
		String usr = "";
		usr += "ID: " + id;
		usr += ", Username: " + username;
		usr += "\n	Name: " + this.getFullName();
		usr += ", Account Type: " + ACCOUNTDESCRIPTIONS[accountType];
		return usr;
	}
	public String getFullName() {
		String fname = firstname.substring(0, 1).toUpperCase() + firstname.substring(1);
		String lname = lastname.substring(0,1).toUpperCase()+ lastname.substring(1);
		return fname + " " + lname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
