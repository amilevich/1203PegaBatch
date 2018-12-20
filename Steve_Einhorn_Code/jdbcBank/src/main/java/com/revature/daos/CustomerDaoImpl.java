package com.revature.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.Customer;
import com.revature.models.OpenApplication;

public class CustomerDaoImpl implements CustomerDao {
	
	private static Connection getConnection() {
		
		// much more secure as environment variables
		String user = "bank_db";  //System.getenv("jdbc_user")
		String password = "p4ssw0rd";
		String url = "jdbc:oracle:thin:@octocatdb.cowlaewb2yhg.us-east-2.rds.amazonaws.com:1521:ORCL";	

		try {
			System.out.println("a");
			return DriverManager.getConnection(url, user,  password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean findCustomer(Customer customer) {
		
		// Connection is a major interface of JDBC
		// Connection is used to access a variety of other interfaces
		Connection conn = getConnection();
		
		boolean customerFound = false;
		
		String query = "SELECT id, username, password, phone_nbr FROM customers " +
				   		"WHERE username = ? AND password = ?";
					   
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customerFound = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customerFound;
	}
	
	public void saveCustomer(Customer customer) {
		
		// Connection is a major interface of JDBC
		// Connection is used to access a variety of other interfaces
		Connection conn = getConnection();
		
		String query = "INSERT INTO CUSTOMERS (ID, USERNAME, PASSWORD) " +
				   		"VALUES (SEQ_CUSTOMERS.NEXTVAL, ?, ?)";
					   
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
