package com.revature.trms.daoimpls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.revature.trms.dao.EventDAO;
import com.revature.trms.models.Event;
import com.revature.trms.util.ConnFactory;

public class EventDAOImpl implements EventDAO {

	private static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public int insertEvent(Event event) {
		try (Connection conn = cf.getConnection();) {
			String sql = "BEGIN INSERT INTO event_detail VALUES(null,?,?,?,?,?,?,?,?,?) RETURNING event_id INTO ?; END;";
			CallableStatement cs = conn.prepareCall(sql);
			System.out.println(event);
			cs.setString(1, event.getType_name());
			cs.setDate(2, Date.valueOf(event.getStart_date()));
			cs.setTimestamp(3, event.getStart_time());
			cs.setInt(4, event.getLocation().getAddress_id());
			cs.setString(5, event.getFormat_name());
			cs.setString(6, event.getDescription());
			cs.setString(7, event.getPassing_grade());
			cs.setString(8, event.getGrade_received());
			cs.setDouble(9, event.getCost());
			
			// Registering return param:
			cs.registerOutParameter(10, Types.NUMERIC);
			cs.executeUpdate();
			int rowAffected = cs.getInt(10);
			return rowAffected;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Event getEvent(int id) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM event_detail WHERE event_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			Event event = null;
			if (rs.next()) {
				event = new Event();
				event.setEvent_id(rs.getInt("event_id"));
				event.setType_name(rs.getString("type_name"));
				event.setStart_date(rs.getDate("start_date").toLocalDate());
				event.setStart_time(rs.getTimestamp("start_time"));
				event.setLocation(new AddressDAOImpl().getAddressById(rs.getInt("location_id")));
				event.setFormat_name(rs.getString("format_name"));
				event.setDescription(rs.getString("Description"));
				event.setPassing_grade(rs.getString("passing_grade"));
				event.setGrade_received(rs.getString("grade_received"));
				event.setCost(rs.getDouble("cost"));
			}
			return event;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateEvent(Event event) {
		try (Connection conn = cf.getConnection();) {
			 
			String sql = "UPDATE employee SET type_name=?, start_date=?, start_time=?, location_id=?, format_name=?, description=?, passing_grade=?, grade_received=?,cost=? WHERE event_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(event);
			ps.setString(1, event.getType_name());
			ps.setDate(2, Date.valueOf(event.getStart_date()));
			ps.setTimestamp(3, event.getStart_time());
			ps.setInt(4, event.getLocation().getAddress_id());
			ps.setString(5, event.getFormat_name());
			ps.setString(6, event.getDescription());
			ps.setString(7, event.getPassing_grade());
			ps.setString(8, event.getGrade_received());
			ps.setDouble(9, event.getCost());
			ps.setInt(10, event.getEvent_id());
			
			// Note: at most 1 row can be updated at a time given that the where clause selects an id
			if ( ps.executeUpdate() >= 1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEvent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
