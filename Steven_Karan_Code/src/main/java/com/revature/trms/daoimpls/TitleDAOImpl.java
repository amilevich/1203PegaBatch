package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.dao.TitleDAO;
import com.revature.trms.util.ConnFactory;

public class TitleDAOImpl implements TitleDAO {

	private static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public int getTitleID(String titlename) {
		try (Connection conn = cf.getConnection()) {
			String sql = "SELECT title_id FROM title WHERE title_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, titlename);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int i = rs.getInt("title_id");
				return i;
			} else {
				// 0 indicates that the query was successful but the department could not be
				// found
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// -1 indicates that the query was unsuccessful query overall
		return -1;

	}

}
