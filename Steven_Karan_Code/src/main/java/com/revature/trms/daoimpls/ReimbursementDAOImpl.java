package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.trms.dao.ReimbursementDAO;
import com.revature.trms.models.Address;
import com.revature.trms.models.Event;
import com.revature.trms.models.Reimbursement;
import com.revature.trms.util.ConnFactory;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	private static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean insertReimbursement(Reimbursement reimb) {
		try (Connection conn = cf.getConnection();) {
			String sql = "INSERT INTO reimbursement VALUES(null,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			// Turn off auto-commit
			conn.setAutoCommit(false);

			ps.setInt(1, reimb.getEmployee().getEmp_id());
			int addr_id = new AddressDAOImpl().insertAddress(reimb.getEvent().getLocation());
			
			if(addr_id > 0) {
				reimb.getEvent().getLocation().setAddress_id(addr_id);
			}else {
				return false;
			}
			
			int event_id = new EventDAOImpl().insertEvent(reimb.getEvent());
			if(event_id > 0) {
				reimb.getEvent().setEvent_id(event_id);
			}else {
				return false;
			}
			
			
			
			ps.setInt(2, reimb.getEvent().getEvent_id());
			ps.setDate(3, Date.valueOf(reimb.getRequest_date()));
			ps.setString(4, reimb.getJustification());
			ps.setInt(5, reimb.getWork_time_missed());
			if (reimb.getStatus_id() < 0)
				ps.setInt(6, reimb.getStatus_id());
			else
				ps.setNull(6, 1);
			
			ps.setDouble(7, reimb.getFund_awarded());
			
			if( ps.executeUpdate() > 0) {
				System.out.println("COMMITTING CHANGES");
				conn.commit();
				return true;
			}
		
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Reimbursement getReimbursement(int id) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM reimb_view WHERE reimb_id = ? SORT BY start_date";
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

				// setting Event Detail
				event.setLocation(location);
				event.setEvent_id(rs.getInt("event_id"));
				event.setType_name(rs.getString("type_name"));
				event.setStart_date(rs.getDate("start_date").toLocalDate());
				event.setStart_time(rs.getTimestamp("start_time"));
				event.setFormat_name(rs.getString("format_name"));
				event.setDefault_passing_grade(rs.getString("default_passing_grade"));
				event.setDescription(rs.getString("description"));
				event.setCost(rs.getDouble("cost"));

				// setting Reimbursement
				reimb.setReimb_id(rs.getInt("reimb_id"));
				reimb.setEmployee(new EmployeeDAOImpl().getEmployeeByID(rs.getInt("emp_id")));
				reimb.setEvent(new EventDAOImpl().getEvent(rs.getInt("event_id")));
				reimb.setStatus_id(rs.getInt("status_id"));
				reimb.setRequest_date(rs.getDate("request_date").toLocalDate());
				
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
				event.setCost(rs.getDouble("cost"));

				// setting Reimbursement
				reimb.setReimb_id(rs.getInt("reimb_id"));
				reimb.setEmployee(new EmployeeDAOImpl().getEmployeeByID(rs.getInt("emp_id")));
				reimb.setEvent(new EventDAOImpl().getEvent(rs.getInt("event_id")));
				reimb.setStatus_id(rs.getInt("status_id"));
				reimb.setRequest_date(rs.getDate("request_date").toLocalDate());
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
			String sql = "UPDATE reimbursement SET emp_id=?, event_id=?, status_id=?, request_date=?, justification=?, work_time_missed=? WHERE reimb_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(2, reimb.getEmployee().getEmp_id());
			ps.setInt(3, reimb.getEvent().getEvent_id());
			ps.setInt(4, reimb.getStatus_id());
			ps.setDate(5, Date.valueOf(reimb.getRequest_date()));
			ps.setString(6, reimb.getJustification());
			ps.setInt(7, reimb.getWork_time_missed());
			ps.setInt(8, reimb.getReimb_id());

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

	@Override
	public ArrayList<Reimbursement> getAllReimbursementByEmployee(int id) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM reimb_view WHERE emp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
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
				event.setCost(rs.getDouble("cost"));

				// setting Reimbursement
				reimb.setReimb_id(rs.getInt("reimb_id"));
				reimb.setEmployee(new EmployeeDAOImpl().getEmployeeByID(rs.getInt("emp_id")));
				reimb.setEvent(new EventDAOImpl().getEvent(rs.getInt("event_id")));
				reimb.setStatus_id(rs.getInt("status_id"));
				reimb.setRequest_date(rs.getDate("request_date").toLocalDate());
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
	public ArrayList<Reimbursement> getAllReimbursementRequireManagement(int next) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM reimb_view WHERE next_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, next);
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
				event.setCost(rs.getDouble("cost"));

				// setting Reimbursement
				reimb.setReimb_id(rs.getInt("reimb_id"));
				reimb.setEmployee(new EmployeeDAOImpl().getEmployeeByID(rs.getInt("emp_id")));
				reimb.setEvent(new EventDAOImpl().getEvent(rs.getInt("event_id")));
				reimb.setStatus_id(rs.getInt("status_id"));
				reimb.setRequest_date(rs.getDate("request_date").toLocalDate());
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
}