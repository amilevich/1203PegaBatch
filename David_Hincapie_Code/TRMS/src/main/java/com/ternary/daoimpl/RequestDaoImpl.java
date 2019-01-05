package com.ternary.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ternary.dao.RequestDao;
import com.ternary.model.Request;
import com.ternary.util.ConnFactory;

public class RequestDaoImpl implements RequestDao {

	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean insertRequest(Request request) throws SQLException {
		// eventlocation, event, grade, request
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO eventLocation VALUES (eventlocation_seq.nextval, ?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, request.getStreetAddress());
		ps.setString(2, request.getCity());
		ps.setString(3, request.getState());
		ps.setString(4, request.getZipCode());
		ps.setString(5, request.getCountry());
		int rowCount = ps.executeUpdate();
		if (rowCount == 1) {
			System.out.println("LOCATION INSERTED SUCCESSFULLY: " + request);
			conn.close();
			return true;
		} else {
			System.out.println("LOCATION RECORDED INSERTED FAILURE: " + request);
			conn.close();
			return false;
		}
	}

	public List<Request> getRequests(int empId, String status) {

		List<Request> requests = new ArrayList<Request>();
		Request request;

		try {

			Connection connection = cf.getConnection();

			String sql = "SELECT event.eventName, request.* " + "FROM request, event " + "WHERE request.empID = ? "
					+ "AND request.eventID = event.eventID " + "AND request.requestStatus = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, empId);
			ps.setString(2, status);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				request = new Request();
				request.setEventType(rs.getString("eventName"));
				request.setRequestCompleted(rs.getDate("requestCompleted"));
				request.setRequestId(rs.getInt("requestID"));
				request.setEmployeeId(rs.getInt("empID"));
				// request.setEventId(rs.getInt("eventID"));
				// request.setUrgent(rs.getInt("requestUrgent"));
				request.setStatus(rs.getString("requestStatus"));
				request.setJustification(rs.getString("justification"));
				request.setDirectMgrApprovalId(rs.getInt("supervisorApproval"));
				request.setDeptHeadApprovalId(rs.getInt("dptHeadApproval"));
				request.setBencoApproval(rs.getInt("benCoApproval"));

				requests.add(request);
				System.out.println(request.toString());

			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requests;

	}
}
