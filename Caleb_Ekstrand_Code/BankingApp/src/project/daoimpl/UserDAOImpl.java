package project.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import project.account.Account;
import project.dao.UserDAO;
import project.users.Admin;
import project.users.Customer;
import project.users.Employee;
import project.users.User;
import project.util.ConnFactory;

public class UserDAOImpl implements UserDAO {
	public static ConnFactory cf = ConnFactory.getInstance();
	public static CustomerAccDAOImpl cadi = new CustomerAccDAOImpl();

	@Override
	public void createCustomer(String username, String password, String name, String address, String phone)
			throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO user_db(username, pass, name, address, phone, type) VALUES(?, ?, ?, ?, ?, 'customer')";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, name);
		ps.setString(4, address);
		ps.setString(5, phone);
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void createEmployee(String username, String password, String name) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO user_db(username, pass, name, type) VALUES(?, ?, ?, 'employee')";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, name);
		ps.executeUpdate();
		conn.close();
	}

	@Override
	public void createAdmin(String username, String password, String name) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO user_db(username, pass, name, type) VALUES(?, ?, ?, 'admin')";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, name);
		ps.executeUpdate();
		conn.close();
		
	}

	@Override
	public ArrayList<String> getUsernames() throws SQLException {
		ArrayList<String> usernameList = new ArrayList<>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT username FROM user_db");
		String s = null;
		while (rs.next()) {
			s = new String(rs.getString(1));
			usernameList.add(s);
		}
		conn.close();
		return usernameList;
	}

	@Override
	public ArrayList<User> getUsers() throws SQLException {
		ArrayList<User> userList = new ArrayList<>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM user_db");
		User s = null;
		while (rs.next()) {
			if (rs.getString(6).equals("customer")) {
				s = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), 1);
				ArrayList<Account> accounts = new ArrayList<>();
				accounts = cadi.getAccounts(s.getUsername());
				((Customer) s).setAccounts(accounts);
			} else if (rs.getString(6).equals("employee")) {
				s = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), 1);
			} else if (rs.getString(6).equals("admin")) {
				s = new Admin(rs.getString(1), rs.getString(2), rs.getString(3), 1);
			}
			userList.add(s);
		}
		conn.close();
		return userList;
	}
	

}
