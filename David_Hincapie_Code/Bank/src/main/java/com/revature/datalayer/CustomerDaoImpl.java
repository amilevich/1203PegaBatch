package com.revature.datalayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.businesslayer.Customer;

public class CustomerDaoImpl implements CustomerDao {

	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean insertCustomerUser(Customer customer) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "INSERT INTO bank_customer (buid, approved) VALUES (?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, customer.getId());
		// ps.setString(2, customer.getUserId());
		ps.setInt(2, customer.isApproved() ? 1 : 0);
		int rowCount = ps.executeUpdate();
		if (rowCount == 1) {
			System.out.println("CUSTOMER RECORDED INSERTED SUCCESSFULLY: " + customer);
			conn.close();
			return true;
		} else {
			System.out.println("CUSTOMER RECORDED INSERTED FAILURE: " + customer);
			conn.close();
			return false;
		}

	}

	@Override
	public boolean updateUser(Customer customer) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "UPDATE bank_customer SET buid = ?, approved = ?  WHERE buid = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, customer.getId());
		ps.setInt(2, customer.isApproved() ? 1 : 0);
		ps.setInt(3, customer.getId());
		// ps.setString(3, customer.getUserId().toString());
		int rowCount = ps.executeUpdate();
		if (rowCount == 1) {
			System.out.println("CUSTOMER RECORDED UPDATED SUCCESSFULLY: " + customer);
			conn.close();
			return true;
		} else {
			System.out.println("CUSTOMER RECORDED UPDATED FAILURE: " + customer);
			conn.close();
			return false;
		}
	}

	@Override
	public boolean deleteCustomerUser(Customer customer) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "DELETE FROM bank_customer WHERE buid = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, customer.getId());
		// ps.setString(1, customer.getUserId().toString());
		int rowCount = ps.executeUpdate();
		if (rowCount == 1) {
			System.out.println("CUSTOMER RECORDED DELETED SUCCESSFULLY: " + customer);
			conn.close();
			return true;
		} else {
			System.out.println("CUSTOMER RECORDED DELETED FAILURE: " + customer);
			conn.close();
			return false;
		}
	}

	@Override
	public Customer findCustomerById(int buid) throws SQLException {
		try {
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			Customer customer = new Customer();
			ResultSet rs = stmt.executeQuery(
					"SELECT bank_user.buid, bank_user.userid, bank_user.firstname, bank_user.lastname,  bank_user.username, bank_user.userpassword,  bank_user.usertype, bank_customer.approved FROM bank_user FULL OUTER JOIN bank_customer ON bank_user.buid = bank_customer.buid WHERE bank_customer.buid = '"
							+ buid + "'");
			int rowCount = 0;
			if (rs.next()) {
				rowCount += rs.getRow();
				customer.setId(rs.getInt("buid"));
				customer.setUserId(rs.getString("userid"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setUserName(rs.getString("username"));
				customer.setPassword(rs.getString("userpassword"));
				customer.setUserType(rs.getString("usertype"));
				customer.setApproved(rs.getBoolean("approved"));
				conn.close();
			}
			if (rowCount == 1) {
				System.out.println("CUSTOMER FOUND. " + buid);
				return customer;
			} else {
				System.out.println("CUSTOMER NOT FOUND. " + buid);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Customer> getCustomerList() throws SQLException {
		List<Customer> customerList = new ArrayList<>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT bank_user.buid, bank_user.userid, bank_user.firstname, bank_user.lastname,  bank_user.username, bank_user.userpassword,  bank_user.usertype, bank_customer.approved FROM bank_user FULL OUTER JOIN bank_customer ON bank_user.buid = bank_customer.buid");
		Customer customer = null;
		while (rs.next()) {
			customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getBoolean(8));
			customerList.add(customer);
		}
		conn.close();
		return customerList;
	}

	@Override
	public Customer findCustomerByUserId(String userId) throws SQLException {
		try {
			Connection conn = cf.getConnection();
			Statement stmt = conn.createStatement();
			Customer customer = new Customer();
			ResultSet rs = stmt.executeQuery(
					"SELECT bank_user.buid, bank_user.userid, bank_user.firstname, bank_user.lastname,  bank_user.username, bank_user.userpassword,  bank_user.usertype, bank_customer.approved FROM bank_user FULL OUTER JOIN bank_customer ON bank_user.buid = bank_customer.buid WHERE bank_user.userid = '"
							+ userId + "'");
			int rowCount = 0;
			if (rs.next()) {
				rowCount += rs.getRow();
				customer.setId(rs.getInt("buid"));
				customer.setUserId(rs.getString("userid"));
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setUserName(rs.getString("username"));
				customer.setPassword(rs.getString("userpassword"));
				customer.setUserType(rs.getString("usertype"));
				customer.setApproved(rs.getBoolean("approved"));
				conn.close();
			}
			if (rowCount == 1) {
				System.out.println("CUSTOMER FOUND. " + userId);
				return customer;
			} else {
				System.out.println("CUSTOMER NOT FOUND. " + userId);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
