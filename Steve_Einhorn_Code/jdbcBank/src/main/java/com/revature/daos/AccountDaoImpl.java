package com.revature.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.CustomerAccount;
import com.revature.models.OpenApplication;

public class AccountDaoImpl {
	
	private static Connection getConnection() {
		
		// much more secure as environment variables
		String user = "bank_db";  //System.getenv("jdbc_user")
		String password = "p4ssw0rd";
		String url = "jdbc:oracle:thin:@octocatdb.cowlaewb2yhg.us-east-2.rds.amazonaws.com:1521:ORCL";	

		try {
			return DriverManager.getConnection(url, user,  password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Account getAccount(int accountNbr) {
		
		// Connection is a major interface of JDBC
		// Connection is used to access a variety of other interfaces
		Connection conn = getConnection();
		
		Account account = null;
		
		String query = "SELECT account_nbr, username, account_type, account_balance FROM accounts " +
				   		"WHERE account_nbr = ?";
					   
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, accountNbr);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setAccountNbr(rs.getInt(1));
				account.setUserName(rs.getString(2));
				account.setAcctType(rs.getString(3));
				account.setAccountBal(rs.getDouble(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	public Account getCustomerAccount(int accountNbr, String userName) {
		
		// Connection is a major interface of JDBC
		// Connection is used to access a variety of other interfaces
		Connection conn = getConnection();
		
		Account account = null;
		
		String query = "SELECT account_nbr, username, account_type, account_balance FROM accounts " +
				   		"WHERE account_nbr = ? " +
				   		"  AND username = ?";
					   
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, accountNbr);
			ps.setString(2, userName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setAccountNbr(rs.getInt(1));
				account.setUserName(rs.getString(2));
				account.setAcctType(rs.getString(3));
				account.setAccountBal(rs.getDouble(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
		
	}
		
		public List<CustomerAccount> getCustomerAccounts() {
			
			// Connection is a major interface of JDBC
			// Connection is used to access a variety of other interfaces
			Connection conn = getConnection();
			List<CustomerAccount> customerAccounts = new ArrayList<>();
			
			CustomerAccount customerAccount = null;
			
			String query = "SELECT c.username, c.password, " +
								  "a.account_nbr, a.account_type, a.account_balance " + 
							 "FROM customers c " +
					   		"INNER JOIN accounts a " +
					   		"   ON c.username = a.username";
						   
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					customerAccount = new CustomerAccount();
					customerAccount.setUserName(rs.getString(1));
					customerAccount.setPassword(rs.getString(2));
					customerAccount.setAccountNbr(rs.getInt(3));
					customerAccount.setAcctType(rs.getString(4));
					customerAccount.setAccountBal(rs.getDouble(5));
					customerAccounts.add(customerAccount);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return customerAccounts;
	}
	
	public void saveAccount(OpenApplication openAppl) {
		
		// Connection is a major interface of JDBC
		// Connection is used to access a variety of other interfaces
		Connection conn = getConnection();
		
		String query = "INSERT INTO ACCOUNTS (ACCOUNT_NBR, USERNAME, ACCOUNT_TYPE, ACCOUNT_BALANCE) " +
				   		"VALUES (SEQ_ACCOUNTS.NEXTVAL, ?, ?, ?)";
					   
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, openAppl.getUsername());
			ps.setString(2, openAppl.getAcctType());
			ps.setDouble(3, openAppl.getInitDeposit());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateAccountBalance(Account account) {
		
		// Connection is a major interface of JDBC
		// Connection is used to access a variety of other interfaces
		Connection conn = getConnection();
		
		String query = "UPDATE ACCOUNTS " +
					   "   SET account_balance = ? " +
				   	   " WHERE account_nbr = ?";
					   
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setDouble(1, account.getAccountBal());
			ps.setInt(2, account.getAccountNbr());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
