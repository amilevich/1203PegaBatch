package com.revature.dao;

import java.sql.SQLException;

import com.revature.beans.Accounts;
import com.revature.beans.Login;

public interface AccountsDAO {
	 //CRUD Ops
	
	//CREATE
	public abstract void createAccountProc(Accounts accs) throws SQLException;
		
	//READ
	public abstract void getSpecificAccount(Login logIn, Accounts accs) throws SQLException;	
	public abstract void getAccountStatus(Accounts accs, int usrID) throws SQLException;
	public abstract void viewAccounts(Accounts accs, Login logIn) throws SQLException;
	public abstract void editAccount(Accounts accs) throws Exception;
	
	//UPDATE
	public abstract void updateAccount(Accounts accs) throws SQLException;	
		
	//DELETE
	public abstract void deleteAccount(int account_id, int usr_id) throws SQLException;
}