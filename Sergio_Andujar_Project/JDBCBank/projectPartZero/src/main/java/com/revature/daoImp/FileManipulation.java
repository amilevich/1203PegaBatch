package com.revature.daoImp;

import java.io.IOError;
import java.sql.CallableStatement;
import java.sql.Connection;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.revature.classes.Account;
import com.revature.classes.BankAdmin;
import com.revature.classes.Customer;
import com.revature.classes.Employee;
import com.revature.conn.ConnFactory;
import com.revature.dao.RetrievalDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileManipulation implements RetrievalDao{
	
	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void removeLogin(int id)throws SQLException, IOError{
		Connection conn = cf.getConnection();
		String sql = "Delete from login "
					+ "where user_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		conn.close();
		removeFromUserAccount(id);
	}
	
	@Override
	public void insertIds(int cus, int acc) throws SQLException, IOError{
		
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO user_account VALUES(?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, cus);
		ps.setInt(2, acc);
		ps.executeUpdate();
		ps.close();
		conn.close();
		
	}
	
	@Override
	public void createAccount(int id) throws SQLException, IOError{
		
		Connection conn = cf.getConnection();
		//add the user name and password to login
		//gets the unique key generated in the database sequence, need this for adding the user
		int generatedKey = 0;
		String sql = "INSERT INTO a_account VALUES (?,?,?)";
		String key[] = {"account_id"};
		PreparedStatement ps = conn.prepareStatement(sql, key); 
		ps.setString(1, null);
		ps.setInt(2,  2);
		ps.setInt(3, 0);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			generatedKey = rs.getInt(1);
		}
		
		insertIds(id, generatedKey);
		ps.close();
		rs.close();
		conn.close();
		
		
	}
	
	@Override
	public ArrayList<Customer> getUnregisteredUsers() throws SQLException, IOError{
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT user_id, firstname, lastname FROM a_user where type_id = 1");
		Customer cus = null; 
		while(rs.next()) {
			cus = new Customer(rs.getInt(1),rs.getString(2), rs.getString(3));
			customerList.add(cus);
		}
		rs.close();
		conn.close();
		stmt.close();
		return customerList;
		
	}
	
	@Override
	public Customer getACustomer(String givenUserName, String givenPassword) throws SQLException, IOError{
		Connection conn = cf.getConnection();
		String sql = "SELECT a_user.user_id, a_user.firstname, a_user.lastname, a_user.type_id"
				   + " FROM a_user"
				   + " Inner Join Login on a_user.user_id = Login.user_id"
				   + " WHERE Login.username = ? and Login.passwords = ?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setString(1, givenUserName);
		ps.setString(2,  givenPassword);
		ResultSet rs = ps.executeQuery();
		Customer emp = null; 
		while(rs.next()) {
			if(rs.getInt(4) == 1) {
				throw new SQLException();
			}
			emp = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3));
		}
		if(emp == null) {throw new SQLException();}
		ps.close();
		rs.close();
		conn.close();
		return emp;
		
	}
	
	@Override
	public Employee getAEmployee(String givenUserName, String givenPassword)throws SQLException, IOError{
		Connection conn = cf.getConnection();
		String sql = "SELECT a_user.user_id, a_user.firstname, a_user.lastname, a_user.type_id"
				   + " FROM a_user"
				   + " Inner Join Login on a_user.user_id = Login.user_id"
				   + " WHERE Login.username = ? and Login.passwords = ?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setString(1, givenUserName);
		ps.setString(2,  givenPassword);
		ResultSet rs = ps.executeQuery();
		Employee emp = null; 
		while(rs.next()) {
			emp = new Employee(rs.getString(2), rs.getString(3));
		}
		rs.close();
		ps.close();
		conn.close();
		if(emp == null) {throw new SQLException();}
		return emp;

	}
	
	@Override
	public BankAdmin getABankAdmin(String givenUserName, String givenPassword)throws SQLException, IOError{
		Connection conn = cf.getConnection();
		String sql = "SELECT a_user.user_id, a_user.firstname, a_user.lastname, a_user.type_id"
				   + " FROM a_user"
				   + " Inner Join Login on a_user.user_id = Login.user_id"
				   + " WHERE Login.username = ? and Login.passwords = ?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setString(1, givenUserName);
		ps.setString(2,  givenPassword);
		ResultSet rs = ps.executeQuery();
		BankAdmin emp = null; 
		while(rs.next()) {
			emp = new BankAdmin(rs.getString(2), rs.getString(3));
		}
		rs.close();
		ps.close();
		conn.close();
		if(emp == null) {throw new SQLException();}
		return emp;
	}
	
	@Override
	public ArrayList<Account> getAllAccounts()throws SQLException, IOError{
		ArrayList<Account> aAccounts = new ArrayList<Account>();
		Connection conn = cf.getConnection();
		String sql = "select balance, account_id from a_account";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Account aAccount = new Account(rs.getInt(1), rs.getInt(2));
			aAccounts.add(aAccount);
		}
		rs.close();
		ps.close();
		conn.close();
		return aAccounts;
	}

	@Override
	public void updateAccount(Account account) throws SQLException , IOError{
		Connection conn = cf.getConnection();
		String sql = "update a_account"
				+ " set balance = ? "
				+ " where account_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, account.getBalance());
		ps.setInt(2, account.getID());
		ps.executeUpdate();
		ps.close();
		conn.close();
		
	}
	
	@Override
	public void updateCustomerToRegistered(Customer customer)throws SQLException, IOError{
		Connection conn = cf.getConnection();
		String sql = "Update a_user "
					+ "set type_id = 2 "
					+ "where user_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, customer.getId());
		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	
	@Override
	public void createApplication(String firstName, String lastName, String alias, String passWord) throws SQLException, IOError {
		//get connection to the database
		//adds the user name and password to the login table
		//adds the user into the a_user table with the unregistered type
			Connection conn = cf.getConnection();
			//add the user name and password to login
			//gets the unique key generated in the database sequence, need this for adding the user
			int generatedKey = 0;
			String sql = "INSERT INTO login VALUES (?,?,?)";
			String key[] = {"user_id"};
			PreparedStatement ps = conn.prepareStatement(sql, key); 
			ps.setString(1, alias);
			ps.setString(2,  passWord);
			ps.setString(3, null);
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				generatedKey = rs.getInt(1);
			}
			
			//add the user into the a_user table using the generated key
			String quary = "INSERT INTO a_user VALUES (?,?,?, ?)";
			PreparedStatement psTwo = conn.prepareStatement(quary);
			psTwo.setInt(1, generatedKey);
			psTwo.setString(2, firstName);
			psTwo.setString(3, lastName);
			psTwo.setInt(4, 1);
			psTwo.executeUpdate();
			
			ps.close();
			psTwo.close();
			rs.close();
			
			conn.close();
		
	}
	
