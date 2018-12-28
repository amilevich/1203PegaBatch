package com.revature.DAO;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {

	public Account getAccountById(int id);
	public List<Account> getAllAccounts();
	public List<Account> getAccountsByUserId(int id);
	
	public int createAccount(Account account, int user);
	
	public boolean updateAccount(Account account);
	
	public int deleteAccount(int id, int user);
}
