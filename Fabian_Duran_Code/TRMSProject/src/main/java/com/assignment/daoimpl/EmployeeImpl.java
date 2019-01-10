package com.assignment.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.assignment.bean.Employee;
import com.assignment.dao.EmployeeDAO;
import com.assignment.utilities.ConnFactory;

public class EmployeeImpl implements EmployeeDAO {
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public boolean readPassword(int ID, String password) throws SQLException {
		Connection conn = cf.getConnection();
		System.out.println("connectd!");
		int id = 0;
		String pw = "";
		String sql = "SELECT emp_id, password_ FROM Employee WHERE emp_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		System.out.println("prepared");
		ps.setInt(1, ID);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			id = rs.getInt(1);
			pw = rs.getString(2);
		}
		System.out.println("The system id and passsword are: " + id + " and " + pw);
		if (ID == id && password.equals(pw))
			return true;
		else
			return false;
	}

	@Override
	public Employee readEmployee(int ID) throws SQLException {
		Employee emp = null;
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM emp_view WHERE emp_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, ID);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			emp = new Employee(rs.getInt("emp_id"), rs.getString("emp_firstname"), rs.getString("emp_middleinitial"),
					rs.getString("emp_lastname"), rs.getString("emp_address"), rs.getString("emp_city"),
					rs.getString("emp_state"), rs.getInt("emp_zip"), rs.getLong("emp_phonenumber"),
					rs.getString("emp_building"), rs.getInt("direct_superid"), rs.getInt("dept_headid"),
					rs.getDouble("emp_remainingaward"), rs.getString("ludefinition"), rs.getString("password_"));
		}
		return emp;
	}

	@Override
	public void checkYear() throws SQLException {// checks system for the new year, if new year updates everyone's
													// remaining award to 1000
		int tempYear = 0;
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM rei_year";// I realize that reimbursement impls will look very different, I blame
												// Alex for being different than Matt in her coding and me for being
												// lazy for not changing it
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
			tempYear = rs.getInt("rei_year");
		int year = Calendar.getInstance().get(Calendar.YEAR);// gets the current year
		if (tempYear != year) {
			sql = "UPDATE rei_year SET rei_year = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, year);
			tempYear = ps.executeUpdate();// cuz reasonzzzzzzzz
			sql = "UPDATE employee SET emp_remainingaward = 1000";
			ps = conn.prepareStatement(sql);
			tempYear = ps.executeUpdate();
			System.out.println("Year update in sql");
		}
	}

	@Override
	public void updateAddress(int emp_id, String streetAddress, String city, String state, int zip)
			throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "UPDATE employee SET EMP_ADDRESS = ?, EMP_CITY = ?, EMP_STATE = ?, EMP_ZIP = ? WHERE emp_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, streetAddress);
		ps.setString(2, city);
		ps.setString(3, state);
		ps.setInt(4, zip);
		ps.setInt(5, emp_id);
		int tempI = ps.executeUpdate();// LOLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
		System.out.println(tempI + "\t addresss UPDATED");
	}

	@Override
	public void updatePhonenum(int emp_id, long phNumber) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "UPDATE employee SET EMP_PHONENUMBER = ? WHERE emp_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, phNumber);
		ps.setInt(2, emp_id);
		int tempI = ps.executeUpdate();// if this doesn't work the world will blow up
		System.out.println(tempI + "\t phone number UPDATED");
	}

	@Override
	public void updateAward(int emp_id, double newAmount) throws SQLException {//I should be doing the check for overdrafts in the controller
		Connection conn = cf.getConnection();
		String sql = "UPDATE employee SET EMP_REMAININGAWARD = ? WHERE emp_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDouble(1, newAmount);
		ps.setInt(2, emp_id);
		int tempI = ps.executeUpdate();// if this doesn't work the world will blow up
		System.out.println(tempI + "\t remaining award amount UPDATED");
	}

}
