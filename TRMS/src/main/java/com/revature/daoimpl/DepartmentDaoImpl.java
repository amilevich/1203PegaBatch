package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bean.Department;
import com.revature.dao.DepartmentDao;

public class DepartmentDaoImpl implements DepartmentDao {

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
	public void selectDepartment(Department department) throws SQLException {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM department WHERE department_name=?");
			ps.setString(1, department.getDepartmentName());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
					department.setDepartmentId(rs.getInt("department_id"));
				}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
