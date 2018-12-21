package com.revature.controllers;

import java.sql.SQLException;
import java.sql.Timestamp;

import com.revature.DAO.AccountDaoImpl;
import com.revature.DAO.TransactionDaoImpl;
import com.revature.DAO.UserDaoImpl;
import com.revature.enums.Account_State;
import com.revature.enums.Transaction_Type;
import com.revature.models.Account;
import com.revature.models.Transaction;

public class TransactionManager {

	public double depositForm(Double amount, Account account, int user) throws SQLException {
		if (amount > 10.00 && amount < 10000) {
			Transaction trans = new Transaction();
			account.setBalance(account.getBalance() + amount);
			System.out.println("Deposit done succesfully!\n");
			
			trans.setAccount(account.getId());
			trans.setAmount(amount);
			trans.setTimestamp(new Timestamp(System.currentTimeMillis()));
			trans.setType(Transaction_Type.DEPOSIT);
			trans.setUser(user);
			new TransactionDaoImpl().createTransaction(new Transaction(trans.getAccount(), trans.getUser(), trans.getAmount(), 
					trans.getType(), trans.getTimestamp()));
			
			
			new AccountDaoImpl().updateAccount(account);
			
			return amount;
		} else if (amount <= 0) {
			System.out.println("The deposit must be greater than 0.00!");
			return 0.00;
		} else {
			System.out.println("The deposit amount ranges from $10.00 to $10,000.00");
			return 0.00;
		}

	}

	public double withdrawalForm(Double amount, Account account, int user) {
		if (amount > 0.00)
			if (account.getBalance() >= amount) {
				Transaction trans = new Transaction();
				account.setBalance(account.getBalance() - amount);
				System.out.println("Withdrawl done succesfully!\n");

				trans.setAccount(account.getId());
				trans.setAmount(amount * (-1));
				trans.setTimestamp(new Timestamp(System.currentTimeMillis()));
				trans.setType(Transaction_Type.WITHDRAWL);
				trans.setUser(user);
				new TransactionDaoImpl().createTransaction(new Transaction(trans.getAccount(), trans.getUser(), trans.getAmount(), 
						trans.getType(), trans.getTimestamp()));
				new AccountDaoImpl().updateAccount(account);
				
				return amount;
			} else {
				System.out.println("Insuficient Funds!");
				return 0.0;
			}
		else {
			System.out.println("Please input an amount greater than 0.00!");
			return 0.00;
		}
	}

	public double transferForm(Double amount, Account account1, Account account2, int user1, int user2) {

		if (account2.getState() == Account_State.APPROVED) {
			if (amount > 0) {
				if (account1.getBalance() > amount) {
					Transaction trans1 = new Transaction();
					Transaction trans2 = new Transaction();

					account1.setBalance(account1.getBalance() - amount);
					account2.setBalance(account2.getBalance() + amount);
					System.out.println("Tranfer to " + account2.getId() + " done successfully\n");

					trans1.setAccount(account1.getId());
					trans1.setAmount(amount * -1);
					trans1.setTimestamp(new Timestamp(System.currentTimeMillis()));
					trans1.setType(Transaction_Type.TRANSFER);
					trans1.setUser(user1);

					trans2.setAccount(account2.getId());
					trans2.setAmount(amount);
					trans2.setTimestamp(new Timestamp(System.currentTimeMillis()));
					trans2.setType(Transaction_Type.TRANSFER);
					trans2.setUser(user2);
					
					new TransactionDaoImpl().createTransaction(new Transaction(trans1.getAccount(), trans1.getUser(), trans1.getAmount(), 
							trans1.getType(), trans1.getTimestamp()));
					new AccountDaoImpl().updateAccount(account1);
					
					new TransactionDaoImpl().createTransaction(new Transaction(trans2.getAccount(), trans2.getUser(), trans2.getAmount(), 
							trans2.getType(), trans2.getTimestamp()));
					new AccountDaoImpl().updateAccount(account2);
					
					return amount;

				} else {
					return 0.00;
				}
			} else {
				System.out.println("Please input an amount greater than 0.00!");
				return 0.00;
			}
		} else {
			System.out.println("Invalid transfer, account not accesible!");
			return 0.00;
		}
	}
	
	public void closeAccount(Account account, int user) {
		if(account.getBalance()>0.00) {
			System.out.println("The account still has funds in it, to close, first withdraw or transfer it.");
		}
		else if(account.getState() != Account_State.APPROVED && new UserDaoImpl().getUserById(user).getRole() != "CUSTOMER") {
			
				if(new AccountDaoImpl().getAccountById(account.getId()).getJoint()==0) {
					new TransactionDaoImpl().deleteTransactionByUserId(user);
					new AccountDaoImpl().deleteAccount(account.getId(), user);
					System.out.println("Account deleted successfully");
				}
				else
					System.out.println("Account can't be closed your not the only one");
				
				
		}
			
		
	}

}
