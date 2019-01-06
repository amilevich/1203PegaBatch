package com.ternary.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				employee.setDepartmentHeadId(rs.getInt("dpthead"));
				employee.setPhoneNumber(rs.getString("phonenumber"));
				employee.setRefund(rs.getDouble("refund"));
				employee.setRefundPending(rs.getDouble("refundpending"));
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
				employee.setDepartmentHeadId(rs.getInt("dpthead"));
				employee.setPhoneNumber(rs.getString("phonenumber"));
				employee.setRefund(rs.getDouble("refund"));
				employee.setRefundPending(rs.getDouble("refundpending"));
				System.out.println(employee.toString());
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee = null;
		Connection conn = cf.getConnection();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
			while (rs.next()) {
				employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10),
						rs.getDouble(11), rs.getDouble(12));
				employeeList.add(employee);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;

	}

}
