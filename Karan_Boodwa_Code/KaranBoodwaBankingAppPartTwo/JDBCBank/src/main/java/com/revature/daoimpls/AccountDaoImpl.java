package com.revature.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.daos.AccountDao;
import com.revature.pojos.Account;
import com.revature.util.ConnFactory;

public class AccountDaoImpl implements AccountDao {
	
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean createAccount(int accNum) {
		/*
		 * initial value in a given account is $0.00, this can be adjusted by
		 * changebalance() below
		 */
		try (Connection conn = cf.getConnection()) {
			String sql = "INSERT INTO bankacc VALUES(? , ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accNum);
			ps.setDouble(2, 0.0);
			ps.executeQuery();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean changeBalance(int accNum, double amount) {
		try (Connection conn = cf.getConnection()) {
			String sql = "UPDATE bankacc SET balance=balance+? WHERE acc_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, accNum);
			ps.executeQuery();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account getAccountById(int accId) {
		try (Connection conn = cf.getConnection()) {
			// NEED TO PACKAGE: accountnumber, balance, holders( list of usernames)
			// SELECTing from the convenience view created from the junction table:
			String sql = "SELECT username, balance FROM USER_BANK_ACCS WHERE account=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accId);
			ResultSet rs = ps.executeQuery();
			// Packaging up resultset into a single account object
			Account account = new Account();
			account.setAccountNumber(accId);
			List<String> holders = new ArrayList<>();
			while (rs.next()) {
				account.setBalance(rs.getDouble(2));
				holders.add(rs.getString(1));
			}
			if (holders.isEmpty()) {
				return null;
			} else {
				account.setAccountHolders(holders);
				return account;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Integer> getAccountsByUsername(String username) {
		try (Connection conn = cf.getConnection()) {
			// NEED TO PACKAGE: accountnumber, balance, holders( list of usernames)
			// SELECTing from the convenience view created from the junction table:
			String sql = "SELECT account FROM USER_BANK_ACCS WHERE username =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();

			List<Integer> accounts = new ArrayList<>();

			while (rs.next()) {
				accounts.add(rs.getInt(1));
			}
			if (accounts.isEmpty()) {
				return null;
			} else {
				return accounts;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean deleteAccount(int accId) {
		try(Connection conn = cf.getConnection()){
			// TODO turn this into a stored procedure
			String sql1 = "DELETE FROM useracc_bankacc WHERE acc_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setInt(1, accId);
			String sql2 = "DELETE FROM bankacc WHERE acc_id = ?";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setInt(1, accId);
			
			conn.setAutoCommit(false);
			ps.executeUpdate();
			ps2.executeUpdate();
			conn.commit();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static void main(String[] args) {
		System.out.println( new AccountDaoImpl().deleteAccount(5551234));
	}

}
