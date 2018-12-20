package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Account;
import com.revature.dao.AccountDAO;
import com.revature.driver.Driver;
import com.revature.util.ConnFactory;

/**
 * Account DAO Implementation
 * 
 * @author Blake Biskner
 * @version 2.0
 * 
 */

public class AccountDAOImpl implements AccountDAO {
	public static ConnFactory cf=ConnFactory.getInstance();
	
	// CREATE
	
	// READ
	@Override
	public void getAccountMap() throws SQLException{
		Connection conn=cf.getConnection();
		String sql="SELECT * FROM BankAccount";
		// Note this will not populate the holder ArrayList of Account objects
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		Account account=null;
		while(rs.next()) {
			account=new Account(rs.getInt(1),rs.getDouble(2)); // Account Number Balance Constructor
			Driver.accounts.put(rs.getInt(1),account);
		}
		// Note once again that account holders have not yet been set
		conn.close();
	}
	
	@Override
	public List<String> getAccountHolders(int acctNum) throws SQLException{
		List<String> usernameList= new ArrayList<>();
		Connection conn=cf.getConnection();
		String sql="SELECT customer_username FROM JunctionUsernameAccount WHERE account_id=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, acctNum);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			usernameList.add(rs.getString(1));
		}
		conn.close();
		return usernameList;
	}
}