//	@Override
//	public void getUserAndAccount(int id) throws SQLException, IOError{
//		Connection conn = cf.getConnection();
//	}
	
	@Override
	public ArrayList<Account> getUserAccounts(int id) throws SQLException, IOError {
		ArrayList<Account> accounts = new ArrayList<Account>();
		ArrayList<Integer> account_ids = new ArrayList<Integer>();
		Connection conn = cf.getConnection();
		String sql = "Select account_id from user_account where user_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			account_ids.add(rs.getInt(1));
		}
		
		FileManipulation dao = new FileManipulation();
		ArrayList<Account> all = dao.getAllAccounts();
		
		for(int i = 0; i < all.size(); i++) {
			for(int j = 0; j < account_ids.size(); j++) {
				if(account_ids.get(j) == all.get(i).getID()) {
					accounts.add(all.get(i));
				}
			}
		}
		rs.close();
		ps.close();
		conn.close();
		return accounts;
		
	}
	
	
	
	
	@Override
	public ArrayList<Customer> getAllCustomer() throws SQLException, IOError{
		
		ArrayList<Customer> all = new ArrayList<Customer>();
		Connection conn = cf.getConnection();
		String sql = "select user_id, firstname, lastname from a_user where type_id = 2";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Customer aCustomer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3));
			all.add(aCustomer);
		}
		rs.close();
		ps.close();
		conn.close();
		return all;
		
	}
	
	@Override
	public void removeCustomer(Customer customer) throws SQLException, IOError{
		Connection conn = cf.getConnection();
		String sql = "Delete from a_user "
					+ "where user_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, customer.getId());
		ps.executeUpdate();
		ps.close();
		conn.close();
		removeFromUserAccount(customer.getId());
	}
		
	
	@Override
	public void removeAccount(int id) throws SQLException, IOError{
		Connection conn = cf.getConnection();
		String sql = "Delete from a_account "
					+ "where account_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		conn.close();
		removeFromUserAccount(id);
	}
	
	@Override
	public void removeFromUserAccount(int cusid, int accid) throws SQLException, IOError {
		Connection conn = cf.getConnection();
		String sql = "Delete from user_account "
					+ "where user_id = ? and account_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, cusid);
		ps.setInt(2, accid);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	@Override
	public void removeFromUserAccount(int id) throws SQLException, IOError {
		Connection conn = cf.getConnection();
		String sql = "Delete from user_account "
					+ "where user_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	@Override
	public void removeDeclined(Customer rejected) throws SQLException, IOError {
		Connection conn = cf.getConnection();
		String sql = "Delete from a_user "
					+ "where user_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, rejected.getId());
		ps.executeUpdate();
		ps.close();
		conn.close();
		removeFromUserAccount(rejected.getId());
		
	}

	@Override
	public void dropTables() throws SQLException, IOError {
		Connection conn = cf.getConnection();
		String sql = "{ call drop_everything";
		CallableStatement call = conn.prepareCall(sql);
		call.execute();
		conn.close();
	}
		
	
//	@Override
//	public void createJointApplication(String firstName, String lastName, String userName, 
//			String passWord, String anfirstName, String anLastName, 
//			String userName2, String passWord2) throws SQLException{
//		
//	}	
}
