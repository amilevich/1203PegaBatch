package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton Connection Factory
 * 
 * @author Blake Biskner
 * @version 2.0
 * 
 */

public class ConnFactory {
	// Constant Declarations
	private static final String DATABASE_PROPS = "database.properties";
	private static final String DRIVER = "driver";
	private static final String URL = "url";
	private static final String USER = "user";
	private static final String PASSWORD = "password";

	private static ConnFactory cf = new ConnFactory();

	private ConnFactory() {
		super();
	}

	public static synchronized ConnFactory getInstance() {
//		if (cf == null) {
//			cf = new ConnFactory();
//		}
		return cf;
	}

	public Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(DATABASE_PROPS));
			Class.forName(prop.getProperty(DRIVER));
			conn = DriverManager.getConnection(prop.getProperty(URL), prop.getProperty(USER),
					prop.getProperty(PASSWORD));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}