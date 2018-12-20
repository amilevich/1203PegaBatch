package project.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import project.account.Account;
import project.dao.AccountDAO;
import project.util.ConnFactory;

public class AccountDAOImpl implements AccountDAO{
	public static ConnFactory cf = ConnFactory.getInstance();
	@Override
	public void createAccount(int num, String type) throws SQLException{
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO account_db(acc_num, acc_type) VALUES(?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, num);
		ps.setString(2, type);
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public ArrayList<Account> getAccounts() throws SQLException{
		ArrayList<Account> accountList = new ArrayList<>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM account_db ORDER BY(acc_num)");
		Account s = null;
		while (rs.next()) {
			s = new Account(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getBoolean(4));
			accountList.add(s);
			//System.out.println(s);
		}
		conn.close();
		return accountList;
	}
	
	@Override
	public void setBalance(int account, double amount) throws SQLException{
		Connection conn = cf.getConnection();
		String sql = "UPDATE account_db SET balance = ? WHERE acc_num = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, amount);
		ps.setInt(2, account);
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void deleteAccount(int account) throws SQLException{
		Connection conn = cf.getConnection();
		String sql = "DELETE FROM customer_acc_db WHERE acc_num = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, account);
		ps.executeUpdate();
		sql = "DELETE FROM account_db WHERE acc_num = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, account);
		ps.executeUpdate();
		conn.close();
		
	}

	@Override
	public void activatedAcc(int account) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "UPDATE account_db SET activated = 'true' WHERE acc_num = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, account);
		ps.executeUpdate();
		conn.close();
	}

}
