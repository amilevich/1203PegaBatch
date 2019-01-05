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

	public List<Request> getRequests(int empId) {

		List<Request> requests = new ArrayList<Request>();
		Request request;

		try {

			Connection connection = cf.getConnection();

			String sql = "SELECT * " + "FROM requestInfo " + "WHERE requestInfo.empID = ? ";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, empId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				request = new Request();
				request.setRequestId(rs.getInt("requestId"));
				request.setEmployeeId(rs.getInt("empId"));
				request.setRequestCompleted(rs.getDate("dateCompleted"));
				request.setStatus(rs.getString("requestStatus"));
				request.setMoreInfo(rs.getBoolean("moreInfo"));
				request.setJustification(rs.getString("justification"));
				request.setStatus(rs.getString("requestStatus"));
				request.setJustification(rs.getString("justification"));
				request.setDirectMgrApprovalId(rs.getInt("directManagerApproved"));
				request.setDeptHeadApprovalId(rs.getInt("departmentHeadApproved"));
				request.setBencoApproval(rs.getInt("bencoApproved"));
				request.setDenied(rs.getBoolean("requestDenied"));
				request.setDeniedReason(rs.getString("deniedReason"));
				request.setPreApprovedSupervisorId(rs.getInt("preApproved"));
				request.setApprovalAttachment(rs.getBlob("approvalAttachment"));
				request.setProjectedReimbursement(rs.getDouble("projectedAward"));
				request.setAwardChanged(rs.getBoolean("awardChangedBenco"));
				request.setExceedAvailable(rs.getBoolean("exceedAvilable"));
				request.setPassingGrade(rs.getString("passingGrade"));
				request.setFinalGrade(rs.getString("finalGrade"));
				request.setUploadedPresentation(rs.getBoolean("presentation"));
				request.setPresentationAttachment(rs.getBlob("presentationAttach"));
				request.setEventDescription(rs.getString("eventDescription"));
				request.setEventCost(rs.getDouble("eventCost"));
				request.setEventStart(rs.getDate("eventStart"));
				request.setEventEnd(rs.getDate("eventEnd"));
				// request.setEventTime(rs.getString("eventTime"));
				request.setEventType(rs.getString("eventTypeName"));
				request.setReimbCoverage(rs.getInt("reimbursementCoverage"));
				request.setStreetAddress(rs.getString("streetAddress"));
				request.setCity(rs.getString("city"));
				request.setState(rs.getString("state"));
				request.setCountry(rs.getString("country"));
				request.setZipCode(rs.getString("zipCode"));
				request.setGradeType(rs.getString("gradeType"));

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
