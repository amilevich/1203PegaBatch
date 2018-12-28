package com.revature.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.util.ConnFactory;

public class CustomerDaoImpl implements CustomerDao {
	public static ConnFactory cf = ConnFactory.getInstance();

	public Customer getCustomerById(int id) {
		Customer cust = null;
		try (Connection conn = cf.getConnection();) {

			String sql = "SELECT * FROM bank_customer WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				cust = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cust;
	}

	public List<Customer> getAllCustomers() {
		List<Customer> custList = new ArrayList<>();
		try (Connection conn = cf.getConnection();) {
			String sql = "SELECT * FROM bank_customer";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				custList.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return custList;
	}

	public int createCustomer(Customer customer) {
		int rowAffected = 0;
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);

			String sql = "INSERT INTO customer_info VALUES(?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customer.getId());
			ps.setString(2, customer.getFirstname());
			ps.setString(3, customer.getMiddlename());
			ps.setString(4, customer.getLastname());
			ps.setString(5, customer.getPhone());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowAffected;
	}

	public boolean updateCustomer(Customer customer) {
		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "UPDATE customer_info SET first_name = ?, middle_name = ?, last_name = ?, phone = ? WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, customer.getFirstname());
			ps.setString(2, customer.getMiddlename());
			ps.setString(3, customer.getLastname());
			ps.setString(4, customer.getPhone());
			ps.setInt(5, customer.getId());
			ps.executeQuery();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int deleteCustomer(int id) {
		int rowAffected = 0;

		try (Connection conn = cf.getConnection();) {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM customer_info WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rowAffected = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowAffected;
	}

//	public static void main(String[] args) {
//		 List<Customer> custl = new CustomerDaoImpl().getAllCustomers();
//		 custl.forEach(cust-> System.out.println(cust.toString()));
//		 int usr = new UserDaoImpl().createUser(new User("TestingCustomers","word123".toCharArray(),"CUSTOMER"));
//		 User new_user = new UserDaoImpl().getUserById(usr);
//		 int cst = new CustomerDaoImpl().createCustomer(new Customer(new_user.getId(),new_user.getUsername(),
//				 new_user.getPassword(), new_user.getRole(), "Test","","Success??!","9999999999"));
//		 Customer new_cust = new CustomerDaoImpl().getCustomerById(cst);
//		
//		
//		 System.out.println((new CustomerDaoImpl().updateCustomer(
//		 new Customer(new_cust.getId(), new_cust.getUsername(), new_cust.getPassword(), new_cust.getRole(), new_cust.getFirstname(), new_cust.getMiddlename(), "TestingTest", new_cust.getPhone()))));
//		
//		 System.out.println(new CustomerDaoImpl().getCustomerById(cst));
//		
//		 System.out.println(new CustomerDaoImpl().deleteCustomer(new_cust.getId()));
//		
//		 System.out.println(new CustomerDaoImpl().getCustomerById(new_cust.getId()));
//	}

}
