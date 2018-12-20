package project.users;

import java.sql.SQLException;

import project.account.Account;
import project.daoimpl.UserDAOImpl;

public class Admin extends Employee{
	private static final int adminCode = 98765;
	private UserDAOImpl udi = new UserDAOImpl();
	public Admin(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		try {
			udi.createAdmin(username, password, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Admin(String username, String password, String name, int cheating) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		//this.cheating = cheating... I needed another constructor...
	}
	public void withdraw(double amount, Account account) {
		
	}
	public void deposit(double amount, Account account) {
		
	}
	public void transfer(Account account1, Account account2, double ammount) {
		
	}
	public void cancelAccount(Account account) {
		
	}
	public static boolean checkAdminCode(int code) {
		if (code == adminCode) {
			return true;
		} else {
			return false;
		}
	}
}
