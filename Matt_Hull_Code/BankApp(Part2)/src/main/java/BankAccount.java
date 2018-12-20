import java.text.DecimalFormat;
/*
 * This is the object that maps to a bank account
 * the balance starts at -6 because that is the transaction fee and all accounts must start at -6
 *basic pojo with constructor, getters, setters
 *
 *Has deposit and withdraw methods that perform checks to make sure it is possible to do carry out withdrawal, etc
 */
public class BankAccount {
	private int accountNumber;
	private double balance =-6;
	
	public BankAccount() {
		
	}
	public BankAccount(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BankAccount(boolean newAccount) {
		if (newAccount) {
			balance=-5;
		}
	}
	public BankAccount(int accountNumber, double balance) {
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	public String toString() {
		String acc = "";
		DecimalFormat df = new DecimalFormat("#.##");
		acc += "Account Number: " + accountNumber;
		acc += "\n	Balance:  $" + df.format(balance);
		return acc;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public boolean deposit(double amount) {
		if (balance == -6) {
			return false;
		}
		balance += amount;
		return true;
	}
	public boolean withdraw(double amount) {
		if (balance <= 0) {
			return false;
		}
		if(amount > balance) {
			return false;
		}
		balance -= amount;
		return true;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	

}
