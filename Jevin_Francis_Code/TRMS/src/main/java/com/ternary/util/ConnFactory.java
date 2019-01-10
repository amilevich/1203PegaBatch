package com.ternary.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {
	private static ConnFactory cf = new ConnFactory();

	private ConnFactory() {
		super();
	}

	public static synchronized ConnFactory getInstance() {
		if (cf == null) {
			cf = new ConnFactory();
		}
		return cf;
	}

	public Connection getConnection() {
		Connection conn = null;
		// Don't hardcode url, user, password

		Properties properties = new Properties();
		try {
			String path = "database.properties";
			properties.load(this.getClass().getResourceAsStream("/" + path));
			// prop.load(new FileReader(path));
			Class.forName(properties.getProperty("driver"));
			conn = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("password"));
			System.out.println("CONNECTION SUCCESS");
		} catch (FileNotFoundException e) {
			System.out.println("Can't find database.properties file.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("CONECTION FAILED.");
			e.printStackTrace();
		}
		return conn;
	}
}
