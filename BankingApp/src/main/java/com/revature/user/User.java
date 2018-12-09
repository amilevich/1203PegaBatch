package com.revature.user;

public class User {
	
	private String name;
	private String password;
	private int permissions = 0;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getPermissions() {
		return permissions;
	}
	
	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", permissions=" + permissions + "]";
	}
	
}
