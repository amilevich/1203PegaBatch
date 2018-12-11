package project0.bank;
enum UserType{
	Customer, Admin, Employee
}
public abstract class  Users {
	
	protected  String firstName;
	protected  String lastName;
	protected  String Username;
	protected  String password;
	protected UserType Type;
	protected boolean approved=false;
	protected Accounts myAccount;
	

	@Override
	public String toString() {
		return "Users [firstName=" + firstName + ", lastName=" + lastName + ", Username=" + Username + ", password="
				+ password + ", Type=" + Type + "]";
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	 String getFirstName() {
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
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getType() {
		return Type;
	}
	public void setType(UserType type) {
		Type = type;
	}

}
