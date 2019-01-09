package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Login;
import com.revature.model.User;
import com.revature.util.ConnFactory;

public class UserDAOImpl implements UserDAO{
	static{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	public static ConnFactory cf = ConnFactory.getInstance();
	@Override
	public User selectUserById(int userId) {
		User user = new User();
		Connection conn = cf.getConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("SELECT * FROM user_db WHERE user_id = ?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("ds_id"), rs.getInt("dep_id"));
			}
			TicketDAOImpl tdi = new TicketDAOImpl();
			user.setTickets(tdi.selectTicketsByUserId(userId));
			user.setTicketsToApprove(tdi.selectTicketsForApprovalByUserId(userId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

}
