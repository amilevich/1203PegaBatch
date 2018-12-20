package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bean.Customer;
import com.revature.dao.CustomerDAO;
import com.revature.driver.Driver;
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

		call.setString(1, customer.getUserName());
		call.setString(2, customer.getPassword());
		call.setString(3, customer.getFirstName());
		call.setString(4, customer.getLastName());
		call.setInt(5, customer.getAge());
		call.setString(6, customer.getSocialSecurity());
		call.setInt(7, customer.getAcctType());
		call.setInt(8, customer.getAcctStatus());

		call.execute();

		conn.close();
	}

	@Override
	public void createJointCustomer(Customer customer) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call INSERT_JtCustomer(?,?,?,?,?,?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);

		call.setString(1, customer.getUserName());
		call.setString(2, customer.getPassword());
		call.setString(3, customer.getFirstName());
		call.setString(4, customer.getLastName());
		call.setInt(5, customer.getAge());
		call.setString(6, customer.getSocialSecurity());
		call.setInt(7, customer.getAcctType());
		call.setInt(8, customer.getAcctStatus());
		call.setInt(9, customer.getAcctNum());

		call.execute();

		call.close();
	}

	// READ
	@Override
	public void getCustomerMap() throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM BankCustomer";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Customer customer = null;
		while (rs.next()) {
			customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
					rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
			Driver.customers.put(rs.getString(1), customer);
		}
		conn.close();
	}

	// UPDATE
	@Override
	public void updateCustomer(Customer customer) throws SQLException{
		Connection conn=cf.getConnection();
		String sql="{ call Update_Customer_Status(?,?)";
		CallableStatement call=conn.prepareCall(sql);
		call.setString(1, customer.getUserName());
		call.setInt(2,customer.getAcctStatus());
		call.execute();
		
		conn.close();
	}

	// DELETE

}
