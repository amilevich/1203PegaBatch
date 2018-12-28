package com.revature.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.enums.Account_State;
import com.revature.models.Account;
import com.revature.util.ConnFactory;

public class AccountDaoImpl implements AccountDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	public List<Account> getAccountsByUserId(int id){
		ArrayList<Account> accList = new ArrayList<>();
			try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM bank_account a JOIN account_user l ON a.account_id = l.account_id AND user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {

				accList.add(new Account(rs.getInt(1), rs.getDouble(2), (Account_State.valueOf(rs.getString(3))),rs.getInt(4),(Integer)rs.getInt(5),rs.getDate(6) == null ? null : rs.getDate(6).toLocalDate()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return accList;
	}
	
	public Account getAccountById(int id) {
		Account acc = null;
		try (Connection conn = cf.getConnection();) {
			
			String sql = "SELECT * FROM bank_account WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				acc = new Account(rs.getInt(1), rs.getDouble(2), (Account_State.valueOf(rs.getString(3))),rs.getInt(4),(Integer)rs.getInt(5), rs.getDate(6) == null ? null : rs.getDate(6).toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acc;
	}

	public List<Account> getAllAccounts() {
		List<Account> accList = new ArrayList<>();
		try(Connection conn = cf.getConnection();){
			String sql = "SELECT * FROM bank_account";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {

				accList.add(new Account(rs.getInt(1), rs.getDouble(2), (Account_State.valueOf(rs.getString(3))),rs.getInt(4),(Integer)rs.getInt(5),rs.getDate(6) == null ? null : rs.getDate(6).toLocalDate()));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return accList;
	}

	public void createAccountConnection(int account, int user) {
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO account_user VALUES(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user);
			ps.setInt(2, account);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int createAccount(Account account, int user) {
		int rowAffected = 0;
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);

			String sql = "BEGIN INSERT INTO bank_account VALUES(null, ?, 'PENDING', ?, null, null) RETURNING account_id INTO ?; END;";
			CallableStatement cs = conn.prepareCall(sql);			
			cs.setDouble(1, account.getBalance());
			cs.setInt(2, account.getJoint());
			cs.registerOutParameter(3, Types.NUMERIC);
			cs.executeUpdate();
			rowAffected = cs.getInt(3);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		createAccountConnection(rowAffected, user);
		
		return rowAffected;
	}

	public boolean updateAccount(Account account) {
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "UPDATE bank_account SET balance = ?, account_state = ?, approved_by = ?, approved_date = ? WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, account.getBalance());
			ps.setString(2, account.getState().name());
			ps.setInt(3, account.getApprovedBy());
			if(account.getApprovedDate()==null){
				ps.setString(4, "");
			}
			else
				ps.setDate(4, Date.valueOf(account.getApprovedDate()));
			ps.setInt(5, account.getId());
			ps.executeQuery();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int deleteAccount(int id, int user) {
		int rowAffected = 0;
		
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM account_user WHERE account_id = ? AND user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, user);
			rowAffected = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM bank_account WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rowAffected = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowAffected;
	}

}
