package com.revature.daos;

import java.util.List;

import com.revature.pojos.Account;

public interface AccountDao {
	// CRUD:
	
	// Create
	boolean createAccount(int accNum);
	
	// Update
	public boolean changeBalance(int accId, double amount);
	
	// Read
	public Account getAccountById(int accId);
	
	public List<Integer> getAccountsByUsername(String username);

	// Delete
	public boolean deleteAccount(int accId);
	
}
