package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Login;
import com.revature.dao.LoginDAO;
import com.revature.util.ConnFactory;

public class LoginDAOImpl implements LoginDAO {
	
	public static ConnFactory cf = ConnFactory.getInstance();
	
	//Create Implementation
	@Override
	public void createLoginProc(Login logIn) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = " { call INSERTLOGIN(?, ?)";
		CallableStatement callS = conn.prepareCall(sql);
		callS.setString(1, logIn.getUserName());
		callS.setString(2, logIn.getPassWord());
		callS.execute();
		System.out.println("You are now a registered user.");
		System.out.println();
	}

	//View
	@Override
	public List<Login> getLoginList() throws SQLException {
		List<Login> logList = new ArrayList<>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM LOGIN");
		Login s = null;
		while(rs.next()) {
			s = new Login(rs.getInt("USER_ID"), rs.getString("USER_NAME"), rs.getString("PASS_WORD"));
			logList.add(s);
		}
		conn.close();
		return logList;
	}

	@Override
	public boolean isInLogin(Login logIn) throws SQLException {
		Connection conn = cf.getConnection();
		boolean isThere = false;
		String sql = "SELECT user_name, pass_word FROM login";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String uN = rs.getString("user_name");
			String pW = rs.getString("pass_word");
			if(logIn.getUserName().equals(uN) & logIn.getPassWord().equals(pW)) {
				isThere = true;
				return isThere;
			}
		}
		return isThere;
	}

	@Override
	public int getUserId(Login logIn) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT user_id FROM login WHERE user_name = ? and pass_word = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, logIn.getUserName());
		ps.setString(2, logIn.getPassWord());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println("The ID of this user is :" + rs.getInt(1));
			logIn.setUserID(rs.getInt(1)); 
		}
		return logIn.getUserID();
	}
}
