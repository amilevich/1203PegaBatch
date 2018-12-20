import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class BankAccountDaoImpl implements BankAccountDao {
	private static Connection conn;
	public BankAccountDaoImpl(Connection conn) {
		this.conn = conn;
	}
	@Override
	public BankAccount insertAccount(BankAccount acc, boolean commit) {

		try  {
			String sqll = "BEGIN INSERT INTO bank_account P (account_number,balance) VALUES(NULL,?) RETURNING P.account_number INTO ?;  END;";
			CallableStatement cs = conn.prepareCall(sqll);
			
			cs.setInt(1,0);
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.execute();
			int id = cs.getInt(2);
			
			
			if(commit) {
				conn.commit();
			}
			if(id>=1) {
				acc.setAccountNumber(id);
				return acc;
			} 
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount updateAccount(BankAccount acc, boolean commit) {
		try  {
			String sql = "UPDATE bank_account SET balance = ? WHERE account_number = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, acc.getBalance());
			ps.setInt(2,acc.getAccountNumber());
			ResultSet rs = ps.executeQuery();
			if(commit) {
				conn.commit();
			}
			if(rs.next()) {
				return acc;
			}
			
			return acc;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAccount(int accountNum, boolean commit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount(BankAccount account, boolean commit) {
		int accID = account.getAccountNumber();
		try {
			String sql = "DELETE FROM bank_account WHERE account_number = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,accID);
			ResultSet rs = ps.executeQuery();
			if(commit) {
				conn.commit();
			}
			return true; //CHECK IF WORKS???????
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<BankAccount> getUserAccounts(BankUser user) {
		ArrayList<BankAccount> accs = new ArrayList<BankAccount>();
		int userID = user.getId();
		try {
			String sql = "SELECT a.account_number, balance FROM account_user a INNER JOIN bank_account b ON a.account_number = b.account_number WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				accs.add(new BankAccount(rs.getInt(1),rs.getDouble(2)));
			}
			return accs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BankAccount> getUserAccounts(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BankAccount> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount getAccount(int accountNum) {
		try {
			String sql = "SELECT a.account_number, b.balance FROM account_user a INNER JOIN bank_account b ON a.account_number = b.account_number WHERE a.account_number = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,accountNum);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return (new BankAccount(rs.getInt(1),rs.getDouble(2)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount getAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount getAccount(int accountNum, int userID) {
		try {
			String sql = "SELECT a.account_number, b.balance FROM account_user a INNER JOIN bank_account b ON a.account_number = b.account_number WHERE a.user_id = ? and a.account_number = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setInt(2,accountNum);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return (new BankAccount(rs.getInt(1),rs.getDouble(2)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount getAccount(BankAccount account, BankUser user) {
		int userID = user.getId();
		int accID = account.getAccountNumber();
		try {
			String sql = "SELECT a.account_number, b.balance FROM account_user a INNER JOIN bank_account b ON a.account_number = b.account_number WHERE a.user_id = ? and a.account_number = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setInt(2,accID);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return (new BankAccount(rs.getInt(1),rs.getDouble(2)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean deleteOrphanAccounts(boolean commit) {
		try {
//			String sql = "DELETE FROM bank_account WHERE account_number NOT IN(SELECT account_number FROM account_user)";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			if(commit) {
//				conn.commit();
//			}
//			return true; //CHECK IF WORKS???????
			CallableStatement cStmt = conn.prepareCall("{call kill_orphans()}");
			cStmt.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}



}
