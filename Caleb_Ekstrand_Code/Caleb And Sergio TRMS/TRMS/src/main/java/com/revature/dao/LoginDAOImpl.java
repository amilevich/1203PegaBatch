package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Login;

import com.revature.util.ConnFactory;

public class LoginDAOImpl implements LoginDAO{
	static{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	public static ConnFactory cf = ConnFactory.getInstance();

	public Login selectLoginByUsername(String username) {
		Login login = new Login();
		Connection conn = cf.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("SELECT * FROM login_db WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				login = new Login(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return login;
	}

}
