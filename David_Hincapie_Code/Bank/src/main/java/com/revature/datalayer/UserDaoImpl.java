package com.revature.datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.businesslayer.User;

public class UserDaoImpl implements UserDao {

	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean insertUser(User user) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO bank_user (buid, userid, firstname, lastname, username, userpassword, usertype) VALUES (USERSEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUserId());
		ps.setString(2, user.getFirstName());
		ps.setString(3, user.getLastName());
		ps.setString(4, user.getUserName());
		ps.setString(5, user.getPassword());
		ps.setString(6, user.getUserType().toString());
		int rowCount = ps.executeUpdate();
		if (rowCount == 1) {
			System.out.println("USER RECORDED INSERTED SUCCESSFULLY: " + user);
			conn.close();
			return true;
		} else {
			System.out.println("USER RECORDED INSERTED FAILURE: " + user);
			conn.close();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "UPDATE bank_user SET firstname = ?, lastname = ?, username = ?, userpassword = ?, usertype = ?  WHERE userid = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getFirstName());
		ps.setString(2, user.getLastName());
		ps.setString(3, user.getUserName());
		ps.setString(4, user.getPassword());
		ps.setString(5, user.getUserType().toString());
		//ps.setInt(6, user.getId());
		ps.setString(6, user.getUserId().toString());
		int rowCount = ps.executeUpdate();
		if (rowCount == 1) {
			System.out.println("USER RECORDED UPDATED SUCCESSFULLY: " + user);
			conn.close();
			return true;
		} else {
			System.out.println("USER RECORDED UPDATED FAILURE: " + user);
			conn.close();
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "DELETE FROM bank_user WHERE userid = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		//ps.setInt(1, user.getId());
		 ps.setString(1, user.getUserId().toString());
		int rowCount = ps.executeUpdate();
		if (rowCount == 1) {
			System.out.println("USER RECORDED DELETED SUCCESSFULLY: " + user);
			conn.close();
			return true;
		} else {
			System.out.println("USER RECORDED DELETED FAILURE: " + user);
			conn.close();
			return false;
		}
	}

	@Override
	public List<User> getUserList() throws SQLException {
		List<User> userList = new ArrayList<>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM bank_user");
		User user = null;
		while (rs.next()) {
			user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7));
			userList.add(user);
		}
		conn.close();
		return userList;
	}

	@Override
	public User findUserById(int id) {

		try {
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			User user = new User();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bank_user WHERE buid = '" + id + "'");
			int rowCount = 0;
			if (rs.next()) {
				rowCount += rs.getRow();
				user.setId(rs.getInt("buid"));
				user.setUserId(rs.getString("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("userpassword"));
				user.setUserType(rs.getString("usertype"));
				conn.close();
			}
			if (rowCount == 1) {
				System.out.println("USER FOUND. " + id);
				return user;
			} else {
				System.out.println("USER NOT FOUND. " + id);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public User findUserByUserId(String userId) throws SQLException {
		try {
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			User user = new User();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bank_user WHERE userid = '" + userId + "'");
			int rowCount = 0;
			if (rs.next()) {
				rowCount += rs.getRow();
				user.setId(rs.getInt("buid"));
				user.setUserId(rs.getString("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("userpassword"));
				user.setUserType(rs.getString("usertype"));
				conn.close();
			}
			if (rowCount == 1) {
				System.out.println("USER FOUND. " + userId);
				return user;
			} else {
				System.out.println("USER NOT FOUND. " + userId);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	// // example for callable INSERTUSER would be the name of the storeed procedure
	// @Override
	// public void createUsr(String firstName, String lastName, String userName,
	// String password) throws SQLException {
	// Connection conn = cf.getConnection();
	// String sql = "{call INSERTUSER(?, ?, ?, ?)";
	// CallableStatement cs = conn.prepareCall(sql);
	// cs.setString(1, firstName);
	// cs.setString(2, lastName);
	// cs.setString(3, userName);
	// cs.setString(4, password);
	// cs.execute();
	//
	// // This returns id
	// String sql2 = "SELECT userid FROM user where username = ?";
	// PreparedStatement ps = conn.prepareStatement(sql2);
	// ps.setString(1, userName);
	// ResultSet rs = ps.executeQuery();
	// while (rs.next()) {
	// System.out.println("The id for this user is: " + rs.getInt(1));
	// }
	//
	// conn.close();
	// }

}
