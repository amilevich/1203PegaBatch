package com.revature.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnFactory;

public class UserDaoImpl implements UserDao {
	
	public static ConnFactory cf = ConnFactory.getInstance();

	public User getUserById(int id) {
		User user = null;
		try (Connection conn = cf.getConnection();) {
			
			String sql = "SELECT * FROM bank_user WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User getUserByIdentifier(String username, String password) {
		User user = null;
		try (Connection conn = cf.getConnection();) {
			
			String sql = "SELECT * FROM bank_user WHERE username = ? AND user_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
		
	}
	
	
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		try(Connection conn = cf.getConnection();){
			String sql = "SELECT * FROM bank_user";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {

				userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}

	public int createUser(User user) {
		int rowAffected = 0;
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);

			String sql = "BEGIN INSERT INTO bank_user VALUES(null, ?, ?, ?) RETURNING user_id INTO ?; END;";
			CallableStatement cs = conn.prepareCall(sql);			
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword().toString());
			cs.setString(3, user.getRole());
			cs.registerOutParameter(4, Types.NUMERIC);
			cs.executeUpdate();
			rowAffected = cs.getInt(4);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(user.getRole().equals("CUSTOMER"))
		{
			//call customer info
		}
		
		return rowAffected;
	}

	public boolean updateUser(User user) {
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "UPDATE bank_user SET username = ?, user_password = ?, role = ? WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword().toString());
			ps.setString(3, user.getRole());
			ps.setInt(4, user.getId());
			ps.executeQuery();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int deleteUser(int id) {
		int rowAffected = 0;
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM account_user WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM bank_user WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowAffected;
	}
	
//	public static void main(String[] args) {
//		List<User> userl = new UserDaoImpl().getAllUsers();
//		userl.forEach(user-> System.out.println(user.toString()));
//		int usr = new UserDaoImpl().createUser(new User("TestingUsers","word123".toCharArray(),"CUSTOMER"));
//		User new_user = new UserDaoImpl().getUserById(usr);
//
//		System.out.println((new UserDaoImpl().updateUser(
//				new User(new_user.getId(), "TesingUser-Updated", new_user.getPassword(), new_user.getRole()))));
//		
//		System.out.println(new UserDaoImpl().getUserById(1));
//		
//		System.out.println(new UserDaoImpl().deleteUser(new_user.getId()));
//		
//		System.out.println(new UserDaoImpl().getUserById(new_user.getId()));
//		System.out.println(new UserDaoImpl().getUserByIdentifier("mike89", "word123").toString());
//	}

}
