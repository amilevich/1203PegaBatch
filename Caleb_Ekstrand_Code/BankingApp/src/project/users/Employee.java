package project.users;

import project.account.Account;

public class Employee extends User{
	private static final int employeeCode = 12345; 
	public Employee(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
	}
	public void viewAllAccounts() {
		
	}
	public void respondToRequest(Account account) {
		
	}
	public static boolean checkEmployeeCode(int code) {
		if (code == employeeCode) {
			return true;
		} else {
			return false;
		}
	}
}
