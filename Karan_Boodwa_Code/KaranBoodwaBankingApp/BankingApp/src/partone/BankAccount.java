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
	private double balance = 0.0;
	// ArrayList of usernames referencing the owners of the account
	private ArrayList<String> holders = new ArrayList<String>();

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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

	@Override
	public String toString() {
		return "BankAccount [balance=" + balance + ", holders=" + holders + "]";
	}
	

}
