package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.bean.Customer;
import com.revature.dao.CustomerDAO;
import com.revature.util.ConnFactory;

/**
 * Customer DAO Implementation
 * 
 * @author Blake Biskner
 * @version 2.0
 * 
 */

public class CustomerDAOImpl implements CustomerDAO {
	public static ConnFactory cf = ConnFactory.getInstance();

	// CREATE
	@Override
	public void createCustomer(Customer customer) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call Insert_Customer(?,?,?,?,?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		
		call.setString(1,customer.getUserName());
		call.setString(2, customer.getPassword());
		call.setString(3, customer.getFirstName());
		call.setString(4, customer.getLastName());
		call.setInt(5,customer.getAge());
		call.setString(6, customer.getSocialSecurity());
		call.setInt(7,customer.getAcctType());
		call.setInt(8, customer.getAcctStatus());

		call.execute();

		conn.close();
	}

	// READ

	// UPDATE

	// DELETE

}
