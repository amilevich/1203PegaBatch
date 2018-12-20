package com.revature.classes;

public class Account {
	
	private int balance = 0;
	private int id;
	
	Account(int id){
		this.id = id;
	}
	
	public Account(int balance, int id){
		this.id = id;
		this.balance = balance; 
	}
	
	
	public int getBalance() {
		return this.balance;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void deposit(int moreMoney) {
		if(moreMoney >= 0) {
			this.balance += moreMoney;
		}
		this.balance += 0;
	}
	
	public int withdraw(int byAmount) {
		if (byAmount <= this.balance) {
			this.balance -= byAmount;
			return byAmount;
		}
		return this.balance;
	}
	
	public void transfer(int byAmount, Account other) {
		if(byAmount <= this.balance) {
			this.withdraw(byAmount);
			other.deposit(byAmount);
		}
		else {
			other.deposit(this.balance);
			this.balance = 0;
		}
		
	}

	@Override
	public String toString() {
		return "Account: " + this.getID() + "-> with balance " + this.getBalance();
	}
	

}
