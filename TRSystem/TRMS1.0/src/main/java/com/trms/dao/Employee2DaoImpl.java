package com.trms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.trms.model.Employee2;

public class Employee2DaoImpl implements Employee2Dao {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static String url = "jdbc:oracle:thin:@spiderman.cuji1aq6umug.us-east-2.rds.amazonaws.com:1521:ORCL";
	private static String username = "adminspiderman";
	private static String password="p4ssw0rd";
	
	@Override
	public int insertEmployee2(Employee2 employee2) {
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Employee2 VALUES(?, ?)");
			ps.setString(1, employee2.getScreenname());
			ps.setString(2, employee2.getPassword());
			return ps.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Employee2 selectEmployee2ByScreenname(String screenname) {
		Employee2 employee2 = null;
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Employee2 WHERE screenname=?");
			ps.setString(1, screenname);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				employee2 = new Employee2(rs.getString("Screenname"), rs.getString("Passwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee2;
	}

}
