package com.bank.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.util.Connector;

public class PermDaoImp {
	public static Connector cr = Connector.getInstance();

	public String getUserPerm(int perm) {

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT permission_id FROM Perm \n" + "WHERE user_type_permissions = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, perm);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;

	}

}
