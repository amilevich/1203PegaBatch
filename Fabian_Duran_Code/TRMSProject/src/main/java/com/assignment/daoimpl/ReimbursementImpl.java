package com.assignment.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assignment.bean.Grade;
import com.assignment.bean.Reimbursement;
import com.assignment.dao.ReimbursementDAO;
import com.assignment.utilities.ConnFactory;

public class ReimbursementImpl implements ReimbursementDAO {
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public void createReimbursement(int emp_id, String reiType, double awardRequest, String submitDate,
			String eventStartDate, int ds_id, int dh_id, String reiState, int urgent, String description,
			String eventTitle) throws SQLException {
		Connection conn = cf.getConnection();
		int rei_type = 0;
		int rei_state = 0;
		switch (reiType) {
		case "University Course":
			rei_type = 0;
			break;
		case "Seminar":
			rei_type = 1;
			break;
		case "Certification Preparation Class":
			rei_type = 2;
			break;
		case "Certification":
			rei_type = 3;
			break;
		case "Technical Training Class":
			rei_type = 4;
			break;
		case "Other":
			rei_type = 5;
			break;
		}
		switch (reiState) {
		case "Submitted":
			rei_state = 1;
			break;
		case "Pending":
			rei_state = 2;
			break;
		case "Denied":
			rei_state = 3;
			break;
		case "Approved":
			rei_state = 4;
			break;
		case "Cancelled":
			rei_state = 5;
			break;
		case "Waiting on Response":
			rei_state = 6;
			break;
		}
		String sql = "INSERT INTO reimbursement (rei_id, emp_id, rei_type, awd_req, awd_grnt, sub_date, evnt_stdate, ds_id, dh_id,"
				+ " rei_state, urgent, description, evnt_title, ds_appr, dh_appr, bc_appr, final_appr) VALUES (reim_id_seq.NEXTVAL, ?, ?, ?, 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, emp_id);
		ps.setInt(2, rei_type);
		ps.setDouble(3, awardRequest);
		ps.setString(4, submitDate);
		ps.setString(5, eventStartDate);
		ps.setInt(6, ds_id);
		ps.setInt(7, dh_id);
		ps.setInt(8, rei_state);
		ps.setInt(9, urgent);
		ps.setString(10, description);
		ps.setString(11, eventTitle);
		ps.setInt(12, 0);// these represent the approvals states on the reimbursements, 0 means they're
							// not done or null
		ps.setInt(13, 0);
		ps.setInt(14, 0);
		ps.setInt(15, 0);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t new reimbursement inserted");
	}

