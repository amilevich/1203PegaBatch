import java.text.DecimalFormat;

public class BankAccount {
	private int accountNumber;
	private double balance =-1;
	
	public BankAccount() {
		
	}
	public BankAccount(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BankAccount(boolean newAccount) {
		if (newAccount) {
			balance=0;
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
		acc += "\n	Balance:  " + df.format(balance);
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
		if (balance == -1) {
			return false;
		}
		balance += amount;
		return true;
	}
	public boolean withdraw(double amount) {
		if (balance == -1) {
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
