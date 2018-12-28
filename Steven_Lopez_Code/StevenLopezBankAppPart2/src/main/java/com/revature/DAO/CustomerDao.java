package com.revature.DAO;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDao {
	public Customer getCustomerById(int id);
	public List<Customer> getAllCustomers();
	
	public int createCustomer(Customer customer);
	
	public boolean updateCustomer(Customer customer);
	
	public int deleteCustomer(int id);

}
