package com.bank.daoimpl;

import java.sql.CallableStatement;
//import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import com.bank.beans.Customer;
import com.bank.dao.CustomerDAO;
import com.bank.util.ConnFactory;

public class CustomerDAOImpl implements CustomerDAO {

	public static ConnFactory cf = ConnFactory.getInstance();

	public void createCustomer(String fn, String ln, int streetNum, String streetName, String city, String cstate,
			int zipcode, long phone, String email, int accid, int pin, String un, String pw) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTCUSTOMER(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, fn);
		call.setString(2, ln);
		call.setInt(3, streetNum);
		call.setString(4, streetName);
		call.setString(5, city);
		call.setString(6, cstate);
		call.setInt(7, zipcode);
		call.setLong(8, phone);
		call.setString(9, email);
		call.setInt(10, accid);
		call.setInt(11, pin);
		call.setString(12, un);
		call.setString(13, pw);
		call.execute();
		String sql1 = "SELECT firstname FROM CUSTOMER WHERE firstname = ?";
		PreparedStatement ps = conn.prepareStatement(sql1);
		ps.setString(1, fn);
		ps.setString(2, ln);
		ps.setInt(3, streetNum);
		ps.setString(4, streetName);
		ps.setString(5, city);
		ps.setString(6, cstate);
		ps.setInt(7, zipcode);
		ps.setLong(8, phone);
		ps.setString(9, email);
		ps.setInt(10, accid);
		ps.setInt(11, pin);
		ps.setString(12, un);
		ps.setString(13, pw);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			for (int i = 0; i < rs.getFetchSize(); i++) {
				System.out.println("Id of this superhero is : " + rs.getInt(i));
			}
		}
	}

}
