package com.bank.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao.BankUserDao;
import com.bank.main.Bank;
import com.bank.main.User;
import com.bank.util.Connector;

public class BankUserDaoImp implements BankUserDao {
	public static Connector cr = Connector.getInstance();

	@Override
	public int createUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUserById(String username, String password, String email) {

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT * FROM BANKUSER WHERE USER_USERNAME = ? AND USER_PASSWORD = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, "fluffy");

//			//ps.setString(1, email);
			ps.setString(2, "LWhFfOsi");

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			Bank.login();

		}
		return null;
	}

	public Boolean userIdCheck(String username, String email) {

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT * FROM BANKUSER WHERE USER_USERNAME = ? OR USER_EMAIL_ADDRESS";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);

			ps.setString(2, email);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

		return true;
	}

}
