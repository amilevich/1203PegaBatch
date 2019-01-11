package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.trms.dao.GradingFormatDAO;
import com.revature.trms.models.GradingFormat;
import com.revature.trms.util.ConnFactory;

public class GradingFormatDAOImpl implements GradingFormatDAO {

	@Override
	public GradingFormat getGradingFormat(String format_name) {

		try(Connection conn = ConnFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM grading_format WHERE format_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, format_name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				GradingFormat gf = new GradingFormat(rs.getString("format_name"),rs.getString("default_passing_grade"));
				return gf;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
