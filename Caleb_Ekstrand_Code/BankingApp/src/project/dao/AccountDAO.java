package project.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import project.account.Account;

public interface AccountDAO {
	//CRUD
	//CREATE
	public abstract void createAccount(int num, String type) throws SQLException;
	
	//READ
	public abstract ArrayList<Account> getAccounts() throws SQLException;
	//UPDATE
	public abstract void setBalance(int account, double amount) throws SQLException;
	public abstract void activatedAcc(int account) throws SQLException;
	
	//DELETE
	public abstract void deleteAccount(int account) throws SQLException;
	
}
