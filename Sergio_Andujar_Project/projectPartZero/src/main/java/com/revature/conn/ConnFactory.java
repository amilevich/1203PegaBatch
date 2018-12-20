package com.revature.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnFactory {
	
	private static ConnFactory cf = new ConnFactory();

	private final static String user = "bank_db"; // System.getenv("jdbc_user")
	private final static String password = "p4ssw0rd";
	private final static String url = "jdbc:oracle:thin:@octocatdb.ckn7zuvrx3td.us-east-2.rds.amazonaws.com:1521:ORCL";

	private ConnFactory() {
		super();
	}
	public static synchronized ConnFactory getInstance() {
		if(cf==null) {
			cf = new ConnFactory();
			return cf;
		}
		return cf;
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;
	}
}
