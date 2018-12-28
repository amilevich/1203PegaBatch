package com.revature.DAO;

import java.util.List;

import com.revature.models.Transaction;

public interface TransactionDao {

	public int createTransaction(Transaction transaction);
	
	public Transaction getTransactionById(int id);
	public List<Transaction> getAllTransactions();
	public List<Transaction> getAllAccountTransactions(int id);
	
	public boolean updateTransaction(Transaction transaction);
	
	public int deleteTransactionByAccountId(int id);
	public int deleteTransactionByUserId(int id);
	public int deleteTransaction(int id);
}
