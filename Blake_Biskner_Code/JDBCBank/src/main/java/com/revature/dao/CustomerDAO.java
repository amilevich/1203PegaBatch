package com.revature.dao;

import java.sql.SQLException;

import com.revature.bean.Customer;

/**
 * Customer DAO
 * 
 * @author Blake Biskner
 * @version 2.0
 */

public interface CustomerDAO {
	// CREATE
	
	/**
	 * Method to create a new customer
	 * 
	 * @param customer
	 * @throws SQLException
	 * 
	 */
	public void createCustomer(Customer customer) throws SQLException;

	/**
	 * Method to Insert a New Joint Holder As they share an account with an existing
	 * customer a stored procedure which does not increment the ACCT_SEQ will
	 * execute
	 * 
	 * @param customer
	 * @throws SQLException
	 * 
	 */
	public void createJointCustomer(Customer customer) throws SQLException;

	// READ

	/**
	 * Method to Read Data from BankCustomer Oracle Table into Local Customers
	 * HashMap
	 * 
	 * @throws SQLException
	 * 
	 */
	public void getCustomerMap() throws SQLException;

	// UPDATE

	// DELETE
}
