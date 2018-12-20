package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bean.Employee;
import com.revature.dao.EmployeeDAO;
import com.revature.driver.Driver;
import com.revature.util.ConnFactory;

/**
 * Employee DAO Implementation
 * 
 * @author Blake Biskner
 * @version 2.0
 */

public class EmployeeDAOImpl implements EmployeeDAO {
	public static ConnFactory cf = ConnFactory.getInstance();

	// CREATE
	// READ
	@Override
	public void getEmployeeMap() throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM BankEmployee";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Employee employee = null;
		while (rs.next()) {
			employee = new Employee(rs.getInt(1), rs.getInt(2));
			Driver.employees.put(rs.getInt(1), employee);
		}
		
		conn.close();
	}
}
