package com.assignment.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.assignment.dao.AccountsDAO;
import com.assignment.persons.Customer;
import com.assignment.utilities.Account;
import com.assignment.utilities.ConnFactory;



public class AccountsDAOImpl implements AccountsDAO {
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public void createAccount() throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call new_account";
		CallableStatement call = conn.prepareCall(sql);
		call.execute();
	}

//	@Override-->for now...
//	public void readAccount(int id) throws SQLException {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public void updateBalance(int update_type, int accountID, double amount) throws SQLException {// to either withdraw
																									// or deposit
		Connection conn = cf.getConnection();
		String sql = "";
		if (update_type == 1) // this is fine because user won't ever make this input, only a method that the
								// program controls will
			sql = "{ call account_deposit(?,?)";
		if (update_type == 2)
			sql = "{ call account_withdraw(?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, accountID);
		call.setDouble(2, amount);
		call.execute();
	}

	@Override
	public void updateApproval(int accountID, int state) throws SQLException {// to approve or deny account...
		Connection conn = cf.getConnection();
		String sql = "{ call appr_account(?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, accountID);
		call.setInt(2, state);
		call.execute();
	}

	@Override//this will be called at the menu class, not ideal but its the best way to replace the code...
	public void updateCancel(int accountID, int state) throws SQLException {// cancel or reopen an account
		Connection conn = cf.getConnection();// the reopen functionality won't be really implemented...
		String sql = "{ call close_account(?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, accountID);
		call.setInt(2, state);
		call.execute();
	}

	@Override
	public void transferFunds(int idOne, int idTwo, double amount) throws SQLException {// 1st int is account
																						// transferred from
		Connection conn = cf.getConnection();// account transferred to, amount is the amount transferred
		String sql = "{ call account_transfer(?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, idOne);
		call.setInt(2, idTwo);
		call.setDouble(3, amount);
		call.execute();
	}

	@Override//this will be called at the menu class, not ideal but its the best way to replace the code...
	public void deleteAccount(int id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call delete_account(?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, id);
		call.execute();
	}

	@Override
	public void addAccountHolder(int userID, int accountID) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call link_account(?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, userID);
		call.setInt(2, accountID);
		call.execute();
	}
	
	@Override
	public List<Account> getAccountList() throws SQLException {
		// |||||you will have to load the information from bank users, bank accounts,
		// and acc_holders this way...
		List<Account> AccountList = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM bank_account");//this about getting the information and manipulating it, not reading it
		Account a = null;// temporary object
		while (rs.next()) {// while there is something to return...
			a = new Account(rs.getInt(1), rs.getDouble(2),rs.getInt(3),rs.getInt(4));// id is in index 1, balance is in index 2
			AccountList.add(a);// adds object to the list
		}
		conn.close();
		return AccountList;//call this and make this list in the account class
	}

	@Override
	public List<Integer> viewPendingAccounts() throws SQLException {//returns a list of pending accounts
		List<Integer> AccountList = new ArrayList<Integer>();//come back to this...this might not work as you think...
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM view_pending_accounts");//this doesn't get the full account object...
		Integer a = 0;// temporary object
		while (rs.next()) {// while there is something to return...
			a = rs.getInt(1);// id is in index 1, balance is in index 2
			AccountList.add(a);// adds object to the list
		}
		conn.close();
		return AccountList;
	}

	@Override
	public List<Customer> getAccountHolders() throws SQLException {
		List<Customer> accountHolders = new ArrayList<Customer>();//come back to this...this might not work as you think...
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM acc_holder");//this doesn't get the full account object...
		Customer c = null;// temporary object
		while (rs.next()) {// while there is something to return...
			c = new Customer(rs.getInt(1), rs.getInt(2));// id is in index 1, balance is in index 2
			accountHolders.add(c);// adds object to the list
		}
		conn.close();
		return accountHolders;
	}
}
