package com.assignment.dao;

import java.sql.SQLException;
import java.util.List;

import com.assignment.persons.Customer;
import com.assignment.utilities.Account;


public interface AccountsDAO {
	//CRUD OPS
		public abstract void createAccount() throws SQLException;//does need much has the id will be made by sequence, doesn't require arguments
		//public abstract void readAccount(int id) throws SQLException;//get info from db-->for now...
		public abstract void updateBalance(int update_type, int accountID, double amount) throws SQLException;//I think this will either withdraw or delete
		public abstract void updateApproval(int accountID, int state) throws SQLException;
		public abstract void updateCancel(int accountID, int state) throws SQLException;
		public abstract void transferFunds(int idOne, int idTwo, double amount) throws SQLException;//to make a transfer
		public abstract void deleteAccount(int id) throws SQLException;
		public abstract void addAccountHolder(int userID, int accountID) throws SQLException;
		public abstract List<Integer> viewPendingAccounts() throws SQLException;
		public abstract List<Account> getAccountList() throws SQLException;//list of accounts?
		public abstract List<Customer> getAccountHolders() throws SQLException;

}
