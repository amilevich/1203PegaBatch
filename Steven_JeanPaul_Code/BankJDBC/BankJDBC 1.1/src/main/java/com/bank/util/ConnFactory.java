package com.bank.util;

import java.io.FileNotFoundException;
import java.io.FileReader; //ctrl + shift + o to do quick import.
import java.io.IOException;
import java.sql.Connection; //ctrl + shift + o to do quick import.
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties; //ctrl + shift + o to do quick import.

public class ConnFactory {
	private static ConnFactory cf = new ConnFactory(); //Create static instance of ourselves?
	
	//Default constructor
	//This will be a factory of connection methods so its easy to make a bunch of them
	private ConnFactory() {
		super();
	}
	
	public static synchronized ConnFactory getInstance() {
		if(cf == null) {
			cf = new ConnFactory();
		}
		return cf; //return the instance of the connfactory 
	}
	
	public Connection getConnection() {
		Connection conn = null;
		//Don't hardcode url, usr, pw ---- no good.
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("database.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"), prop.getProperty("pw"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
}