	@Override
	public Reimbursement readReimbursement(int rei_id) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM VIEW_REIMBURSEMENT WHERE rei_id = ?");
		ps.setInt(1, rei_id);
		ResultSet rs = ps.executeQuery();
		Reimbursement r = null;
		while (rs.next()) {// if value in sql is null, that variable will have 0, string slots should
							// always have a value
			r = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getDouble(3), rs.getDouble(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11),
					rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17),
					rs.getString(18), rs.getInt(19), rs.getInt(20), rs.getString(21));
		}
		return r;
	}

	@Override
	public void updateAwardGranted(int rei_id, double amount) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE reimbursement SET awd_grnt = ? WHERE rei_id = ?");
		ps.setDouble(1, amount);
		ps.setInt(2, rei_id);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t awd_grnt UPDATED");
	}

	@Override
	public void updateEventFinishDate(int rei_id, String date) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE reimbursement SET evnt_fndate = ? WHERE rei_id = ?");
		ps.setString(1, date);
		ps.setInt(2, rei_id);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t evnt_fndate UPDATED");
	}

	@Override
	public void updateCompletedDate(int rei_id, String date) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE reimbursement SET comp_date = ? WHERE rei_id = ?");
		ps.setString(1, date);
		ps.setInt(2, rei_id);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t comp_date UPDATED");
	}

	@Override
	public void updateDSAppr(int rei_id, int appr) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE reimbursement SET ds_appr = ? WHERE rei_id = ?");
		ps.setInt(1, appr);
		ps.setInt(2, rei_id);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t ds_appr UPDATED");
	}

	@Override
	public void updateDHAppr(int rei_id, int appr) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE reimbursement SET dh_appr = ? WHERE rei_id = ?");
		ps.setInt(1, appr);
		ps.setInt(2, rei_id);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t dh_appr UPDATED");
	}

	@Override
	public void updateBCAppr(int rei_id, int appr) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE reimbursement SET bc_appr = ? WHERE rei_id = ?");
		ps.setInt(1, appr);
		ps.setInt(2, rei_id);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t bc_appr UPDATED");
	}

	@Override
	public void updateFinalAppr(int rei_id, int appr) throws SQLException {// ---------THIS NEEDS TO BE LOOKED AT AGAIN,
																			// HOW ARE YOU GOING TO ASSIGN THE FINAL
																			// APPROVER?!
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE reimbursement SET final_appr = ? WHERE rei_id = ?");
		ps.setInt(1, appr);
		ps.setInt(2, rei_id);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t FINAL_APPR UPDATED");
	}

	@Override
	public void updateReiState(int rei_id, int state) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE reimbursement SET rei_state = ? WHERE rei_id = ?");
		ps.setInt(1, state);
		ps.setInt(2, rei_id);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t STATE UPDATED");
	}

	@Override
	public void updateGradeID(int rei_id, int gradeID) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("UPDATE reimbursement SET grade_id = ? WHERE rei_id = ?");
		ps.setInt(1, gradeID);
		ps.setInt(2, rei_id);
		int tempI = ps.executeUpdate();
		System.out.println(tempI + "\t GRADE UPDATED");
	}

	@Override
	public List<Reimbursement> getPendingEmpList(int empID) throws SQLException {
		List<Reimbursement> reiList = new ArrayList<Reimbursement>();
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM view_reimbursement WHERE emp_id = ? AND (rs_def = 'Pending' OR rs_def = 'Waiting Event Completion' OR rs_def = 'Change in Award' OR rs_def = 'Waiting Final Approval' OR rs_def = 'Submitted')");
		ps.setInt(1, empID);
		ResultSet rs = ps.executeQuery();
		// System.out.println("was empty? "+rs.wasNull());
		Reimbursement r = null;
		while (rs.next()) {// if value in sql is null, that variable will have 0, string slots should
							// always have a value
			r = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getDouble(3), rs.getDouble(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11),
					rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17),
					rs.getString(18), rs.getInt(19), rs.getInt(20), rs.getString(21));
			reiList.add(r);
		}
		conn.close();
		// System.out.println(reiList.get(0));
		return reiList;
	}

	@Override
	public List<Reimbursement> getDHPendingReviewList(int apprID, String empType) throws SQLException {// this still
																										// needs
																										// a LOT OF WORK
		List<Reimbursement> reiList = new ArrayList<Reimbursement>();
		Connection conn = cf.getConnection();
		PreparedStatement ps = null;
		if (empType.equals("Department Head") || empType.equals("Benefits Coordinator Department Head"))
			ps = conn.prepareStatement("SELECT * FROM VIEW_REIMBURSEMENT WHERE dh_id = ? AND rs_def = 'Pending'");
		else
			ps = conn.prepareStatement("SELECT * FROM VIEW_REIMBURSEMENT WHERE bc_id = ? AND rs_def = 'Pending'");
		ps.setInt(1, apprID);
		ResultSet rs = ps.executeQuery();
		Reimbursement r = null;
		while (rs.next()) {// if value in sql is null, that variable will have 0, string slots should
							// always have a value
			r = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getDouble(3), rs.getDouble(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11),
					rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17),
					rs.getString(18), rs.getInt(19), rs.getInt(20), rs.getString(21));
			reiList.add(r);
		}
		conn.close();
		return reiList;
	}

	public List<Reimbursement> getBCPendingReviewList() throws SQLException {
		List<Reimbursement> reiList = new ArrayList<Reimbursement>();
		GradeImpl gimpl = new GradeImpl();
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM VIEW_REIMBURSEMENT WHERE dh_appr = 1 AND (rs_def = 'Waiting Final Approval' OR rs_def = 'Pending')'");
		ResultSet rs = ps.executeQuery();
		Reimbursement r = null;
		Grade g = null;
		while (rs.next()) {// if value in sql is null, that variable will have 0, string slots should
							// always have a value
			r = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getDouble(3), rs.getDouble(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11),
					rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17),
					rs.getString(18), rs.getInt(19), rs.getInt(20), rs.getString(21));
			g = gimpl.readGrade(r.getRei_id());
			if (!g.getGradeFormat().equals("Presentation"))// so long as it doesn't equal Presentation, put it on the
															// list
				reiList.add(r);
		}
		conn.close();
		return reiList;
	}

	public List<Reimbursement> getDSFinalReviewList() throws SQLException {
		List<Reimbursement> reiList = new ArrayList<Reimbursement>();
		GradeImpl gimpl = new GradeImpl();
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM VIEW_REIMBURSEMENT WHERE rs_def = 'Waiting Final Approval'");
		ResultSet rs = ps.executeQuery();
		Reimbursement r = null;
		Grade g = null;
		while (rs.next()) {// if value in sql is null, that variable will have 0, string slots should
							// always have a value
			r = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getDouble(3), rs.getDouble(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11),
					rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17),
					rs.getString(18), rs.getInt(19), rs.getInt(20), rs.getString(21));
			g = gimpl.readGrade(r.getRei_id());
			if (g.getGradeFormat().equals("Presentation"))// For ds, if its a presentation they have final review
				reiList.add(r);
		}
		conn.close();
		return reiList;
	}

	public List<Reimbursement> getSubmittedReviewList(int apprID) throws SQLException {// this still needs a LOT OF WORK
		List<Reimbursement> reiList = new ArrayList<Reimbursement>();
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM VIEW_REIMBURSEMENT WHERE ds_id = ? AND rs_def = 'Submitted'");
		ps.setInt(1, apprID);
		ResultSet rs = ps.executeQuery();
		Reimbursement r = null;
		while (rs.next()) {// if value in sql is null, that variable will have 0, string slots should
							// always have a value
			r = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getDouble(3), rs.getDouble(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11),
					rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17),
					rs.getString(18), rs.getInt(19), rs.getInt(20), rs.getString(21));
			reiList.add(r);
		}
		System.out.println("Okay here" + reiList.size());
		conn.close();
		return reiList;
	}

	@Override
	public List<Reimbursement> getPendingHistoryList(int empID) throws SQLException {
		List<Reimbursement> reiList = new ArrayList<Reimbursement>();
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM VIEW_REIMBURSEMENT WHERE emp_id = ?");
		ps.setInt(1, empID);
		ResultSet rs = ps.executeQuery();
		Reimbursement r = null;
		while (rs.next()) {// if value in sql is null, that variable will have 0, string slots should
							// always have a value
			r = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getDouble(3), rs.getDouble(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11),
					rs.getInt(12), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17),
					rs.getString(18), rs.getInt(19), rs.getInt(20), rs.getString(21));
			reiList.add(r);
		}
		conn.close();
		return reiList;
	}
}
