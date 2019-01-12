package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bean.Employee;
import com.revature.bean.Login;
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
	public void selectEmployee(Employee employ, Login login) throws SQLException {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM employee WHERE user_id = ?");
			ps.setInt(1, login.getLoginId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				employ.setEmployeeId(rs.getInt("employee_id"));
				employ.setFirstName(rs.getString("first_name"));
				employ.setLastName(rs.getString("last_name"));
				employ.setDepartmentId(rs.getInt("department_id"));
				employ.setAvaiReimbursement(rs.getDouble("avai_reimbursement"));
				employ.setAwaredReimbursement(rs.getDouble("awarded_reimbursement"));
				employ.setPendingReimbursement(rs.getDouble("pending_reimbursement"));
				employ.setReportsto(rs.getInt("reportsto"));
				employ.setUserId(rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateReimbursementAmounts(Employee employ) throws SQLException {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement(" { call UPDATEAMOUNTS(?, ?, ?, ?) } ");
			ps.setInt(1, employ.getEmployeeId());
			ps.setDouble(2, employ.getAvaiReimbursement());
			ps.setDouble(3, employ.getAwaredReimbursement());
			ps.setDouble(4, employ.getPendingReimbursement());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}