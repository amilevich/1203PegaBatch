package com.bank.main;

public class User {
	int id;
	String user;	
	String passWord;
	String email;
	int permission;
	

	public User(int id, String user, String passWord, String email, int permission) {
		super();
		this.id = id;
		this.user = user;
		this.passWord = passWord;
		this.email = email;
		this.permission = permission;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
