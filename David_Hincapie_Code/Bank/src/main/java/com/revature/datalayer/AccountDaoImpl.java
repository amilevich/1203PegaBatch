package com.revature.datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.businesslayer.Account;
import com.revature.businesslayer.Customer;

public class AccountDaoImpl implements AccountDao {

	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean insertAccount(Account account) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO bank_account (buid, accountid, balance, isjoint) VALUES (?, ?, ?, ?) ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, account.getId());
		ps.setString(2, account.getAccountid());
		ps.setDouble(3, account.getBalance());
		ps.setInt(4, account.isJointAccount() ? 1 : 0);
		int rowCount = ps.executeUpdate();
		if (rowCount == 1) {
			System.out.println("ACCOUNT INSERTED SUCCESSFULLY: " + account);
			conn.close();
			return true;
		} else {
			System.out.println("ACCOUNT RECORDED INSERTED FAILURE: " + account);
			conn.close();
			return false;
		}
	}

	@Override
	public boolean deleteAccount(Account account) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "DELETE FROM bank_account WHERE buid =? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, account.getId());
		// ps.setString(1, account.getAccountid().toString());
		int rowCount = ps.executeUpdate();
		if (rowCount == 1) {
			System.out.println("ACCOUNT DELETED SUCCESSFULLY: " + account);
			conn.close();
			return true;
		} else {
			System.out.println("ACCOUNT DELETED FAILURE: " + account);
			conn.close();
			return false;
		}
	}

	@Override
	public boolean updateAccount(Account account) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "UPDATE bank_account SET buid = ?, accountid = ?, balance = ?, isjoint = ? WHERE buid = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, account.getId());
		ps.setString(2, account.getAccountid());
		ps.setDouble(3, account.getBalance());
		ps.setInt(4, account.isJointAccount() ? 1 : 0);
		ps.setInt(5, account.getId());
		int rowCount = ps.executeUpdate();
		if (rowCount == 1) {
			System.out.println("ACCOUNT UPDATED SUCCESSFULLY: " + account);
			conn.close();
			return true;
		} else {
			System.out.println("ACCOUNT UPDATED INSERTED FAILURE: " + account);
			conn.close();
			return false;
		}
	}

	@Override
	public Account findAccountById(int id) throws SQLException {
		try {
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			Account account = new Account();
			ResultSet rs = stmt.executeQuery(
					"SELECT buid, accountid, balance, isjoint FROM bank_account WHERE buid = '" + id + "'");
			int rowCount = 0;
			if (rs.next()) {
				rowCount += rs.getRow();
				account.setId(rs.getInt("buid"));
				account.setAccountid(rs.getString("accountid"));
				account.setBalance(rs.getDouble("balance"));
				account.setJointAccount(rs.getBoolean("isjoint"));
				conn.close();
			}
			if (rowCount == 1) {
				System.out.println("ACCOUNT FOUND. " + id);
				return account;
			} else {
				System.out.println("ACCOUNT NOT FOUND. " + id);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public Account findAccountrByAccountId(String accountId) throws SQLException {
		try {
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			Account account = new Account();
			ResultSet rs = stmt.executeQuery(
					"SELECT buid, accountid, balance, isjoint FROM bank_account WHERE accountid = '" + accountId + "'");
			int rowCount = 0;
			if (rs.next()) {
				rowCount += rs.getRow();
				account.setId(rs.getInt("buid"));
				account.setAccountid(rs.getString("accountid"));
				account.setBalance(rs.getDouble("balance"));
				account.setJointAccount(rs.getBoolean("isjoint"));
				conn.close();
			}
			if (rowCount == 1) {
				System.out.println("ACCOUNT FOUND. " + accountId);
				return account;
			} else {
				System.out.println("ACCOUNT NOT FOUND. " + accountId);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Account> getAccountList() throws SQLException {
		List<Account> accountList = new ArrayList<>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM bank_account");
		Account account;
		while (rs.next()) {
			account = new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4));
			accountList.add(account);
		}
		conn.close();
		return accountList;
	}

	@Override
	public Customer findAccountCustomerById(int id) throws SQLException {
		try {
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			Customer customer = new Customer();
			ResultSet rs = stmt.executeQuery(
					"SELECT bank_user.buid, bank_user.userid, bank_user.firstname, bank_user.lastname,  bank_user.username, bank_user.userpassword,"
							+ "  bank_user.usertype, bank_customer.approved,     bank_account.accountid, bank_account.balance, bank_account.isjoint "
							+ "FROM bank_user FULL OUTER JOIN bank_customer ON bank_user.buid = bank_customer.buid FULL OUTER JOIN bank_account ON bank_customer.buid = bank_account.buid WHERE bank_customer.buid = '"
							+ id + "'");
			int rowCount = 0;
			if (rs.next()) {
				rowCount += rs.getRow();
				customer.setId(rs.getInt("buid"));
				customer.setUserId(rs.getString("userid"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setUserName(rs.getString("username"));
				customer.setPassword(rs.getString("userpassword"));
				customer.setUserType(rs.getString("usertype"));
				customer.setApproved(rs.getBoolean("approved"));
				conn.close();
			}
			if (rowCount == 1) {
				System.out.println("CUSTOMER FOUND. " + id);
				return customer;
			} else {
				System.out.println("CUSTOMER NOT FOUND. " + id);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public Customer findAccountCustomerByUserId(String userId) throws SQLException {
		try {
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			Customer customer = new Customer();
			ResultSet rs = stmt.executeQuery(
					"SELECT bank_user.buid, bank_user.userid, bank_user.firstname, bank_user.lastname,  bank_user.username, bank_user.userpassword,"
							+ "  bank_user.usertype, bank_customer.approved,     bank_account.accountid, bank_account.balance, bank_account.isjoint "
							+ "FROM bank_user FULL OUTER JOIN bank_customer ON bank_user.buid = bank_customer.buid FULL OUTER JOIN bank_account ON bank_customer.buid = bank_account.buid WHERE bank_user.userid = '"
							+ userId + "'");
			int rowCount = 0;
			if (rs.next()) {
				rowCount += rs.getRow();
				customer.setId(rs.getInt("buid"));
				customer.setUserId(rs.getString("userid"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setUserName(rs.getString("username"));
				customer.setPassword(rs.getString("userpassword"));
				customer.setUserType(rs.getString("usertype"));
				customer.setApproved(rs.getBoolean("approved"));
				conn.close();
			}
			if (rowCount == 1) {
				System.out.println("CUSTOMER FOUND. " + userId);
				return customer;
			} else {
				System.out.println("CUSTOMER NOT FOUND. " + userId);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Customer> getAccountCustomerList() throws SQLException {
		List<Customer> customerList = new ArrayList<>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT bank_user.buid, bank_user.userid, bank_user.firstname, bank_user.lastname,  bank_user.username, bank_user.userpassword,"
						+ "  bank_user.usertype, bank_customer.approved,     bank_account.accountid, bank_account.balance, bank_account.isjoint "
						+ "FROM bank_user FULL OUTER JOIN bank_customer ON bank_user.buid = bank_customer.buid FULL OUTER JOIN bank_account ON bank_customer.buid = bank_account.buid");
		Customer customer = null;
		Account account = null;
		;
		while (rs.next()) {
			customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7),
					account = new Account(rs.getString(9), rs.getDouble(10), rs.getBoolean(11)), rs.getBoolean(8));
			customerList.add(customer);
		}
		conn.close();
		return customerList;
	}

}
