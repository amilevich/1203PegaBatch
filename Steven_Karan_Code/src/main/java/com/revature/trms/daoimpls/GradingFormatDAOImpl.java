package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.trms.dao.GradingFormatDAO;
import com.revature.trms.models.EventType;
import com.revature.trms.models.GradingFormat;
import com.revature.trms.util.ConnFactory;

public class GradingFormatDAOImpl implements GradingFormatDAO {
	private static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean insertGradingFormat(GradingFormat format) {
		try (Connection conn = cf.getConnection();) {
			String sql = "INSERT INTO grading_format VALUES(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, format.getFormat_name());
			ps.setString(2, format.getDefault_passing_grade());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public GradingFormat getGradingFormat(String format) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM grading_format WHERE format_name= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, format);
			ResultSet rs = ps.executeQuery();
			GradingFormat g_format = null;
			if (rs.next()) {
				g_format = new GradingFormat();
				g_format.setFormat_name(rs.getString("format_name"));
				g_format.setDefault_passing_grade(rs.getString("defualt_passing_grade"));
			}
			return g_format;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<GradingFormat> getAllGradingFormat() {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM grading_format";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<GradingFormat> format_list = new ArrayList<>();
			while(rs.next()) {
				GradingFormat g_format = new GradingFormat();
				g_format.setFormat_name(rs.getString("type_name"));
				g_format.setDefault_passing_grade(rs.getString("coverage"));
				g_format.add(g_format);			}
			return type_list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateGradingFormat(GradingFormat format, String searchFormat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteGradingFormat(String format) {
		// TODO Auto-generated method stub
		return false;
	}

}
