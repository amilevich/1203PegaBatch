package project0.bank;

import java.util.ArrayList;

public class Accounts {

	double balance;
	String name;
	ArrayList<Users> authorizedUsers= new ArrayList<Users>();
	Accounts(double initialAmount, String accountName, Users u){
		balance=initialAmount;
		name=accountName;
		authorizedUsers.add(u);
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

		public boolean withdraw(double amount) {
		String amountAsString = Double.toString(amount);
		int decPoint = amountAsString.indexOf(".");

		if ((amountAsString.length() - decPoint - 1 <= 2) && (this.getBalance() > amount) && (amount > 0)) {
			this.setBalance(this.getBalance() - amount);
			System.out
					.println("you withdrew $" + amount + " from your account. Your new balance is" + this.getBalance());
			return true;
		} else {
			System.out.println("invalid input. please try again");
			return false;
		}
	}

	public boolean deposit(double amount) {
		String amountAsString = Double.toString(amount);
		int decPoint = amountAsString.indexOf(".");

		if ((amountAsString.length() - decPoint - 1 <= 2) && (amount > 0)) {
			this.setBalance(this.getBalance() + amount);
			System.out.println(
					"you deposited $" + amount + " into your account. Your new balance is " + this.getBalance());
			return true;
		} else {
			System.out.println("invalid input. please try again");
			return false;
		}

	}

	public void transferFunds(double amount, Accounts a) {
		boolean withdrawalHappened = this.withdraw(amount);
		if (withdrawalHappened)
			a.deposit(amount);

	}


	public void addUser(Users u) {

	}

}
