package com.bank.dao;
//import com.bank.util.TransactionMethods;
import java.sql.SQLException;
import java.util.List;

import com.bank.beans.Account;

public interface AccountDAO{ //extends TransactionMethods{
	
	abstract void deposit(int accountid, String timestmp, String acctype, int activation, int deposit) throws SQLException;
	
	//abstract void withdraw(int accountid, String timestmp, String acctype, int activation, int withdraw) throws SQLException;
	
	abstract List<Account> getFullAccountList() throws SQLException;
	abstract List<Account> getAllAccountIds() throws SQLException;	
	abstract List<Account> getAllAccountCreationDates() throws SQLException;
	abstract List<Account> getActiveAccounts() throws SQLException;
	abstract List<Account> getAllAccountBalances() throws SQLException;
	abstract List<Account> getAllAccountTypes() throws SQLException;
	abstract int getLastBalance(int accID) throws SQLException;
	
	
	
}
