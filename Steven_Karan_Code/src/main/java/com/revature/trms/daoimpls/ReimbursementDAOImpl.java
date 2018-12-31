package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import com.revature.trms.dao.ReimbursementDAO;
import com.revature.trms.models.Address;
import com.revature.trms.models.Employee;
import com.revature.trms.models.Event;
import com.revature.trms.models.Reimbursement;
import com.revature.trms.util.ConnFactory;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	private static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean insertReimbursement(Reimbursement reimb) {
		try (Connection conn = cf.getConnection();) {
			String sql = "INSERT INTO reimbursement VALUES(null,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(2, reimb.getEmp_id());
			ps.setInt(3, reimb.getEvent_id());
			ps.setInt(4, reimb.getStatus_id());
			ps.setDate(5, Date.valueOf(reimb.getRequest_date()));
			ps.setDouble(6, reimb.getCost());
			ps.setString(7, reimb.getJustification());
			ps.setInt(8, reimb.getWork_time_missed());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Reimbursement getReimbursement(int id) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM reimb_view WHERE reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Reimbursement reimb = null;
			Event event = null;
			Address location = null;
			if (rs.next()) {
				reimb = new Reimbursement();
				event = new Event();
				location = new Address();

				// setting Reimbursement Status
				reimb.setUrgent(rs.getInt("urgent") == 1 ? true : false);
				reimb.setSup_flag(rs.getInt("sup_flag") == 1 ? true : false);
				reimb.setDept_flag(rs.getInt("dept_flag") == 1 ? true : false);
				reimb.setBenco_flag(rs.getInt("benco_flag") == 1 ? true : false);
				reimb.setNext_id(rs.getInt("next_id"));

				// setting address
				location.setAddress_id(rs.getInt("location_id"));
				location.setStreet_number(rs.getString("street_number"));
				location.setRoute(rs.getString("route"));
				location.setCity(rs.getString("city"));
				location.setState(rs.getString("state"));
				location.setCountry(rs.getString("country"));

				// setting Event Details
				event.setLocation(location);
				event.setEvent_id(rs.getInt("event_id"));
				event.setType_name(rs.getString("type_name"));
				event.setStart_date(rs.getDate("start_date").toLocalDate());
				event.setStart_time(rs.getTimestamp("start_time"));
				event.setFormat_name(rs.getString("format_name"));
				event.setDefault_passing_grade(rs.getString("default_passing_grade"));
				event.setDescription(rs.getString("description"));

				// setting Reimbursement
				reimb.setReimb_id(rs.getInt("reimb_id"));
				reimb.setEmp_id(rs.getInt("emp_id"));
				reimb.setEvent_id(rs.getInt("event_id"));
				reimb.setStatus_id(rs.getInt("status_id"));
				reimb.setRequest_date(rs.getDate("request_date").toLocalDate());
				reimb.setCost(rs.getDouble("cost"));
				reimb.setJustification(rs.getString("justification"));
				reimb.setWork_time_missed(rs.getInt("work_time_missed"));
				reimb.setFund_awarded(rs.getDouble("fund_awarded"));
			}

			return reimb;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getAllReimbursement() {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM reimb_view";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<Reimbursement> reimb_list = new ArrayList<>();
			Reimbursement reimb = null;
			Event event = null;
			Address location = null;

			while (rs.next()) {
				reimb = new Reimbursement();
				event = new Event();
				location = new Address();

				// setting Reimbursement Status
				reimb.setUrgent(rs.getInt("urgent") == 1 ? true : false);
				reimb.setSup_flag(rs.getInt("sup_flag") == 1 ? true : false);
				reimb.setDept_flag(rs.getInt("dept_flag") == 1 ? true : false);
				reimb.setBenco_flag(rs.getInt("benco_flag") == 1 ? true : false);
				reimb.setNext_id(rs.getInt("next_id"));

				// setting address
				location.setAddress_id(rs.getInt("location_id"));
				location.setStreet_number(rs.getString("street_number"));
				location.setRoute(rs.getString("route"));
				location.setCity(rs.getString("city"));
				location.setState(rs.getString("state"));
				location.setCountry(rs.getString("country"));

				// setting Event Details
				event.setLocation(location);
				event.setEvent_id(rs.getInt("event_id"));
				event.setType_name(rs.getString("type_name"));
				event.setStart_date(rs.getDate("start_date").toLocalDate());
				event.setStart_time(rs.getTimestamp("start_time"));
				event.setFormat_name(rs.getString("format_name"));
				event.setDefault_passing_grade(rs.getString("default_passing_grade"));
				event.setDescription(rs.getString("description"));
				event.setCoverage(rs.getDouble("coverage"));

				// setting Reimbursement
				reimb.setReimb_id(rs.getInt("reimb_id"));
				reimb.setEmp_id(rs.getInt("emp_id"));
				reimb.setEvent_id(rs.getInt("event_id"));
				reimb.setStatus_id(rs.getInt("status_id"));
				reimb.setRequest_date(rs.getDate("request_date").toLocalDate());
				reimb.setCost(rs.getDouble("cost"));
				reimb.setJustification(rs.getString("justification"));
				reimb.setWork_time_missed(rs.getInt("work_time_missed"));
				reimb.setFund_awarded(rs.getDouble("fund_awarded"));

				reimb_list.add(reimb);
			}
			return reimb_list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateReimbursement(Reimbursement reimb) {
		try (Connection conn = cf.getConnection();) {
			// TODO: NOTE: check that not null values are not null from any calling method
			String sql = "UPDATE reimbursement SET emp_id=?, event_id=?, status_id=?, request_date=?, cost=?, justification=?, work_time_missed=? WHERE reimb_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(2, reimb.getEmp_id());
			ps.setInt(3, reimb.getEvent_id());
			ps.setInt(4, reimb.getStatus_id());
			ps.setDate(5, Date.valueOf(reimb.getRequest_date()));
			ps.setDouble(6, reimb.getCost());
			ps.setString(7, reimb.getJustification());
			ps.setInt(8, reimb.getWork_time_missed());
			ps.setInt(9, reimb.getReimb_id());

			// Note: at most 1 row can be updated at a time given that the where clause
			// selects an id
			if (ps.executeUpdate() >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteReimbursement(int id) {

		return false;
	}

	public static void main(String[] args) {
		Event event = new Event(Date.valueOf(LocalDate.now()), new Timestamp(System.currentTimeMillis()), "C", "hello this a test.", "Seminars",
				new Address("12007", "Bruce B Downs Blvd","Tampa", "FL","US","33612"), );
		Reimbursement reimb = new Reimbursement();
		new ReimbursementDAOImpl().insertReimbursement(reimb);

	}
}
