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
	public void createCustomer(Customer customer) throws SQLException;
	// READ
	public void getCustomerMap() throws SQLException;

	// UPDATE

	// DELETE
}
