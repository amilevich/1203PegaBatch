import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;



public class BankUserDaoImpl implements BankUserDao {
private static Connection conn;
	public BankUserDaoImpl(Connection conn) {
		this.conn = conn;
	}
	@Override
	public BankUser insertUser(BankUser user, boolean commit) {
		String username = user.getUsername();
		String firstName = user.getFirstname();
		String lastName = user.getLastname();
		String pw = user.getPassword();
		try {
			String sqll = "BEGIN INSERT INTO bank_user P (id,username,pw,firstname,lastname,account_type) VALUES(NULL,?,?,?,?,?) RETURNING P.id INTO ?;  END;";
			CallableStatement cs = conn.prepareCall(sqll);
			
			//PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			cs.setString(1, username);
			cs.setString(2, pw);
			cs.setString(3, firstName);
			cs.setString(4, lastName);
			cs.setInt(5, user.getAccountType());
			cs.registerOutParameter(6, OracleTypes.NUMBER);
			cs.execute();
			int id = cs.getInt(6);
			if(commit) {
				conn.commit();
			}
			if(id>=1) {
				user.setId(id);
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(BankUser user, boolean commit) {
		try  {
			String sql = "DELETE FROM bank_user where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			if(commit) {
				conn.commit();
			}
			if(rs.next()) {
				return true;
			}
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String username, boolean commit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getUserID(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BankUser updateUser(BankUser user, boolean commit) {
		try  {
			String sql = "UPDATE bank_user SET username = ?,firstname = ?,lastname = ?,pw = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getLastname());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getId());
			ResultSet rs = ps.executeQuery();
			if(commit) {
				conn.commit();
			}
			if(rs.next()) {
				return user;
			}
			
			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankUser getUser(String username) {
		try {
			String sql = "SELECT id,firstname, lastname,account_type,pw FROM bank_user WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//System.out.println("Got here");
				BankUser user = new BankUser();
			//	System.out.println(rs.getInt(1));
				user.setUsername(username);
				user.setId(rs.getInt(1));
				user.setFirstname(rs.getString(2));
				user.setLastname(rs.getString(3));
				user.setAccountType(rs.getInt(4));
				user.setPassword(rs.getString(5));
			
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankUser getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean userExists(String username) {
		try {
			String sql = "SELECT username FROM bank_user where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<BankUser> getAllUsers() {
		ArrayList<BankUser> users = new ArrayList<BankUser>();
		try {
			String sql = "SELECT id, firstname, lastname,account_type,username FROM bank_user";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//System.out.println("Got here");
				String recpw = rs.getString(2);
				BankUser user = new BankUser();
				user.setAccountType(rs.getInt(4));
				user.setFirstname(rs.getString(2));
				user.setId(rs.getInt(1));
				user.setLastname(rs.getString(3));
				user.setUsername(rs.getString(5));
				users.add(user);
			//	System.out.println(rs.getInt(1));
			
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankUser validateUser(BankUser user) {
		String username = user.getUsername();
		String pw = user.getPassword();
		try {
			String sql = "SELECT id, pw,firstname, lastname,account_type FROM bank_user WHERE username = ? and pw = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			

			if (rs.next()) {
				String recpw = rs.getString(2);
				
				user.setId(rs.getInt(1));
				user.setFirstname(rs.getString(3));
				user.setLastname(rs.getString(4));
				user.setAccountType(rs.getInt(5));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public ArrayList<BankUser> getAccountUsers(BankAccount acc) {
		ArrayList<BankUser> users = new ArrayList<BankUser>();
		try {
			String sql = "SELECT id, firstname, lastname,account_type,username FROM bank_user INNER JOIN account_user ON bank_user.id = account_user.user_id WHERE account_user.account_number = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acc.getAccountNumber());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				//System.out.println("Got here");
				String recpw = rs.getString(2);
				BankUser user = new BankUser();
				user.setAccountType(rs.getInt(4));
				user.setFirstname(rs.getString(2));
				user.setId(rs.getInt(1));
				user.setLastname(rs.getString(3));
				user.setUsername(rs.getString(5));
				users.add(user);
			//	System.out.println(rs.getInt(1));
			
			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}


}
