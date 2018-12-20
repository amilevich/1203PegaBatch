package project.users;

import java.sql.SQLException;

import project.account.Account;
import project.daoimpl.UserDAOImpl;

public class Employee extends User{
	private static final int employeeCode = 12345;
	private UserDAOImpl udi = new UserDAOImpl();

	public Employee() {
	
	}
	public Employee(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		try {
			udi.createEmployee(username, password, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Employee(String username, String password, String name, int cheating) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		//this.cheating = cheating... I needed another constructor...
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
