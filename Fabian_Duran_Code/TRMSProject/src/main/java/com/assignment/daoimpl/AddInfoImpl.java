package com.assignment.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assignment.bean.AdditionalInformation;
import com.assignment.bean.Reimbursement;
import com.assignment.dao.AdditionalInformationDAO;
import com.assignment.utilities.ConnFactory;

public class AddInfoImpl implements AdditionalInformationDAO {
	public static ConnFactory cf = ConnFactory.getInstance();
	Connection conn = cf.getConnection();

	@Override
	public void createAddInfo(int rei_id, int from_id, int to_id, String request) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO additional_info (ai_id, rei_id, from_id, to_id, request, state) VALUES (add_info_seq.NEXTVAL, ?, ?, ?, ?, 0)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, rei_id);
		ps.setInt(2, from_id);
		ps.setInt(3, to_id);
		ps.setString(4, request);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t notice CREATED");
		sql = "SELECT MAX (ai_id) AS latest FROM additional_info";
		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
			tempI = rs.getInt(1);

	}
	@Override
	public  AdditionalInformation readAddInfo(int ai_id) throws SQLException{
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM additional_info WHERE ai_id = ? ";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, ai_id);

		ResultSet rs = ps.executeQuery();
		AdditionalInformation ai = null;
		while (rs.next()) {
			ai = new AdditionalInformation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(7),
					rs.getString(5), rs.getString(6));
		}

		return ai;
	}
	@Override
	public AdditionalInformation readAddInfo(int rei_id, int emp_id, int state) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = null;
		switch (state) {
		case 0:
			sql = "SELECT * FROM additional_info WHERE rei_id = ? AND to_id = ?";
		case 1:
			sql = "SELECT * FROM additional_info WHERE rei_id = ? AND from_id = ?";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, rei_id);
		ps.setInt(2, emp_id);
		ResultSet rs = ps.executeQuery();
		AdditionalInformation ai = null;
		while (rs.next()) {
			ai = new AdditionalInformation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(7),
					rs.getString(5), rs.getString(6));
		}

		return ai;
	}

	@Override
	public void updateResponse(int ai_id, String response) throws SQLException {// stores a response and changes row to
																				// 1 to show that notification has been
																				// returned
		Connection conn = cf.getConnection();
		String sql = "UPDATE additional_info SET response = ?, state = 1 WHERE ai_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, response);
		ps.setInt(2, ai_id);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t notice UPDATED");
	}

	@Override
	public List<AdditionalInformation> getNotificationList(int empID) throws SQLException {
		Connection conn = cf.getConnection();
		List<AdditionalInformation> notificationList = new ArrayList<AdditionalInformation>();
		String sql = "SELECT * FROM additional_info WHERE to_id = ? AND state = 0";// searches for any sent messages
																					// without responses
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, empID);
		ResultSet rs = ps.executeQuery();
		AdditionalInformation ai = null;
		while (rs.next()) {
			ai = new AdditionalInformation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(7),
					rs.getString(5), rs.getString(6));
			notificationList.add(ai);
		}
		sql = "SELECT * FROM additional_info WHERE from_id = ? AND state = 1";// now we're getting messages that are
																				// returning to user
		ps = conn.prepareStatement(sql);
		ps.setInt(1, empID);
		rs = ps.executeQuery();
		while (rs.next()) {
			ai = new AdditionalInformation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(7),
					rs.getString(5), rs.getString(6));
			notificationList.add(ai);
		}

		return notificationList;
	}

	@Override
	public void finishAdditionalInformation(int ai_id) throws SQLException {// once read and acknowledged, labels
																			// message as read and complete removing it
																			// from notification list
		Connection conn = cf.getConnection();
		String sql = "UPDATE additional_info SET state = 3 WHERE ai_id = 3";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, ai_id);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\tnotification FINISHED");

	}

}
