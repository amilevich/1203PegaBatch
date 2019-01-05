package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bean.Event;
import com.revature.dao.EventDao;

public class EventDaoImpl implements EventDao {

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
	public void selectEvent(Event event) throws SQLException {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM event WHERE event_type=?");
			ps.setString(1, event.getEvenType());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
					event.setEventId(rs.getInt("event_id"));
					event.setConverage(rs.getFloat("event_coverage"));
				}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
