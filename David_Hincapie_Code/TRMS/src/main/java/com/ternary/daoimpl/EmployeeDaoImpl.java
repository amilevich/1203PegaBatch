package com.ternary.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ternary.dao.EmployeeDao;
import com.ternary.model.Employee;
import com.ternary.util.ConnFactory;

public class EmployeeDaoImpl implements EmployeeDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public Employee selectByEmployeeId(int id) {
		Employee employee = null;
		try {
			Connection connection = cf.getConnection();
			String sql = "SELECT * FROM empInfo WHERE empid = ?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("INSIDE selectByEmployeeId");
				employee = new Employee();

				employee.setEmployeeId(rs.getInt("empid"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setJobTitle(rs.getString("jobtitle"));
				employee.setDepartmentName(rs.getString("dptname"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
				employee.setReportTo(rs.getInt("reportto"));
				employee.setPhoneNumber(rs.getString("phonenumber"));
				employee.setAvailbleFunds(rs.getDouble("refund"));
				System.out.println(employee.toString());
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	@Override
	public Employee selectByEmployeeEmail(String email) {
		Employee employee = null;
		try {
			Connection connection = cf.getConnection();
			String sql = "SELECT * FROM empInfo WHERE email = ?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("INSIDE selectByEmail");
				employee = new Employee();

				employee.setEmployeeId(rs.getInt("empid"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setJobTitle(rs.getString("jobtitle"));
				employee.setDepartmentName(rs.getString("dptname"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
				employee.setReportTo(rs.getInt("reportto"));
				employee.setPhoneNumber(rs.getString("phonenumber"));
				employee.setAvailbleFunds(rs.getDouble("refund"));
				System.out.println(employee.toString());
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

}