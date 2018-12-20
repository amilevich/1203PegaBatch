package com.revature.dao;

import java.sql.SQLException;

import com.revature.bean.Customer;

public interface AdminDAO {
	// CREATE
	// READ
	// UPDATE
	// DELETE
	
	/**
	 * Method to Delete Account
	 * 
	 * @param customer
	 * @throws SQLException
	 * 
	 */

	public void deleteAccount(Customer customer) throws SQLException;
}
