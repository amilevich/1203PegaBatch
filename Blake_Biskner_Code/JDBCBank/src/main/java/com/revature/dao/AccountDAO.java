package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.bean.Account;

/**
 * Account DAO
 * 
 * @author Blake Biskner
 * @version 2.0
 * 
 */

public interface AccountDAO {
	// CREATE

	// READ

	/**
	 * Method to Pull RBDMS Data into Local HashMap
	 * 
	 * @throws SQLException
	 * 
	 */
	public void getAccountMap() throws SQLException;

	/**
	 * Method to Obtain Account Holders
	 * 
	 * @param acctNum
	 * @return list of holders
	 * 
	 */

	public List<String> getAccountHolders(int acctNum) throws SQLException;
	// UPDATE

	/**
	 * Method to Update Account in Database
	 * 
	 * @param account
	 * @throws SQLException
	 * 
	 */
	public void updateAccount(Account account) throws SQLException;
	// DELETE

}
