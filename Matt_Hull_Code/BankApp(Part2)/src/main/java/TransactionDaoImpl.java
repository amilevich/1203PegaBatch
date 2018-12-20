import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;
/*
 * the class deals with the interaction with the table that records all transactions of accounts (Deposits, withdraws, etc)
 * Maps to that table
 */ 
public class TransactionDaoImpl implements TransactionDao {
	private static Connection conn;

	public TransactionDaoImpl(Connection conn) {
		this.conn = conn;
	}
//gets all transactions initiated by that user
	@Override
	public ArrayList<Transaction> getUserTransactions(BankUser user) {

		ArrayList<Transaction> trans = new ArrayList<Transaction>();
		int userID = user.getId();
		try {
			String sql = "SELECT firstname, lastname, account_number, amount,resulting_balance,transaction_time FROM bank_account a INNER JOIN x_action b ON a.account_number = b.acc_number INNER JOIN bank_user c ON c.id =b.acc_user where acc_user=? ORDER BY transaction_time DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Transaction newTrans = new Transaction();
				newTrans.setAccountNumber(rs.getInt(3));
				newTrans.setAmount(rs.getDouble(4));
				newTrans.setResultingBalance(rs.getDouble(5));
				newTrans.setTime(rs.getTimestamp(6));
				newTrans.setUsersFullName(rs.getString(1), rs.getString(2));
				trans.add(newTrans);
			}
			return trans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		// TODO Auto-generated method stub
		return null;
	}
//gets all transactions associated with this account
	@Override
	public ArrayList<Transaction> getAccountTransactions(BankAccount account) {
		ArrayList<Transaction> trans = new ArrayList<Transaction>();
		int accNum = account.getAccountNumber();
		try {
			String sql = "SELECT firstname, lastname, account_number, amount,resulting_balance,transaction_time FROM bank_account a INNER JOIN x_action b ON a.account_number = b.acc_number INNER JOIN bank_user c ON c.id =b.acc_user where account_number=? ORDER BY transaction_time DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accNum);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Transaction newTrans = new Transaction();
				newTrans.setAccountNumber(rs.getInt(3));
				newTrans.setAmount(rs.getDouble(4));
				newTrans.setResultingBalance(rs.getDouble(5));
				newTrans.setTime(rs.getTimestamp(6));
				newTrans.setUsersFullName(rs.getString(1), rs.getString(2));
				trans.add(newTrans);
			}
			return trans;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		// TODO Auto-generated method stub
		return null;
	}
//not yet implemented
	@Override
	public ArrayList<Transaction> getAccountTransactions(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}
//inserts transaction into x_action table
	@Override
	public Transaction insertTransaction(Transaction trans, boolean commit) {
		/*
		 * 
		 */
		try {
			PreparedStatement ps;

			String sql = "INSERT INTO x_action (id,acc_number, acc_user, amount,transaction_time,resulting_balance) VALUES(null,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);

			// String query = "BEGIN INSERT INTO team (name) values (?) returning id into ?;
			// END;";
			ps.setInt(1, trans.getAccountNumber());
			ps.setInt(2, trans.getUserID());
			ps.setDouble(3, trans.getAmount());
			// ps.setTimestamp(4, trans.getTime());
			ps.setTimestamp(4, trans.getTime());
			ps.setDouble(5, trans.getResultingBalance());
			ps.executeUpdate();
			if (commit) {
				conn.commit();
			}
			return trans;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
//not yet implemented
	@Override
	public boolean deleteTransaction(Transaction trans, boolean commit) {
		// TODO Auto-generated method stub
		return false;
	}
//not yet implemented
	@Override
	public boolean deleteTransactions(int id, boolean commit) {
		// TODO Auto-generated method stub
		return false;
	}
//deletes transaction relating to this acc
	@Override
	public boolean deleteTransactions(BankAccount acc, boolean commit) {
		int accID = acc.getAccountNumber();
		try {
			String sql = "DELETE FROM x_action  WHERE acc_number = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accID);
			ResultSet rs = ps.executeQuery();
			if (commit) {
				conn.commit();
			}
			return true; // CHECK IF WORKS???????

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
//deletes transactions relating to this user
	@Override
	public boolean deleteTransactions(BankUser user, boolean commit) {
		try {
			String sql = "DELETE FROM x_action where acc_user = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			if (commit) {
				conn.commit();
			}
			return true; // CHECK IF WORKS???????

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
// deletes all transactions relating to an account that has no relationships
	@Override
	public boolean deleteOrphanAccountTransactions(boolean commit) {
		try {
			String sql = "DELETE FROM x_action where acc_number in(SELECT account_number from bank_account WHERE account_number NOT IN(SELECT account_number FROM account_user))";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (commit) {
				conn.commit();
			}
			return true; // CHECK IF WORKS???????

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

}
