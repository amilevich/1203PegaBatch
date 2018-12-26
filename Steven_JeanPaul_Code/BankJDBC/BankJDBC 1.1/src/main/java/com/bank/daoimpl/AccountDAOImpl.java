package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Comparator;
import java.util.List;
import com.bank.dao.AccountDAO;
import com.bank.util.*;
import com.bank.beans.Account;

//import com.bank.util.ConnFactory;

public class AccountDAOImpl implements AccountDAO {

	public static ConnFactory cf = ConnFactory.getInstance();

	// @Override
	public void deposit(int accountid, String timestmp, String acctype, int activation, int deposit)
			throws SQLException {
		Connection conn = cf.getConnection();
		// int lastBal = getLastBalance(accountid);
		String sql = "INSERT INTO ACCOUNT VALUES(BANKSEQ.NEXTVAL, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "28MAY10");
		ps.setString(2, "Savings"); // 3
		ps.setInt(3, 0);
		ps.setInt(4, deposit);
		// ps.setInt(4, (deposit + lastBal));
		ps.executeUpdate();
		/* ACCOUNT (accountid, createddate, accounttype, activated, balance); */
	}

	public int getLastBalance(int accID) throws SQLException {
		List<Account> accountList = new ArrayList<Account>();
		int lastBalance = 0;
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT WHERE ACCOUNTID = " + accID); // getting superhero
																								// objects from the
																								// database. store them
																								// in arraylist.
		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)); // id, name
			// a = new Account(rs.getInt(1), rs.getString(2), "14JUN18", 1, 222); //id, name
			accountList.add(a);
			System.out.println("Acc #" + rs.getInt(1) + " balance is $" + rs.getInt(5));
			;
			lastBalance = rs.getInt(5);
		}

		conn.close();
		return lastBalance;
	}

	public List<Account> getFullAccountList() throws SQLException { // array list that holds super hero objects
		List<Account> accountList = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT"); // getting superhero objects from the database. store
																	// them in arraylist.
		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)); // id, name
			// a = new Account(rs.getInt(1), rs.getString(2), "14JUN18", 1, 222); //id, name
			accountList.add(a);
			System.out.println("\nAccID #" + a.getAccountID() + " was created on " + a.getCreatedDate() + ". as a "
					+ a.getAccountType() + " account. Account status is: " + a.isActivated());
			// accountid, String timestmp, String acctype, int activation, int deposit
		}
		conn.close();
		return accountList;
	}

	public List<Account> getAllAccountIds() throws SQLException { // array list that holds super hero objects
		List<Account> accountList = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ACCOUNTID FROM ACCOUNT"); // getting superhero objects from the
																			// database. store them in arraylist.
		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1)); // id, name
			// a = new Account(rs.getInt(1), rs.getString(2), "14JUN18", 1, 222); //id, name
			accountList.add(a);
			System.out.println("\nAccID #" + a.getAccountID());
			// accountid, String timestmp, String acctype, int activation, int deposit
		}
		conn.close();
		return accountList;
	}

	public List<Account> getAllAccountCreationDates() throws SQLException { // array list that holds super hero objects
		List<Account> accountList = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT"); // getting superhero objects from the database. store
																	// them in arraylist.
		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)); // id, name
			// a = new Account(rs.getInt(1), rs.getString(2), "14JUN18", 1, 222); //id, name
			accountList.add(a);
			System.out.print("\n AccID #" + a.getAccountID() + " was created on " + a.getCreatedDate().substring(0, 9));
			// accountid, String timestmp, String acctype, int activation, int deposit
		}
		conn.close();
		return accountList;
	}

	public List<Account> getActiveAccounts() throws SQLException {
		List<Account> accountList = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		int aCountInProcess = 0;
		ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT"); // getting superhero objects from the database. store
																	// them in arraylist.
		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)); // id, name
			// a = new Account(rs.getInt(1), rs.getString(2), "14JUN18", 1, 222); //id, name
			accountList.add(a);

			if (a.isActivated() == 1) {
				System.out.print("\n AccID #" + a.getAccountID() + " created on " + a.getCreatedDate().substring(0, 9)
						+ " is active.");
				// accountid, String timestmp, String acctype, int activation, int deposit
			} else if (a.isActivated() == 0) {
				System.out.print("\n AccID #" + a.getAccountID() + " created on " + a.getCreatedDate().substring(0, 9)
						+ " is inactive.");
			} else {
				aCountInProcess++;
				System.out.println("There are " + aCountInProcess + " accounts in process.");
			}
		}
		conn.close();
		return accountList;
	}

	public List<Account> getAllAccountBalances() throws SQLException {
		List<Account> accountList = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT"); // getting superhero objects from the database. store
																	// them in arraylist.
		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)); // id, name
			// a = new Account(rs.getInt(1), rs.getString(2), "14JUN18", 1, 222); //id, name
			accountList.add(a);
			System.out.println("\n Account #" + rs.getInt(1) + " has a balance of $" + rs.getInt(5) + ".");
		}
		conn.close();
		return accountList;
	}

	public List<Account> getAllAccountTypes() throws SQLException {
		List<Account> accountList = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT"); // getting superhero objects from the database. store
																	// them in arraylist.
		Account a = null;
		while (rs.next()) {
			a = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)); // id, name
			// a = new Account(rs.getInt(1), rs.getString(2), "14JUN18", 1, 222); //id, name
			accountList.add(a);
			System.out.println("\n Account #" + rs.getInt(1) + " is a " + rs.getString(3) + " account.");
		}
		conn.close();
		return accountList;
	}

	/*
	 * public void withdraw(int accountid, String timestmp, String acctype, int
	 * activation, int withdraw) throws SQLException{ Connection conn =
	 * cf.getConnection(); String sql =
	 * "SELECT BALANCE FROM ACCOUNT(BANKSEQ.NEXTVAL, ?, ?, ?, ?)"; PreparedStatement
	 * ps = conn.prepareStatement(sql); ps.setString(1, "12MAR10"); ps.setString(2,
	 * "Savings"); //3 ps.setInt(3, 1); ps.setInt(4, withdraw); ps.executeUpdate();
	 * }
	 */

	/*
	 * public void createAccount(String accountid) throws SQLException { Connection
	 * conn = cf.getConnection(); String sql = "{ call INSERTACCOUNT(?)";
	 * CallableStatement call = conn.prepareCall(sql); call.setString(1, accountid);
	 * call.execute(); String sql1 =
	 * "SELECT ACCOUNTID FROM ACCOUNT WHERE ACCOUNTID = ?"; PreparedStatement ps =
	 * conn.prepareStatement(sql1); ps.setString(1, accountid); //ResultSet rs =
	 * ps.executeQuery(); //while(rs.next()) {
	 * //System.out.println("Id of this superhero is : " + rs.getInt(1)); //} }
	 */

	public int withdraw(int w) {
		return 0; // this will return the result to the calling method where ever
	}

	public int transfer(int accountid, int transferamount) { // a regular expression for input here would be nice
		return 0;
	}

}