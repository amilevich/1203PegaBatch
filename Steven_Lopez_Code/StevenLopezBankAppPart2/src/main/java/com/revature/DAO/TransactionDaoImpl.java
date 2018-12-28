package com.revature.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.enums.Transaction_Type;
import com.revature.models.Transaction;
import com.revature.util.ConnFactory;

public class TransactionDaoImpl implements TransactionDao {

	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public List<Transaction> getAllAccountTransactions(int id) {
		List<Transaction> transList = new ArrayList<>();
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM bank_transaction WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				transList.add(new Transaction(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4),
						(Transaction_Type.valueOf(rs.getString(5))), rs.getTimestamp(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transList;
	}

	public Transaction getTransactionById(int id) {
		Transaction trans = null;
		try (Connection conn = cf.getConnection();) {

			String sql = "SELECT * FROM bank_transaction WHERE trans_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				trans = new Transaction(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4),
						Transaction_Type.valueOf(rs.getString(5)), rs.getTimestamp(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trans;
	}

	public List<Transaction> getAllTransactions() {
		List<Transaction> transList = new ArrayList<>();
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM bank_transaction";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				transList.add(new Transaction(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4),
						(Transaction_Type.valueOf(rs.getString(5))), rs.getTimestamp(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transList;
	}

	public int createTransaction(Transaction transaction) {
		int rowAffected = 0;
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);

			String sql = "BEGIN INSERT INTO bank_transaction VALUES(null, ?, ?, ?, ?, ?) RETURNING trans_id INTO ?; END;";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, transaction.getAccount());
			cs.setInt(2, transaction.getUser());
			cs.setDouble(3, transaction.getAmount());
			cs.setString(4, transaction.getType().name());
			cs.setTimestamp(5, transaction.getTimestamp());
			cs.registerOutParameter(6, Types.NUMERIC);
			cs.executeUpdate();
			rowAffected = cs.getInt(6);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowAffected;
	}

	public boolean updateTransaction(Transaction transaction) {
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "UPDATE bank_transaction SET amount = ?, trans_type = ?, trans_date = ? WHERE trans_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setDouble(1, transaction.getAmount());
			ps.setString(2, transaction.getType().name());
			ps.setTimestamp(3, transaction.getTimestamp());
			ps.setInt(4, transaction.getId());
			ps.executeQuery();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int deleteTransaction(int id) {
		int rowAffected = 0;
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM bank_transaction WHERE trans_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rowAffected = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowAffected;
	}
	
	public int deleteTransactionByAccountId(int id) {
		int rowAffected = 0;
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM bank_transaction WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rowAffected = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowAffected;
	}
	
	public int deleteTransactionByUserId(int id) {
		int rowAffected = 0;
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM bank_transaction WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rowAffected = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowAffected;
	}
	

	public static void main(String[] args) {
		// List<Transaction> transL = new TransactionDaoImpl().getAllTransactions();
		// transL.forEach(trn-> System.out.println(trn.toString()));
		// int tran = new TransactionDaoImpl().createTransaction(new
		// Transaction(2473,0,100.00,Transaction_Type.DEPOSIT,new
		// Timestamp(System.currentTimeMillis())));
		// Transaction new_trans = new TransactionDaoImpl().getTransactionById(tran);
		//
		// System.out.println((new TransactionDaoImpl().updateTransaction(
		// new Transaction(new_trans.getId(), new_trans.getAccount(),
		// new_trans.getUser(), new_trans.getAmount(), new_trans.getType(), new
		// Timestamp(System.currentTimeMillis())))));
		//
		// System.out.println(new TransactionDaoImpl().getTransactionById(tran));
		//
		// System.out.println(new
		// TransactionDaoImpl().deleteTransaction(new_trans.getId()));
		//
		// System.out.println(new
		// TransactionDaoImpl().getTransactionById(new_trans.getId()));
	}

}
