package com.revature.bean;

public class Login {
	
	int loginId;
	String username;
	String passwd;
	
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login(int loginId, String username, String passwd) {
		super();
		this.loginId = loginId;
		this.username = username;
		this.passwd = passwd;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "Login [loginId=" + loginId + ", username=" + username + ", passwd=" + passwd + "]";
	}
}
