package project.users;

import project.account.Account;

public class Admin extends Employee{
	private static final int adminCode = 98765;
	public Admin(String username, String password, String name) {
		super(username, password, name);
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
