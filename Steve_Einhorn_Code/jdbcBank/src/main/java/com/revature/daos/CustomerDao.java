package com.revature.daos;

import com.revature.models.Customer;

/**
 * Data Access Object
 * ---
 * That this is the interface through which
 * we intend to interact with our persistency layer
 * for this kind of project.d
 * 
 * @author Steven Einhorn
 */

public interface CustomerDao {

	public boolean findCustomer(Customer customer);
	public void saveCustomer(Customer customer);
	
}
