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
				request.setEventName(rs.getString("eventName"));
				request.setDateCompleted(rs.getDate("requestCompleted"));
				request.setRequestId(rs.getInt("requestID"));
				request.setEmpId(rs.getInt("empID"));
				request.setEventId(rs.getInt("eventID"));
				request.setUrgent(rs.getInt("requestUrgent"));
				request.setStatus(rs.getString("requestStatus"));
				request.setJustification(rs.getString("justification"));
				request.setApprovalSupervisor(rs.getInt("supervisorApproval"));
				request.setApprovalDeptHead(rs.getInt("dptHeadApproval"));
				request.setApprovalBenco(rs.getInt("benCoApproval"));

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
