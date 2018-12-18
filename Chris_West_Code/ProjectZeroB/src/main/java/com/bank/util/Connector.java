package com.bank.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

	private static Connector cr = new Connector();

	private Connector() {
		super();
	}

	public static synchronized Connector getInstance() {
		if (cr == null) {
			cr = new Connector();
		}
		return cr;
	}

	public Connection getConnection(){
	  Connection conn = null;
	  // Don't hardcode url, usr, pw

	  Properties prop = new Properties();
	  try{
	  prop.load(new FileReader("database.properties"));
	try {
		Class.forName(prop.getProperty("driver"));
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}catch (IOException e){
	  e.printStackTrace();
	}
	return conn;
	}
}