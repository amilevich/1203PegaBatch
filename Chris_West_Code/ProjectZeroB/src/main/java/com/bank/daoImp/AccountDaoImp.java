package com.bank.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bank.main.Account;
import com.bank.main.Bank;
import com.bank.util.Connector;

public class AccountDaoImp {
	public static Connector cr = Connector.getInstance();

	public Account getAccount(int userID) { // get the account information from the account table
		ArrayList<Integer> arr = new ArrayList<>();
		int x = 0, z = 0, count = 0;
		double y = 0;
		String temp = "";

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT account_id, account_balance, account_type,"
					+ "account_open FROM ACCOUNT WHERE USER_ID = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (count == 0) {
					x = rs.getInt(1);
					y = rs.getDouble(2);
					temp = rs.getString(3);
					z = rs.getInt(4);
					arr.add(x);
					count++;
				} else {
					x = rs.getInt(1);
					arr.add(x);
				}

			}
			return new Account(x, y, temp, z, arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Account getAccountByNumber(int accID) { // get the account information from the account table best seen in the account class for its use
		ArrayList<Integer> arr = new ArrayList<>();
		int x = 0, z = 0, count = 0;
		double y = 0;
		String temp = "";

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT account_id, account_balance, account_type,"
					+ "account_open FROM ACCOUNT WHERE account_id = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (count == 0) {
					x = rs.getInt(1);
					y = rs.getDouble(2);
					temp = rs.getString(3);
					z = rs.getInt(4);
					arr.add(x);
					count++;
				} else {
					x = rs.getInt(1);
					arr.add(x);
				}
				String sql2 = "SELECT user_id" + " FROM ACCOUNT WHERE account_id = ?";
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setInt(1, accID);

				ResultSet rs2 = ps2.executeQuery();
				int userID = 0;
				if (rs2.next()) {
					userID = rs2.getInt(1);

				}
				String sql3 = "SELECT account_id" + " FROM ACCOUNT WHERE USER_ID = ? AND account_id != ?";
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.setInt(1, userID);
				ps3.setInt(2, accID);

				ResultSet rs3 = ps3.executeQuery();
				while (rs3.next()) {
					x = rs3.getInt(1);
					arr.add(x);
					count++;
				}

			}
			return new Account(x, y, temp, z, arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean accountExist(int userID) { // does the account exist in the account table

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT WHERE USER_ID = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean accountExist2(int userID) { // does the account exist in the account table

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT WHERE account_id = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean accountExistTransfer(int accID) { // does the account that is transferring to exist

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNT_ID = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Account getAccountTransfer(int accID) { // account2 for transfer purposes
		ArrayList<Integer> arr = new ArrayList<>();
		int x = 0, z = 0, count = 0;
		double y = 0;
		String temp = "";

		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT account_id, account_balance, account_type,"
					+ "account_open FROM ACCOUNT WHERE ACCOUNT_ID = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accID);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (count == 0) {
					x = rs.getInt(1);
					y = rs.getDouble(2);
					temp = rs.getString(3);
					z = rs.getInt(4);
					arr.add(x);
					count++;
				} else {
					x = rs.getInt(1);
					arr.add(x);
				}

			}
			return new Account(x, y, temp, z, arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addNewAccount(int acID, String type) { // create a new account
		try (Connection conn = cr.getConnection()) {
			String sql = "INSERT INTO ACCOUNT VALUES (?,?,?,?,?)";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acID);
			ps.setInt(2, Bank.user1.getId());
			ps.setDouble(3, 0.0);
			ps.setString(4, type);
			ps.setInt(5, 0);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateAccountBalance() { // update the account balances
		try (Connection conn = cr.getConnection()) {
			String sql = "UPDATE ACCOUNT SET ACCOUNT_BALANCE = ? WHERE ACCOUNT_ID = ?";
System.out.println("why: "+Bank.account.getBalance());
			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, Bank.account.getBalance());
			ps.setInt(2, Bank.account.getAccountID());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAccountBalance2() { // update the second account of a different customer
		try (Connection conn = cr.getConnection()) {
			String sql = "UPDATE ACCOUNT SET ACCOUNT_BALANCE = ? WHERE ACCOUNT_ID = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, Bank.account2.getBalance());
			ps.setInt(2, Bank.account2.getAccountID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public void updateAccountOpen() {
//		try (Connection conn = cr.getConnection()) {
//			String sql = "UPDATE ACCOUNT SET ACCOUNT_OPEN = ? WHERE ACCOUNT_ID = ?";
//System.out.println("WHY"+ Bank.account2.getAccountOpen() + " " + Bank.account2.getAccountID() );
//			// Prepared Statement
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, Bank.account2.getAccountOpen());
//			ps.setInt(2, Bank.account2.getAccountID());
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public void addJointAccount(int acID, int userId) { // add a joint account
		try (Connection conn = cr.getConnection()) {
			String sql = "INSERT INTO JOINT VALUES (?,?,?)";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acID);
			ps.setInt(2, userId);
			ps.setInt(3, 0);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeAccount() { // remove an account
		try (Connection conn = cr.getConnection()) {
			String sql = "DELETE FROM ACCOUNT where account_id = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Bank.account.getAccountID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeJointAccount() { // remove a joint account
		try (Connection conn = cr.getConnection()) {
			String sql = "DELETE FROM JOINT where account_id = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Bank.account.getAccountID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> accountsClosed() { // all accounts that haven't been approved

		ArrayList<Integer> arr = new ArrayList<Integer>();
		try (Connection conn = cr.getConnection()) {
			String sql = "SELECT ACCOUNT_ID FROM ACCOUNT WHERE ACCOUNT_OPEN = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				arr.add(rs.getInt(1));
			}
			return arr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateAccountOpen(int accID) { // update the account balances
		try (Connection conn = cr.getConnection()) {
			String sql = "UPDATE ACCOUNT SET ACCOUNT_Open = ? WHERE ACCOUNT_ID = ?";

			// Prepared Statement
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2,accID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	public Account getJointAccount(int userID) {
//
//		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//			String sql = "SELECT * FROM ACCOUNT WHERE USER_ID = ?";
//
//			// Prepared Statement
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, userID);
//
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//
//				return new Account(rs.getDouble(1), rs.getString(2), rs.getInt(3));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
