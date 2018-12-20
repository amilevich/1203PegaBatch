package com.revature.daoimpls;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.daos.UserDao;
import com.revature.pojos.User;
import com.revature.pojos.User.UserType;
import com.revature.util.ConnFactory;

public class UserDaoImpl implements UserDao {

	public static ConnFactory cf = ConnFactory.getInstance();

	/**
	 * Gets a user with the provided username from the useracc table if one exists
	 */
	@Override
	public User getUserByUsername(String username) {
		try (Connection conn = cf.getConnection()) {
			String sql = "SELECT * FROM useracc WHERE username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			/*
			 * If a user is found with the provided username, package up the users details
			 * into a User object and return it
			 */
			if (rs.next()) {
				// ID, Username, Password, Usertype

				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				/*
				 * Set the user type based on the value in the 4th column (manual translation
				 * instead of lookup for simplicity in this case since this is not expected to
				 * change)
				 */
				switch (rs.getInt(4)) {
				case 1:
					user.setUserType(UserType.CUSTOMER);
					break;
				case 2:
					user.setUserType(UserType.EMPLOYEE);
					break;
				case 3:
					user.setUserType(UserType.ADMIN);
					break;
				case 4:
					user.setUserType(UserType.SUPER);
					break;

				default:
					break;
				}

				return user;

			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// If a user is not found with the provided username, return null
		return null;

	}

	public boolean createUser(User user) {
		try (Connection conn = cf.getConnection()) {
			String sql = "INSERT INTO useracc VALUES(null, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			// Unpack given user into sql arguments
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			switch (user.getUserType()) {
			case CUSTOMER:
				ps.setInt(3, 1);
				break;
			case EMPLOYEE:
				ps.setInt(3, 2);
				break;
			case ADMIN:
				ps.setInt(3, 3);
				break;
			case SUPER:
				ps.setInt(3, 4);
				break;
			default:
				break;
			}

			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteUser(int userid) {
		try (Connection conn = cf.getConnection()) {
			String sql = "{call delete_user(?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, userid);
			cs.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateUser(int userid, String username, String password) {
		try (Connection conn = cf.getConnection()) {
			String sql = "UPDATE useracc SET username=?, password=? WHERE user_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, userid);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<String> getCustomers() {
		try (Connection conn = cf.getConnection()) {
			String sql = "SELECT username FROM user_view WHERE type='Customer'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<String> customers = new ArrayList<String>();
			while (rs.next()) {
				customers.add(rs.getString(1));
			}
			if (!customers.isEmpty()) {
				return customers;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
