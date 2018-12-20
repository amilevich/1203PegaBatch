package com.revature.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.daos.ApplicationDao;
import com.revature.pojos.Application;
import com.revature.util.ConnFactory;

/**
 * Implementation of the ApplicationDao interface used to interact directly with the 'Application' table.
 * In this context, application refers to bank account applications (aka form) made by users,
 * requesting a bank account be created
 * @author Karan
 *
 */
public class ApplicationDaoImpl implements ApplicationDao {

	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean createApplication(Application application) {
		try (Connection conn = cf.getConnection();) {
			String sql = "INSERT INTO application VALUES(null,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, application.getUser_id());
			int holder2 = application.getUser_id2();
			if (holder2 == 0) {
				ps.setObject(2, null);
			} else {
				ps.setInt(2, holder2);
			}
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Application> getApplications() {
		try (Connection conn = cf.getConnection()) {
			String sql = "SELECT * FROM app_view ORDER BY APP_ID1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Application> applications = new ArrayList<Application>();
			while (rs.next()) {
				// Package application instance:
				Application application = new Application(rs.getInt(1), rs.getInt(2), rs.getString(3));
				int user_2 = rs.getInt(4);
				if (user_2 != 0) {
					application.setUser_id2(user_2);
				}
				String username_2 = rs.getString(5);
				if (username_2 != null) {
					application.setUsername_2(username_2);
				}
				applications.add(application);
			}
			return applications;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean removeApplication(int appid) {
		try (Connection conn = cf.getConnection()) {
			String sql = "Delete FROM application WHERE app_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, appid);
			ps.executeQuery();
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Application getApplicationById(int appid) {
		try (Connection conn = cf.getConnection()) {
			String sql = "SELECT * FROM app_view WHERE app_id1=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, appid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Application application = new Application(rs.getInt(1), rs.getInt(2), rs.getString(3));
				int user_2 = rs.getInt(4);
				if (user_2 != 0) {
					application.setUser_id2(user_2);
				}
				String username_2 = rs.getString(5);
				if (username_2 != null) {
					application.setUsername_2(username_2);
				}
				return application;
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
