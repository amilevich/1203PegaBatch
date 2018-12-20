package com.revature.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Accounts;
import com.revature.beans.Login;
import com.revature.dao.AccountsDAO;
import com.revature.util.ConnFactory;

public class AccountsDAOImpl implements AccountsDAO {

	public static ConnFactory cf = ConnFactory.getInstance();

	//CREATE
	@Override
	public void createAccountProc(Accounts accs) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = " { call INSERTACCOUNT(?, ?, ?, ?)";
		CallableStatement callS = conn.prepareCall(sql);
		callS.setString(1, accs.getAccounType());
		callS.setDouble(2, accs.getBalance());
		callS.setString(3, accs.getStatus());
		callS.setInt(4, accs.getUserID());
		callS.execute();
	}

	//READ
	@Override
	public void getSpecificAccount(Login logIn, Accounts accs) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM accounts WHERE user_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, logIn.getUserID());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println("The Bank ID of this user is :" + rs.getInt(1));
			accs.setAccountID(rs.getInt("ACC_ID"));
			accs.setUserID(rs.getInt("USER_ID"));
			accs.setBalance(rs.getDouble("BALANCE"));
			accs.setAccounType(rs.getString("ACC_TYPE"));
			accs.setStatus(rs.getString("STATUS"));
			System.out.println(accs.toString());
		}
		
	}

	@Override
	public void getAccountStatus(Accounts accs, int usrID) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM accounts WHERE user_id = ? AND acc_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, usrID);
		ps.setInt(2, accs.getAccountID());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
//			System.out.println("The Bank ID of this user is :" + rs.getInt(1));
//			accs.setAccountID(rs.getInt("ACC_ID"));
			accs.setUserID(rs.getInt("USER_ID"));
			accs.setBalance(rs.getDouble("BALANCE"));
			accs.setAccounType(rs.getString("ACC_TYPE"));
			accs.setStatus(rs.getString("STATUS"));
//			System.out.println(accs.toString());
		}	
	}

	@Override 
	public void viewAccounts(Accounts accs, Login logIn) throws SQLException{
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM accounts FULL OUTER JOIN login ON accounts.user_id = login.user_id";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			accs.setAccountID(rs.getInt("ACC_ID"));
			accs.setUserID(rs.getInt("USER_ID"));
			accs.setBalance(rs.getDouble("BALANCE"));
			accs.setAccounType(rs.getString("ACC_TYPE"));
			accs.setStatus(rs.getString("STATUS"));
			logIn.setUserName(rs.getString("USER_NAME"));
			System.out.println(accs.toString() + " UserName: " + logIn.getUserName());
		}
	}
	
	@Override
	public void editAccount(Accounts accs) throws Exception {
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM accounts WHERE acc_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, accs.getAccountID());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println("The Bank ID of this user is :" + rs.getInt(1));
			accs.setUserID(rs.getInt("USER_ID"));
			accs.setBalance(rs.getDouble("BALANCE"));
			accs.setAccounType(rs.getString("ACC_TYPE"));
			accs.setStatus(rs.getString("STATUS"));
		}
	}
	
	//UPDATE
	@Override
	public void updateAccount(Accounts accs) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = " { call UPDATEACCOUNT(?, ?, ?, ?, ?)";
		CallableStatement callS = conn.prepareCall(sql);
		callS.setInt(1, accs.getAccountID());
		callS.setString(2, accs.getAccounType());
		callS.setDouble(3, accs.getBalance());
		callS.setString(4, accs.getStatus());
		callS.setInt(5, accs.getUserID());
		callS.execute();
		
	}

	//DELETE
	@Override
	public void deleteAccount(int account_id, int usr_id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = " { call DELETEACCOUNT(?, ?)";
		CallableStatement callS = conn.prepareCall(sql);
		callS.setInt(1, account_id);
		callS.setInt(2, usr_id);
		callS.execute();
	}
}