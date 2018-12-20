package project.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import project.account.Account;
import project.dao.CustomerAccDAO;
import project.users.Admin;
import project.users.Customer;
import project.users.Employee;
import project.users.User;
import project.util.ConnFactory;

public class CustomerAccDAOImpl implements CustomerAccDAO {
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public void createPair(String username, int acc_num) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO customer_acc_db(username, acc_num) VALUES(?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setInt(2, acc_num);
		ps.executeUpdate();
		conn.close();
	}

	public ArrayList<Account> getAccounts(String username) throws SQLException{
		ArrayList<Account> accList = new ArrayList<>();
		Connection conn = cf.getConnection();
		String sql = "SELECT account_db.acc_num, balance, acc_type, activated FROM account_db " + 
				"INNER JOIN customer_acc_db ON account_db.acc_num = customer_acc_db.acc_num " + 
				"INNER JOIN user_db ON user_db.username = customer_acc_db.username "
				+ "WHERE user_db.username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		Account acc = null;
		//System.out.println(username);
		while (rs.next()) {
			acc = new Account(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getBoolean(4));
			accList.add(acc);
			//System.out.println(acc);
		}
		return accList;
	}
}
