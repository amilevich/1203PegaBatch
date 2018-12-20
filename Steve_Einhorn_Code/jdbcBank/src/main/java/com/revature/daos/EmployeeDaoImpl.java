package com.revature.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.OpenApplication;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private static Connection getConnection() {
		
		// much more secure as environment variables
		String user = "bank_db";  //System.getenv("jdbc_user")
		String password = "p4ssw0rd";
		String url = "jdbc:oracle:thin:@octocatdb.cowlaewb2yhg.us-east-2.rds.amazonaws.com:1521:ORCL";	

		try {
			return DriverManager.getConnection(url, user,  password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean findEmployee(Employee employee) {
		
		// Connection is a major interface of JDBC
		// Connection is used to access a variety of other interfaces
		Connection conn = getConnection();
		
		boolean employeeFound = false;
		
		String query = "SELECT id, username, password FROM employees " +
				   		"WHERE username = ? AND password = ?";
					   
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, employee.getUsername());
			ps.setString(2, employee.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				employeeFound = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employeeFound;
	}
	
	public boolean findBusinessAnalyst(Employee employee) {
		
		// Connection is a major interface of JDBC
		// Connection is used to access a variety of other interfaces
		Connection conn = getConnection();
		
		boolean busAdminFound = false;
		
		String query = "SELECT id, username, password FROM employees " +
				   		"WHERE username = ? AND password = ? AND employee_type = 'BA'";
					   
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, employee.getUsername());
			ps.setString(2, employee.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				busAdminFound = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return busAdminFound;
	}

}
