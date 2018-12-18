package com.revature.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException, IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("C:\\Users\\ihabb\\Desktop\\project\\project\\src\\test\\resources\\common.properties");
		prop.load(in);
		String url = "jdbc:oracle:thin:@monger.c75ty2cvarhp.us-east-2.rds.amazonaws.com:1521:orcl";
		String username = prop.getProperty("username");
				//prop.getProperty("ijb87");
		String password = prop.getProperty("password");
				//prop.getProperty("p4ssw0rd");
		return DriverManager.getConnection(url, username, password);
	}
	
}
