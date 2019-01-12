package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bean.Employee;
import com.revature.bean.Login;
import com.revature.bean.Management;
import com.revature.dao.ManagementDao;

public class ManagementDaoImpl implements ManagementDao {

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
	public void selectManagement(Management management, Login login) throws SQLException {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM management WHERE user_id=?");
			ps.setInt(1, login.getLoginId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
					management.setManagementId(rs.getInt("manager_id"));
					management.setFirstName(rs.getString("first_name"));
					management.setLastName(rs.getString("last_name"));
					management.setDepartmentId(rs.getInt("department_id"));
					management.setAvaiReimbursement(rs.getDouble("avai_reimbursement"));
					management.setAwardedReimbursement(rs.getDouble("awarded_reimbursement"));
					management.setPendingReimbursement(rs.getDouble("pending_reimbursement"));
					management.setReportsto(rs.getInt("reportsto"));
					management.setUserId(rs.getInt("user_id"));
				}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void employeeManager(Management management, Employee employee) throws SQLException {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM management WHERE manager_id=?");
			ps.setInt(1, employee.getReportsto());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
					management.setManagementId(rs.getInt("manager_id"));
					management.setFirstName(rs.getString("first_name"));
					management.setLastName(rs.getString("last_name"));
					management.setDepartmentId(rs.getInt("department_id"));
					management.setAvaiReimbursement(rs.getDouble("avai_reimbursement"));
					management.setAwardedReimbursement(rs.getDouble("awarded_reimbursement"));
					management.setPendingReimbursement(rs.getDouble("pending_reimbursement"));
					management.setReportsto(rs.getInt("reportsto"));
					management.setUserId(rs.getInt("user_id"));
				}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateReimbursementAmounts(Management management) throws SQLException {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement(" { call UPDATEMANAGEMENTAMOUNTS(?, ?, ?, ?) } ");
			ps.setInt(1, management.getManagementId());
			ps.setDouble(2, management.getAvaiReimbursement());
			ps.setDouble(3, management.getAwardedReimbursement());
			ps.setDouble(4, management.getPendingReimbursement());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
