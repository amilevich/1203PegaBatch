package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bean.Login;
import com.revature.dao.LoginDao;

public class LoginDaoImpl implements LoginDao {

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
	
	public boolean isInLogin(Login login) {
		boolean isThere = false;
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM employeelogin WHERE user_name=?");
			ps.setString(1, login.getUsername());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String uN = rs.getString("user_name");
				String pW = rs.getString("pass_word");
				
				if (login.getUsername().equals(uN) & login.getPasswd().equals(pW)) {
					login.setLoginId(rs.getInt("user_id"));
					isThere = true;
					return isThere;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isThere;
	}

	@Override
	public boolean managementLogin(Login login) throws SQLException {
		boolean isThere = false;
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM managementlogin WHERE user_name=?");
			ps.setString(1, login.getUsername());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String uN = rs.getString("user_name");
				String pW = rs.getString("pass_word");
				if (login.getUsername().equals(uN) & login.getPasswd().equals(pW)) {
					login.setLoginId(rs.getInt("user_id"));
					isThere = true;
					return isThere;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isThere;
		
	}
}
