package com.revature.dao;

import java.sql.SQLException;

/**
 * Employee DAO
 * 
 * @author Blake Biskner
 * @version 2.0
 */

public interface EmployeeDAO {
	// CREATE
	// READ
	
	/**
	 * Method to Read Data from BankEmployee Oracle Table into HashMap
	 * 
	 * @throws SQLException
	 * 
	 */
	public void getEmployeeMap() throws SQLException;
}
