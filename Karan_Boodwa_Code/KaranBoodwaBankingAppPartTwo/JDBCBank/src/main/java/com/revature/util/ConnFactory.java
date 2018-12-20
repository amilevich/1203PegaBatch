package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class is in charge of connecting to the database directly Having this
 * connection in exactly one place reduces redundant code and keeps the overall
 * application modular, being able to hotswap databases easily by simply
 * changing it below
 * 
 * @author karan
 *
 */
/*
 * Singleton Connection Factory (pumps out connections)
 */
public class ConnFactory {
	private static ConnFactory cf = new ConnFactory();

	private ConnFactory() {
	}

	public static synchronized ConnFactory getInstance() {
		if (cf == null) {
			cf = new ConnFactory();
		}
		return cf;
	}

	public Connection getConnection() {
		Connection conn = null;
		// Don't hardcode url, username, and password (BAD)
		Properties prop = new Properties();

		try {
			// Create Connection using properties file
			prop.load(new FileReader("database.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
					prop.getProperty("password"));
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}

}
