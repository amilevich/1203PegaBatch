package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.bean.Employee;
import com.revature.dao.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static String url = "jdbc:oracle:thin:@octocatdbp.c2mjrycz2a8r.us-east-2.rds.amazonaws.com:1521:ORCL";
	private static String username = "TRMS_DB";
	private static String password = "p4ssw0rd";
	
	@Override
	public void insertEmployInfo(Employee employee) throws SQLException {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("{ call INSERTINFO(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, employee.getEmployeeId());
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setString(4, employee.getEmail());
			ps.setInt(5, employee.getDepartmentId());
			ps.setInt(6, employee.getManagerId());
			ps.setInt(7, employee.getEventId());
			ps.setString(8, employee.getEventLocation());
			ps.setDouble(9, employee.getEventCost());
			ps.setDouble(10, employee.getAvailableReimbursement());
			ps.setString(11, employee.getAppStatus());
			ps.setDate(12, employee.getEventDate());
			ps.setInt(13, employee.getUserId());
			ps.execute();

		}
	}

	@Override
	public void selectEmployInfo(Employee employee) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
