package com.ternary.daoimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import com.ternary.dao.RequestDao;
import com.ternary.model.Employee;
import com.ternary.model.Request;
import com.ternary.util.ConnFactory;

public class RequestDaoImpl implements RequestDao {

	public static ConnFactory cf = ConnFactory.getInstance();

	// CREATE
	@Override
	public int insertAttachment(String fileName, int requestId) {
		Connection connection = cf.getConnection();
		int rowAffected = 0;
		// PreparedStatement ps = null;
		CallableStatement cs = null;

		try {
			String sql = "INSERT INTO attachment VALUES(attachment_seq.nextval, ?, ?) RETURNING locationid INTO ?; END; ";
			// ps = connection.prepareStatement(sql);
			cs = connection.prepareCall(sql);
			File file = new File("");
			long fileLength = file.length();
			FileInputStream fis = new FileInputStream(fileName);
			cs.setBinaryStream(2, fis, fileLength);
			cs.executeUpdate();
			rowAffected = cs.getInt(3);
			fis.close();
			cs.close();
			cs = null;
			connection.commit();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cs != null) {
				try {
					cs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return rowAffected;
	}

	@Override
	public int insertCompleteRequest(Request request) {
		int locationId;
		int eventId;
		int gradeId;
		int requestId;
		int attachmentId;
		// eventlocation, event, grade, request
		System.out.println("RUNNING insertEventLocation");
		locationId = insertEventLocation(request);

		System.out.println("RUNNING insertEvent");
		eventId = insertEvent(request, locationId);

		System.out.println("RUNNING insertGrade");
		gradeId = insertGrade(request);

		System.out.println("RUNNING insertRequest");
		requestId = insertRequest(request, eventId, gradeId);
		// attachmentId =
		// insertAttachment(request.getApprovalAttachment().getAbsolutePath(),
		// requestId);

		return requestId;
	}

	@Override
	public int insertEventLocation(Request request) {
		Connection conn = cf.getConnection();
		int rowAffected = 0;
		try {
			// insert the eventlocation
			String sql = "BEGIN INSERT INTO eventLocation VALUES (eventlocation_seq.nextval, ?,?,?,?,?) RETURNING locationid INTO ?; END;";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, request.getStreetAddress());
			cs.setString(2, request.getCity());
			cs.setString(3, request.getState());
			cs.setString(4, request.getZipCode());
			cs.setString(5, request.getCountry());
			cs.registerOutParameter(6, Types.NUMERIC);
			cs.executeUpdate();
			rowAffected = cs.getInt(6);
			return rowAffected;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowAffected;

	}

	@Override
	public int insertEvent(Request request, int locationId) {
		Connection conn = cf.getConnection();
		int rowAffected = 0;
		try {
			// insert the event values
			String sql = "BEGIN INSERT INTO event VALUES (event_seq.nextval, ?,?,?,?,?,?,?,?) RETURNING eventid INTO ?; END;";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, request.getEventType());
			cs.setInt(2, request.getGradeTypeId());
			cs.setInt(3, locationId);
			cs.setString(4, request.getEventDescription());
			cs.setDouble(5, request.getEventCost());
			cs.setString(6, request.getEventTime());
			cs.setDate(7, java.sql.Date.valueOf(request.getEventStart()));
			cs.setDate(8, java.sql.Date.valueOf(request.getEventEnd()));
			cs.registerOutParameter(9, Types.NUMERIC);
			cs.executeUpdate();
			rowAffected = cs.getInt(9);
			return rowAffected;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowAffected;
	}

	@Override
	public int insertGrade(Request request) {
		Connection conn = cf.getConnection();
		int rowAffected = 0;

		try {
			// insert the grade values
			String sql = "BEGIN INSERT INTO grade VALUES (grade_seq.nextval, ?,?,?,?) RETURNING gradeid INTO ?; END;";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, request.getPassingGrade());
			cs.setString(2, request.getFinalGrade());
			cs.setBoolean(3, request.isUploadedPresentation());
			cs.setBlob(4, (Blob) request.getPresentationAttachment());
			cs.registerOutParameter(5, Types.NUMERIC);
			cs.executeUpdate();
			rowAffected = cs.getInt(5);
			return rowAffected;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowAffected;
	}

	@Override
	public int insertRequest(Request request, int eventId, int gradeId) {
		Connection conn = cf.getConnection();
		int rowAffected = 0;
		String sql = "BEGIN INSERT INTO request VALUES (request_seq.nextval, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING requestid INTO ?; END;";
		try {
			System.out.println("YOYOYOYOYOYOYOYOY   " + request.getApprovalAttachment().getName());

			Part fileField = (Part) request.getApprovalAttachment().getInputStream();
			String filename = fileField.getSubmittedFileName();
			File file = new File(filename);
			long fileLength = file.length();

			FileInputStream fis = new FileInputStream(file);

			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, request.getEmployeeId());
			cs.setInt(2, eventId);
			cs.setInt(3, gradeId);
			cs.setDate(4, java.sql.Date.valueOf(request.getReimbursementDate()));
			cs.setString(5, request.getStatus());
			cs.setBoolean(6, request.isMoreInfo());
			cs.setString(7, request.getJustification());
			cs.setInt(8, request.getDirectMgrApprovalId());
			cs.setInt(9, request.getDeptHeadApprovalId());
			cs.setInt(10, request.getBencoApprovalId());
			cs.setBoolean(11, request.isDenied());
			cs.setString(12, request.getDeniedReason());
			cs.setInt(13, request.getPreApprovedSupervisorId());// TODO
			cs.setBinaryStream(14, fis, fileLength);
			cs.setDouble(15, request.getProjectedReimbursement());
			cs.setInt(16, 0);
			cs.setBoolean(17, request.isExceedAvailable());
			cs.setString(18, request.getExceedAvailibleComment());
			cs.registerOutParameter(19, Types.NUMERIC);
			cs.executeUpdate();
			rowAffected = cs.getInt(19);
			return rowAffected;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowAffected;
	}

	@Override
	public List<Request> getRequests(int empId) {

		List<Request> requests = new ArrayList<Request>();
		Request request;

		try {

			Connection connection = cf.getConnection();

			String sql = "SELECT * FROM requestInfo WHERE requestInfo.empID = ? ";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, empId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				request = new Request();
				request.setRequestId(rs.getInt("requestId"));
				request.setEmployeeId(rs.getInt("empId"));
				request.setReimbursementDate(
						new java.sql.Timestamp(rs.getDate("dateCompleted").getTime()).toLocalDateTime().toLocalDate());
				request.setStatus(rs.getString("requestStatus"));
				request.setMoreInfo(rs.getBoolean("moreInfo"));
				request.setJustification(rs.getString("justification"));
				request.setStatus(rs.getString("requestStatus"));
				request.setJustification(rs.getString("justification"));
				request.setDirectMgrApprovalId(rs.getInt("directManagerApproved"));
				request.setDeptHeadApprovalId(rs.getInt("departmentHeadApproved"));
				request.setBencoApprovalId(rs.getInt("bencoApproved"));
				request.setDenied(rs.getBoolean("requestDenied"));
				request.setDeniedReason(rs.getString("deniedReason"));
				request.setPreApprovedSupervisorId(rs.getInt("preApproved"));
				// request.setApprovalAttachment( rs.getBlob("approvalAttachment"));
				request.setProjectedReimbursement(rs.getDouble("projectedAward"));
				request.setAwardChanged(rs.getBoolean("awardChangedBenco"));
				request.setExceedAvailable(rs.getBoolean("exceedAvilable"));
				request.setPassingGrade(rs.getString("passingGrade"));
				request.setFinalGrade(rs.getString("finalGrade"));
				request.setUploadedPresentation(rs.getBoolean("presentation"));
				// request.setPresentationAttachment((File) rs.getBlob("presentationAttach"));
				request.setEventDescription(rs.getString("eventDescription"));
				request.setEventCost(rs.getDouble("eventCost"));
				request.setEventStart(
						new java.sql.Timestamp(rs.getDate("eventStart").getTime()).toLocalDateTime().toLocalDate());
				request.setEventEnd(
						new java.sql.Timestamp(rs.getDate("eventEnd").getTime()).toLocalDateTime().toLocalDate());
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

	public Request getRequest(int reqId) {

		Request request = new Request();

		try {

			Connection connection = cf.getConnection();

			String sql = "SELECT * " + "FROM requestInfo " + "WHERE requestInfo.requestID = ? ";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, reqId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				request.setRequestId(rs.getInt("requestId"));
				request.setEmployeeId(rs.getInt("empId"));
				request.setReimbursementDate(
						new java.sql.Timestamp(rs.getDate("dateCompleted").getTime()).toLocalDateTime().toLocalDate());
				request.setStatus(rs.getString("requestStatus"));
				request.setMoreInfo(rs.getBoolean("moreInfo"));
				request.setJustification(rs.getString("justification"));
				request.setStatus(rs.getString("requestStatus"));
				request.setJustification(rs.getString("justification"));
				request.setDirectMgrApprovalId(rs.getInt("directManagerApproved"));
				request.setDeptHeadApprovalId(rs.getInt("departmentHeadApproved"));
				request.setBencoApprovalId(rs.getInt("bencoApproved"));
				request.setDenied(rs.getBoolean("requestDenied"));
				request.setDeniedReason(rs.getString("deniedReason"));
				request.setPreApprovedSupervisorId(rs.getInt("preApproved"));
				// request.setApprovalAttachment((File) rs.getBlob("approvalAttachment"));
				request.setProjectedReimbursement(rs.getDouble("projectedAward"));
				request.setAwardChanged(rs.getBoolean("awardChangedBenco"));
				request.setExceedAvailable(rs.getBoolean("exceedAvilable"));
				request.setPassingGrade(rs.getString("passingGrade"));
				request.setFinalGrade(rs.getString("finalGrade"));
				request.setUploadedPresentation(rs.getBoolean("presentation"));
				// request.setPresentationAttachment((File) rs.getBlob("presentationAttach"));
				request.setEventDescription(rs.getString("eventDescription"));
				request.setEventCost(rs.getDouble("eventCost"));
				request.setEventStart(
						new java.sql.Timestamp(rs.getDate("eventStart").getTime()).toLocalDateTime().toLocalDate());
				request.setEventEnd(
						new java.sql.Timestamp(rs.getDate("eventEnd").getTime()).toLocalDateTime().toLocalDate());
				request.setEventTime(rs.getString("eventTime"));
				request.setEventType(rs.getString("eventTypeName"));
				request.setReimbCoverage(rs.getInt("reimbursementCoverage"));
				request.setStreetAddress(rs.getString("streetAddress"));
				request.setCity(rs.getString("city"));
				request.setState(rs.getString("state"));
				request.setCountry(rs.getString("country"));
				request.setZipCode(rs.getString("zipCode"));
				request.setGradeType(rs.getString("gradeType"));

				System.out.println(request.toString());

			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return request;

	}

	public void cancelRequest(int requestId) {

		try {

			Connection connection = cf.getConnection();

			String sql = "UPDATE Request " + "SET requestStatus = ?" + "WHERE requestId = ? ";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, "Cancel");
			ps.setInt(2, requestId);

			ps.executeQuery();

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Request> getMgrRequests(Employee employee) {

		List<Request> mgrRequests = new ArrayList<Request>();
		Request request;

		try {

			Connection connection = cf.getConnection();

			String sql = "SELECT * " + "FROM requestInfo "
					+ "WHERE ( empId IN ( SELECT empId from empInfo WHERE reportTo = ? AND empId != ?) ) AND directmanagerapproved = 0 ";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, employee.getEmployeeId());
			ps.setInt(2, employee.getEmployeeId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				request = new Request();

				request.setRequestId(rs.getInt("requestId"));
				request.setEmployeeId(rs.getInt("empId"));
				request.setReimbursementDate(
						new java.sql.Timestamp(rs.getDate("dateCompleted").getTime()).toLocalDateTime().toLocalDate());
				request.setStatus(rs.getString("requestStatus"));
				request.setMoreInfo(rs.getBoolean("moreInfo"));
				request.setJustification(rs.getString("justification"));
				request.setStatus(rs.getString("requestStatus"));
				request.setJustification(rs.getString("justification"));
				request.setDirectMgrApprovalId(rs.getInt("directManagerApproved"));
				request.setDeptHeadApprovalId(rs.getInt("departmentHeadApproved"));
				request.setBencoApprovalId(rs.getInt("bencoApproved"));
				request.setDenied(rs.getBoolean("requestDenied"));
				request.setDeniedReason(rs.getString("deniedReason"));
				request.setPreApprovedSupervisorId(rs.getInt("preApproved"));
				//request.setApprovalAttachment(rs.getBlob("approvalAttachment"));
				request.setProjectedReimbursement(rs.getDouble("projectedAward"));
				request.setAwardChanged(rs.getBoolean("awardChangedBenco"));
				request.setExceedAvailable(rs.getBoolean("exceedAvilable"));
				request.setPassingGrade(rs.getString("passingGrade"));
				request.setFinalGrade(rs.getString("finalGrade"));
				request.setUploadedPresentation(rs.getBoolean("presentation"));
				//request.setPresentationAttachment(rs.getBlob("presentationAttach"));
				request.setEventDescription(rs.getString("eventDescription"));
				request.setEventCost(rs.getDouble("eventCost"));
				request.setEventStart(
						new java.sql.Timestamp(rs.getDate("eventStart").getTime()).toLocalDateTime().toLocalDate());
				request.setEventEnd(
						new java.sql.Timestamp(rs.getDate("eventEnd").getTime()).toLocalDateTime().toLocalDate());
				// request.setEventTime(rs.getString("eventTime"));
				request.setEventType(rs.getString("eventTypeName"));
				request.setReimbCoverage(rs.getInt("reimbursementCoverage"));
				request.setStreetAddress(rs.getString("streetAddress"));
				request.setCity(rs.getString("city"));
				request.setState(rs.getString("state"));
				request.setCountry(rs.getString("country"));
				request.setZipCode(rs.getString("zipCode"));
				request.setGradeType(rs.getString("gradeType"));

				mgrRequests.add(request);

				System.out.println(request.toString());

			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mgrRequests;
	}

}