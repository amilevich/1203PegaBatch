package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.Attachment;
import com.revature.model.Ticket;
import com.revature.util.ConnFactory;

public class TicketDAOImpl implements TicketDAO {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public void insertTicket(Ticket ticket) {
		Connection conn = cf.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("INSERT INTO ticket_db(event_date, event_time, "
					+ "event_loc, event_desc, event, event_cost, gf_id, gf_passing, "
					+ "justification, user_id) VALUES(?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, ticket.getDate());
			ps.setString(2, ticket.getTime());
			ps.setString(3, ticket.getLocation());
			ps.setString(4, ticket.getDescription());
			ps.setString(5, ticket.getEvent());
			ps.setInt(6, ticket.getCost());
			ps.setInt(7, ticket.getGfId());
			ps.setString(8, ticket.getGfPassing());
			ps.setString(9, ticket.getJustification());
			ps.setInt(10, ticket.getUserId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Ticket> selectTicketsByUserId(int userId) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		DownloadDAOImpl ddi = new DownloadDAOImpl();
		Connection conn = cf.getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("SELECT * FROM ticket_db WHERE user_id = ? ORDER BY ticket_id");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ArrayList<Attachment> att = new ArrayList<Attachment>();
				att.add(ddi.getBlob(rs.getInt("ticket_id")));
				Ticket ticket = new Ticket(rs.getInt("ticket_id"), rs.getString("event_date"),
						rs.getString("event_time"), rs.getString("event_loc"), rs.getString("event_desc"),
						rs.getString("event"), rs.getInt("event_cost"), rs.getInt("gf_id"), rs.getString("gf_passing"),
						rs.getString("justification"), rs.getInt("user_id"), rs.getString("status"),
						rs.getString("stage"), att);
				tickets.add(ticket);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tickets;
	}

	@Override
	public ArrayList<Ticket> selectTicketsForApprovalByUserId(int userId) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Connection conn = cf.getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("SELECT * FROM ticket_db WHERE user_id IN "
					+ "(SELECT user_id FROM user_db WHERE ds_id = ?) AND stage = 'request' ORDER BY ticket_id");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Ticket ticket = new Ticket(rs.getInt("ticket_id"), rs.getString("event_date"),
						rs.getString("event_time"), rs.getString("event_loc"), rs.getString("event_desc"),
						rs.getString("event"), rs.getInt("event_cost"), rs.getInt("gf_id"), rs.getString("gf_passing"),
						rs.getString("justification"), rs.getInt("user_id"), rs.getString("status"),
						rs.getString("stage"));
				tickets.add(ticket);
			}
			ps = conn.prepareStatement("SELECT dep_id FROM dep_head_db" + " WHERE dep_head_id = ?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				ps = conn.prepareStatement("SELECT * FROM ticket_db WHERE user_id IN "
						+ "(SELECT user_id FROM user_db WHERE dep_id = ?) AND stage = 'DS Approved' ORDER BY ticket_id");
				ps.setInt(1, rs.getInt("dep_id"));
				ResultSet rs2 = ps.executeQuery();
				while (rs2.next()) {
					Ticket ticket = new Ticket(rs2.getInt("ticket_id"), rs2.getString("event_date"),
							rs2.getString("event_time"), rs2.getString("event_loc"), rs2.getString("event_desc"),
							rs2.getString("event"), rs2.getInt("event_cost"), rs2.getInt("gf_id"),
							rs2.getString("gf_passing"), rs2.getString("justification"), rs2.getInt("user_id"),
							rs2.getString("status"), rs2.getString("stage"));
					tickets.add(ticket);
				}
			}
			ps = conn.prepareStatement("SELECT dep_id FROM dep_benco_db" + " WHERE dep_benco_id = ?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				ps = conn.prepareStatement("SELECT * FROM ticket_db WHERE user_id IN "
						+ "(SELECT user_id FROM user_db WHERE dep_id = ?) AND stage = 'DH Approved' ORDER BY ticket_id");
				ps.setInt(1, rs.getInt("dep_id"));
				ResultSet rs2 = ps.executeQuery();
				while (rs2.next()) {
					Ticket ticket = new Ticket(rs2.getInt("ticket_id"), rs2.getString("event_date"),
							rs2.getString("event_time"), rs2.getString("event_loc"), rs2.getString("event_desc"),
							rs2.getString("event"), rs2.getInt("event_cost"), rs2.getInt("gf_id"),
							rs2.getString("gf_passing"), rs2.getString("justification"), rs2.getInt("user_id"),
							rs2.getString("status"), rs2.getString("stage"));
					tickets.add(ticket);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tickets;
	}

	@Override
	public void approveTicket(int ticketId, String stage) {
		Connection conn = cf.getConnection();
		PreparedStatement ps;
		if (stage.equals("request")) {
			try {
				ps = conn.prepareStatement("UPDATE ticket_db SET stage = 'DS Approved'" + "WHERE ticket_id = ?");
				ps.setInt(1, ticketId);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (stage.equals("DS Approved")) {
			try {
				ps = conn.prepareStatement("UPDATE ticket_db SET stage = 'DH Approved'" + "WHERE ticket_id = ?");
				ps.setInt(1, ticketId);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (stage.equals("DH Approved")) {
			try {
				ps = conn.prepareStatement("UPDATE ticket_db SET stage = 'BC Approved'" + "WHERE ticket_id = ?");
				ps.setInt(1, ticketId);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
	@Override
	public void denyTicket(int ticketId) {
		Connection conn = cf.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("UPDATE ticket_db SET status = 'denied'" + "WHERE ticket_id = ?");
			ps.setInt(1, ticketId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void cancelTicket(int ticketId) {
		Connection conn = cf.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("DELETE FROM ticket_db WHERE ticket_id = ?");
			ps.setInt(1, ticketId);
			ps.executeQuery();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	 }
	}

}
