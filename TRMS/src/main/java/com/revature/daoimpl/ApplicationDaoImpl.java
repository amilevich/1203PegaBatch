package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Application;
import com.revature.dao.ApplicationDao;

public class ApplicationDaoImpl implements ApplicationDao {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static String url = "jdbc:oracle:thin:@octocatdbp.c2mjrycz2a8r.us-east-2.rds.amazonaws.com:1521:ORCL";
	private static String username = "TRMS_DB";
	private static String password = "p4ssw0rd";
	
	@Override
	public void insertEmployInfo(Application application) throws SQLException {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			//String sql = "{ call INSERTINFO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
			//CallableStatement cs = conn.prepareCall(sql);
			PreparedStatement ps = conn.prepareStatement("{ call INSERTINFO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			ps.setString(1, application.getFirstName());
			ps.setString(2, application.getLastName());
			ps.setString(3, application.getEmail());
			ps.setInt(4, application.getDepartmentId());
			ps.setInt(5, application.getManagerId());
			ps.setInt(6, application.getEventId());
			ps.setString(7, application.getEventLocation());
			ps.setDouble(8, application.getEventCost());
			ps.setString(9, application.getAppStatus());
			ps.setString(10, application.getEventDate());
			ps.setInt(11, application.getUserId());
			ps.setString(12, application.getGradeFormat());
			ps.setString(13, application.getJustification());
			ps.setString(14, application.getSummary());
			ps.setInt(15, application.getHoursMissed());
			ps.setInt(16, application.getSuperviserApproved());
			ps.setInt(17, application.getDepartmentheadApproved());
			ps.setInt(18, application.getBencoApproved());
			ps.setBlob(19, application.getAttachment());
			ps.setBlob(20, application.getApprovedAttahment());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Application> selectAppInfo() throws SQLException {
		List<Application> appList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, username, password)){
		String sql = "SELECT * FROM application";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Application app = null;
		
		while(rs.next())
		{
			app = new Application(rs.getInt("app_id"), rs.getString("first_name"), rs.getString("last_name"), 
					rs.getString("email"), rs.getInt("department_id"), rs.getInt("manager_id"), 
					rs.getInt("event_id"), rs.getString("eventLocation"), rs.getDouble("eventCost"),
					rs.getString("app_status"), rs.getString("event_date"), rs.getInt("user_id"),
					rs.getString("grade_format"), rs.getString("work_justification"), rs.getString("summary"),
					rs.getInt("hours_missed"), rs.getInt("superviser_approved"), 
					rs.getInt("departmenthead_approved"), rs.getInt("benco_approved"), null, null);
			appList.add(app);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appList;
	}
}
