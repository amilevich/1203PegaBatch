package com.revature.beans;

public class Login {
	private int userID;
	private String userName;
	private String passWord;
	
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login(int userID, String userName, String passWord) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.passWord = passWord;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "Login [userID=" + userID + ", userName=" + userName + ", passWord=" + passWord + "]";
	}
	
	
	
	
}
