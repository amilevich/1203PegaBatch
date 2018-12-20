package com.revature.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.OpenApplication;

public class OpenApplicationDaoImpl implements OpenApplicationDao {
	
	private static Connection getConnection() {
		
		// much more secure as environment variables
		String user = "bank_db";  //System.getenv("jdbc_user")
		String password = "p4ssw0rd";
		String url = "jdbc:oracle:thin:@octocatdb.cowlaewb2yhg.us-east-2.rds.amazonaws.com:1521:ORCL";	

		try {
			return DriverManager.getConnection(url, user,  password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<OpenApplication> getOpenApplications() {
		
		List<OpenApplication> openAppls = new ArrayList<>();
		OpenApplication openAppl;
		
		// Connection is a major interface of JDBC
		// Connection is used to access a variety of other interfaces
		Connection conn = getConnection();
		
		String query = "SELECT id, username, password, acct_type, deposit FROM open_applications ";
					   
		try {
			
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				openAppl = new OpenApplication();
				openAppl.setId( rs.getInt("id") );
				openAppl.setUsername( rs.getString("username") );
				openAppl.setPassword( rs.getString("password") );
				openAppl.setAcctType( rs.getString("acct_type") );
				openAppl.setInitDeposit( rs.getDouble("deposit") );
				
				openAppls.add(openAppl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return openAppls;
	}
	
	public boolean findOpenApplication(OpenApplication openAppl) {
		
		// Connection is a major interface of JDBC
		// Connection is used to access a variety of other interfaces
		Connection conn = getConnection();
		
		boolean openApplFound = false;
		
		String query = "SELECT id, username, password, acct_type, deposit FROM open_applications " +
				   		"WHERE username = ?";
					   
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, openAppl.getUsername());
//			ps.setString(2, openAppl.getPassword());
//			ps.setString(3, openAppl.getAcctType());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				openApplFound = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return openApplFound;
	}
	
	public void saveOpenApplication(OpenApplication openAppl) {
		
		Connection conn = getConnection();
		
		boolean openApplFound = false;
		
		String query = "INSERT INTO open_applications VALUES ( " +
				   		"seq_open_applications.nextval, ?, ?, ?, ? )";
					   
		try {
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, openAppl.getUsername());
			ps.setString(2, openAppl.getPassword());
			ps.setString(3, openAppl.getAcctType());
			ps.setDouble(4, openAppl.getInitDeposit());
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void deleteOpenApplication(OpenApplication openAppl) {
		
		Connection conn = getConnection();
		
		String query = "DELETE FROM open_applications " +
		   			    "WHERE id = ?";
					   
		try {
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, openAppl.getId());
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
