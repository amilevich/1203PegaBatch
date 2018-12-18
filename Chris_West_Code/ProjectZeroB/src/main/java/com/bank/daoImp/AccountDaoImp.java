package com.bank.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.main.Account;
import com.bank.util.Connector;

public class AccountDaoImp {
	public static Connector cr = Connector.getInstance();

	public Account getAccount(int userID) {

		try (Connection conn = cr.getConnection()){
			String sql = "SELECT * FROM ACCOUNT WHERE USER_ID = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return new Account(rs.getDouble(1), rs.getString(2), rs.getInt(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Account getJointAccount(int userID) {

		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "SELECT * FROM ACCOUNT WHERE USER_ID = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return new Account(rs.getDouble(1), rs.getString(2), rs.getInt(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
