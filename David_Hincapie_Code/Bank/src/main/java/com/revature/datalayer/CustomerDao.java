package com.revature.datalayer;

import java.sql.SQLException;
import java.util.List;

import com.revature.businesslayer.Customer;
import com.revature.businesslayer.User;

public interface CustomerDao {
	// CRUD OPERTAIONS
	public abstract boolean insertCustomerUser(Customer customer) throws SQLException;

	public abstract boolean deleteCustomerUser(Customer customer) throws SQLException;

	public abstract boolean updateUser(Customer customer) throws SQLException;

	public abstract Customer findCustomerById(int id) throws SQLException;

	public abstract Customer findCustomerByUserId(String userId) throws SQLException;

	public abstract List<Customer> getCustomerList() throws SQLException;

}
