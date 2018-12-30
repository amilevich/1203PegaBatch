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
			String sql = "SELECT\n" + "employee.empid,\n" + "employee.dptid,\n" + "employee.firstname,\n"
					+ "employee.lastname,\n" + "employee.jobtitle,\n" + "employee.email,\n" + "employee.password,\n"
					+ "employee.reportto,\n" + "employee.phonenumber,\n" + "employee.refund,\n" + "department.dptname\n"
					+ "FROM employee\n" + "INNER JOIN department ON employee.dptid = department.dptid WHERE email = ?";
			// sql = "SELECT * FROM employee WHERE email=?";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("INSIDE selectByEmail");
				employee = new Employee();
				employee.setEmployeeId(rs.getInt("empid"));
				employee.setDepartmentId(rs.getInt("dptid"));
				employee.setDepartmentName("dptname");
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setTitle(rs.getString("jobtitle"));
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
