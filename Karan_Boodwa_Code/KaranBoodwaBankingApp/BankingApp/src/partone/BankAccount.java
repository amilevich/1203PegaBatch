package partone;

import java.util.ArrayList;

/**
 * BankAccount holds account information as well as owner information
 * 
 * @author karan
 *
 */
public class BankAccount {

	// Amount of money in the bank account
	// Note: there is no constraint on the amount to be 2 decimal places e.g.
	// At the moment that makes this banking application general in nature (think cryptocurrency w/ small subdivisions ;) )
	// This functionality can be implemented in the future 
	// by leveraging 'Decimal Formats' in Java
	private double balance = 0.0;
	
	// ArrayList of usernames referencing the owners of the account
	// This is also used to differentiate between accounts with one owner and ones with two (joint accounts)
	// This is also extensible to allow for >2 account holders 
	private ArrayList<String> holders = new ArrayList<String>();
	
	private int accountNumber = 0;
	
	
	// Getters and Setters:
	
	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		this.balance = balance + amount;
	}
	
	public void withdraw(double amount) {
		this.balance = balance - amount;
	}

	public ArrayList<String> getHolders() {
		return holders;
	}

	public void setHolders(ArrayList<String> holders) {
		this.holders = holders;
	}
	
	public void addHolder(String holder) {
		this.holders.add(holder);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "BankAccount [Account Number= " + accountNumber + ", balance=" + balance + ", holders=" + holders + "]";
	}
	

}
