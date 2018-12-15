package com.projectz.bankapp;

public class Account implements TransactionMethods {
	private double amount = 0.00; 			//placeholder for the code that holds the balance in a .txt, .csv, or cookie, or log4j
	private int accountID;
		
		public Account(int accID){
			this.accountID = accID;
		}

		public Account(int accID, double amnt) {
			this.accountID = accID;
			this.amount = amnt;
		}

		public void viewAccount() {
			System.out.println("The account balance for Account #" + this.accountID + " is $" + this.amount);
		}
		
		public int getAccountID() {
			return this.accountID;
		}
		
		public String getStringAccountID() {
			String thing = "00" + this.accountID;
			return thing;
		}
		
		public double withdraw(double w){
			this.amount -= w;
			if (this.amount <= 0){ 
				
				System.out.println("You've overdrafted your account. Your balance is: $" + this.amount);
			}
			else {
				
				System.out.println("You've withdrawn $" + this.amount  +"." + "Your balance is: $" + this.amount);
			}

			return this.amount; //this will return the result to the calling method where ever
		}	

		public void deposit(double d){	//this will only update the amount in the bank
			if (this.amount <= 0){
				System.out.println("Deposit completed. Due to previous overdraft activity on your account, your balance is: 								$" + this.amount);
				this.amount += d;
			}
			else {
				System.out.println("You've deposited $" + this.amount  +"." + "Your balance is: $" + this.amount);
				this.amount += d;
			}
		}	
				
		public double transfer(int accountid, double transferamount){ //a regular expression for input here would be nice
			this.amount -= transferamount;
			Account na = new Account(accountid);
			na.deposit(transferamount);
			return this.amount;
		}
		
		public void cancel() {
			System.out.println("Your account will be processed for cancellation. Please complete our brief 10 question survey so we can better serve our customers.");
		}
}
