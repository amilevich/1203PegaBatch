package com.trms.model;

public class Employee2 { 

	private String screenname, passwd;
	
	public Employee2() {
	}

	@Override
	public String toString() {
		return "Employee [Screenname = " + screenname + ", Password is = " + passwd + "]";
	}

	public String getScreenname() {
		return screenname;
	}

	public void setScreenname(String screenname) {
		this.screenname = screenname;
	}

	public String getPassword() {
		return passwd;
	}

	public void setPassword(String password) {
		this.passwd = password;
	}

	public Employee2(String screenname, String password) {
		super();
		this.screenname = screenname;
		this.passwd = password;
	}	
}

