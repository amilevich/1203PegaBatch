package project.users;

import java.util.ArrayList;

import project.account.Account;

public class Customer extends User{
	private String phoneNumber;
	private String address;
	private ArrayList<Account> accountNumbers = new ArrayList<>();
	
	
	public Customer(String username, String password, String name, String phoneNumber, String address) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	public void withdraw(double amount, int number) {
		for (Account i : accountNumbers) {
			if (i.getAccountNumber() == number) {
				i.withdraw(amount);
			}
		}
	
	}
	public void deposit(double amount, int accNum) {
		for (Account i : accountNumbers) {
			if (i.getAccountNumber() == accNum) {
				i.deposit(amount);
			}
		}
	}
	public void transfer(Account account1, Account account2, double ammount) {
		
	}
	public double getBalance(int account) {
		for(Account i : accountNumbers) {
			if (i.getAccountNumber() == account) {
				return i.getBalance();
			}
		}
		return 0;
	}
	public void createRequest() {
		
	}
	public void addAccount(Account account) {
		this.accountNumbers.add(account);
	}
	public ArrayList<Account> getAccounts() {
		return accountNumbers;
	}
	public boolean hasAccount(int num) {
		for(Account i : accountNumbers) {
			if (i.getAccountNumber() == num) {
				return true;
			}
		}
		return false;
	}
	public void removeAccount(int account) {
		for (Account i : accountNumbers) {
			if (i.getAccountNumber() == account) {
				accountNumbers.remove(i);
			}
		}
	}
	public Account getAccount(int num) {
		for(Account i : accountNumbers) {
			if (i.getAccountNumber() == num) {
				return i;
			}
		}
		return null;
	}
	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", name=" + name + ", phoneNumber=" + phoneNumber + ", address=" + address + ", accountNumbers=" + accountNumbers + "]";
	}
	
}
