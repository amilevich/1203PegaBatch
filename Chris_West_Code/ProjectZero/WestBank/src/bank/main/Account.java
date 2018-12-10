package bank.main;

public class Account {
//	protected double withdraw;
//	protected double deposit;
//	protected double transferFunds;
	
	public double withdrawMoney(double amount) {
		double bal = Bank.customer1.getBalance();
		double withdraw = bal - amount;
		return withdraw;
	}
	public double depositMoney(double amount) {
		double bal = Bank.customer1.getBalance();
		double deposit = bal + amount;
		return deposit;
		
	}
	public double transferFunds(double amount) {
		double bal = Bank.customer1.getBalance();
		double transfer = bal - amount;
		return transfer;
	}
	
	
	
}
