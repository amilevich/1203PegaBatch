package com.revature.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.daos.User_Account_JunctDao;
import com.revature.util.ConnFactory;

public class User_Account_JunctDaoImpl implements User_Account_JunctDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	// CRUD
	
	// CREATE
	
	/**
	 * Adds a useraccount entry into the junction table 
	 * Makes a connection between a given user and bank account
	 * effectively making that user an authorized owner of the account
	 * DOES NOT CHECK ENTRY FOR VALIDITY
	 * Check beforehand using isOwner.
	 */
	@Override
	public boolean addUserAccount(int user_id, int acc_id) {
		try(Connection conn = cf.getConnection()) {
			String sql = "INSERT INTO useracc_bankacc VALUES(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,user_id);
			ps.setInt(2, acc_id);
			ps.executeQuery();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}


	
	// READ
	
	/**
	 * checks to see if the given userid is an owner of the given account
	 */
	@Override
	public boolean isOwner(int user_id, int acc_id) {
		try(Connection conn = cf.getConnection()) {
			String sql = "SELECT user_id FROM useracc_bankacc WHERE user_id=? AND acc_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, acc_id);
			ResultSet rs = ps.executeQuery();
			// If a row was found with the matching id and account, return true, otherwise return false
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	// UPDATE
	
	// DELETE
	
}
