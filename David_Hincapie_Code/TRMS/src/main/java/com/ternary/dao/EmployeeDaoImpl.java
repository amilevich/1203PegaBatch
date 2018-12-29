package com.ternary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ternary.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public Employee selectByEmail(String email) {
		Employee employee = null;
		try {
			Connection connection = cf.getConnection();
			String sql = "SELECT * FROM employee WHERE email=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("INSIDE selectByEmail");
				employee = new Employee(rs.getString("email"), rs.getString("password"));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

}
