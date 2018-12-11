package project0.bank;

import java.util.ArrayList;

public class Customer extends Users{
	
	Accounts myAccount;
	 
	Customer(String firstName,String lastName, String username, String password){
	//	Bank bank= Bank.createBank();
		
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUsername(username);
		this.setPassword(password);
		this.setType(UserType.Customer);
		//bank.addToCustomers(this);
		//bank.addToUsers(this);
	}
	
	public void withdraw(Accounts a, double amount) {
		a.withdraw(amount);
			}
	public void deposit(Accounts a, double amount) {
		a.withdraw(amount);
	}
	public void transfer(Accounts a, Accounts b, double amount) {
		a.transferFunds(amount, b);
	}

}
