package com.revature.datalayer;

import java.sql.SQLException;
import java.util.List;

import com.revature.businesslayer.Account;
import com.revature.businesslayer.Customer;
import com.revature.businesslayer.User;

public interface AccountDao {

	// CRUD OPERTAIONS
	public abstract boolean insertAccount(Account account) throws SQLException;

	public abstract boolean deleteAccount(Account account) throws SQLException;

	public abstract boolean updateAccount(Account account) throws SQLException;

	public abstract Account findAccountById(int id) throws SQLException;

	public abstract Account findAccountrByAccountId(String accountId) throws SQLException;

	public abstract List<Account> getAccountList() throws SQLException;

	public abstract Customer findAccountCustomerById(int id) throws SQLException;

	public abstract Customer findAccountCustomerByUserId(String customerId) throws SQLException;

	public abstract List<Customer> getAccountCustomerList() throws SQLException;

}
