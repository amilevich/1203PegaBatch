package com.assignment.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {//I don't know if I'm going to need this LOL, yeah I will
	private static ConnFactory cf = new ConnFactory();

	
	//this will be a singleton factory
	private ConnFactory() {
		super();
	}
	
	//create method that creates the objects
	public static synchronized ConnFactory getInstance() {
		if (cf==null) {
			cf= new ConnFactory();
		}
		return cf;
	}
	//make a connection...
	public Connection getConnection() {
		Connection conn = null;
		//not hardcoded, but referenced external, the url, username, password->it BAD!
		Properties prop = new Properties();
		//method from properties
		try {
		prop.load(new FileReader("database.properties"));
		Class.forName(prop.getProperty("driver"));
		conn = DriverManager.
				getConnection(prop.getProperty("url"),
				prop.getProperty("user"), prop.getProperty("password"));
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return conn;
	}
}
