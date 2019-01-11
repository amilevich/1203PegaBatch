package com.revature.trms.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.trms.dao.EventTypeDAO;
import com.revature.trms.models.EventStats;
import com.revature.trms.models.EventType;
import com.revature.trms.util.ConnFactory;

public class EventTypeDAOImpl implements EventTypeDAO{

	private static ConnFactory cf = ConnFactory.getInstance();
	@Override
	public boolean insertEventType(EventType type) {
		try (Connection conn = cf.getConnection();) {
			String sql = "INSERT INTO event_type VALUES(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, type.getType_name());
			ps.setDouble(2, type.getCoverage());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public EventType getEventType(String type) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM event_type WHERE type_name= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			ResultSet rs = ps.executeQuery();
			EventType e_type = null;
			if (rs.next()) {
				e_type = new EventType();
				e_type.setType_name(rs.getString("type_name"));
				e_type.setCoverage(rs.getDouble("coverage"));
			}
			return e_type;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public EventStats getEventTypeStats(String type) {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT COUNT(*) FROM event_detail WHERE type_name= ?";
			EventStats et = new EventStats();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			ResultSet rs = ps.executeQuery();
			
			String sql2 = "SELECT SUM(cost) FROM event_detail WHERE type_name = ?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, type);
			ResultSet rs2 = ps2.executeQuery();
			
			if (rs.next() && rs2.next()) {
				et.setEventType(type);
				et.setCount(rs.getInt(1));
				et.setTotalSpent(rs2.getDouble(1));
				return et;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<EventType> getAllEventTypes() {
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM event_type";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<EventType> type_list = new ArrayList<>();
			while(rs.next()) {
				EventType e_type = new EventType();
				e_type.setType_name(rs.getString("type_name"));
				e_type.setCoverage(rs.getDouble("coverage"));
				type_list.add(e_type);			
			}
			System.out.println(type_list);
			return type_list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateEventType(EventType event, String search) {
		// TODO Auto-generated method stub
		try (Connection conn = cf.getConnection();) {
			// TODO: NOTE: check that not null values are not null from any calling method
			String sql = "UPDATE event_type SET type_name=?, coverage=? WHERE type_name=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, event.getType_name());
			ps.setDouble(2, event.getCoverage());
			ps.setString(3, search);
			
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
	public boolean deleteEventType(String type) {
		// TODO Auto-generated method stub
		return false;
	}

}
