import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class OwnershipDaoImpl implements OwnershipDao {
	private static Connection conn;
	public OwnershipDaoImpl(Connection conn) {
		this.conn = conn;
	}
	@Override
	public Ownership insertConnection(BankAccount acc, BankUser user, boolean commit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ownership insertConnection(Ownership link, boolean commit) {
		int userID = link.getUserID();
		int accNum = link.getAccountNumber();
		try  {
			String sql = "INSERT INTO account_user (account_number,user_id) VALUES(?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, accNum);
			ps.setInt(2, userID);

			ResultSet rs = ps.executeQuery();
			if(commit) {
				conn.commit();
			}
			if(rs.next()) {
				return link;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ownership> getAccountOwners(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ownership> getAccountOwners(BankAccount acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ownership> getOwnersAccounts(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ownership> getOwnersAccounts(BankUser user) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRelationship(Ownership relationship, boolean commit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRelationships(BankAccount acc, boolean commit) {
		int accID = acc.getAccountNumber();
		try {
			String sql = "DELETE FROM account_user WHERE account_number = ?";
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
	public boolean deleteRelationships(BankUser user, boolean commit) {
		int userId = user.getId();
		try {
			String sql = "DELETE FROM account_user WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,userId);
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

}
