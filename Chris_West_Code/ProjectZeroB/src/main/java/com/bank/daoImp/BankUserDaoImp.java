package com.bank.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao.BankUserDao;
import com.bank.main.Bank;
import com.bank.main.User;
import com.bank.util.Connector;

public class BankUserDaoImp implements BankUserDao {
	public static Connector cr = Connector.getInstance();

	@Override
	public int createUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}
	/*
	 * 
	 * Check to see if Username and password are legit in the BANKUSER Table
	 * 
	 */
	@Override
	public User getUserById(String username, String password, String email) {

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT * FROM BANKUSER WHERE (USER_USERNAME = ? OR USER_EMAIL_Address = ?) AND USER_PASSWORD = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);

			ps.setString(2, username);
			ps.setString(3, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			Bank.msg.loginFailedMsg();
			Bank.login();

		}
		Bank.msg.loginFailedMsg();
		Bank.login();
		return null;
	}
	/*
	 * 
	 * Check to see if USERNAME/EMAIL in the BankUSER Table
	 * 
	 */
	public Boolean userIdCheck(String username, String email) {

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT * FROM BANKUSER WHERE USER_USERNAME = ? OR USER_EMAIL_ADDRESS = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);

			ps.setString(2, email);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

		return true;
	}
	/*
	 * 
	 * Add New User after they complete the Registration form
	 * 
	 */
	public void addNewUser() {
		try (Connection conn = cr.getConnection()) {
			/*
			 * 
			 * Insert New User Information into the BankUSER Table
			 * 
			 */
			String sql = "INSERT INTO BANKUSER (user_id,USER_USERNAME,USER_PASSWORD,USER_EMAIL_ADDRESS,USER_TYPE_PERMISSIONS, USER_LOGGED_IN,USER_ACCOUNT_LOCKED)"
					+ "VALUES (user_id.nextval,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Bank.reg.getUserName());
			ps.setString(2, Bank.reg.getPassWord());
			ps.setString(3, Bank.reg.getEmail());
			ps.setInt(4, 1);
			ps.setInt(5, 0);
			ps.setInt(6, 0);

			ps.executeUpdate();
			/*
			 * 
			 * SELECT New User ID FROM the BANKUSER Table
			 * 
			 */			

			String sql2 = "SELECT USER_ID FROM BANKUSER WHERE USER_USERNAME = ?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, Bank.reg.getUserName());
			ResultSet rs = ps2.executeQuery();
			int userID = 0;
			if(rs.next())
			userID = rs.getInt(1);

			/*
			 * 
			 * Insert New User Information into the Personal Table
			 * 
			 */
			String sql3 = "INSERT INTO PERSONAL (PERSONAL_ID,USER_ID, PERSONAL_FIRST_NAME, PERSONAL_MIDDLE_INITIAL,"
					+ "PERSONAL_LAST_NAME,PERSONAL_ADDRESS,PERSONAL_CITY,"
					+ "PERSONAL_STATE,PERSONAL_ZIP,PERSONAL_PHONE_NUMBER) VALUES(personal_id.nextval,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps3 = conn.prepareStatement(sql3);
			ps3.setInt(1, userID);
			ps3.setString(2, Bank.reg.getFirstName());
			ps3.setString(3, Bank.reg.getMiddleInitial());
			ps3.setString(4, Bank.reg.getLastName());
			ps3.setString(5, Bank.reg.getAddress());
			ps3.setString(6, Bank.reg.getCity());
			ps3.setString(7, Bank.reg.getState());
			ps3.setInt(8, Bank.reg.getZip());
			ps3.setFloat(9, Bank.reg.getPhoneNum());
			ps3.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int userId(String username, String email) {

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT USER_ID FROM BANKUSER WHERE USER_USERNAME = ? OR USER_EMAIL_ADDRESS = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);

			ps.setString(2, email);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			

		}

		return 0;
	}

}
