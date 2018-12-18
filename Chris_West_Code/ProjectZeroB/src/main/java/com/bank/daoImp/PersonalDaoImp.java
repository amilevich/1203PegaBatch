package com.bank.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.main.PersonalInformation;

public class PersonalDaoImp {
	private static final String USERNAME = "westbank";
	private static final String PASSWORD = "westBank";
	private static final String URL = "jdbc:oracle:thin:@octocatdb.cwpwwksk2urc.us-east-2.rds.amazonaws.com:1521:ORCL";

	public PersonalInformation getPersonalInfo(int userID) {

		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "SELECT * FROM PERSONAL WHERE USER_ID = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, userID);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return new PersonalInformation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getLong(10));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;
	}

}
