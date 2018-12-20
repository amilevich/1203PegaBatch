package com.revature.pojos;

/**
 * Application POJO
 * @author Karan
 *
 */
public class Application {
	private int app_id = 0;
	private int user_id = 0;
	private int user_id2 =0;
	private String username_1 = "";
	private String username_2 = "";
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser_id2() {
		return user_id2;
	}
	public void setUser_id2(int user_id2) {
		this.user_id2 = user_id2;
	}
	public String getUsername_1() {
		return username_1;
	}
	public void setUsername_1(String username_1) {
		this.username_1 = username_1;
	}
	public String getUsername_2() {
		return username_2;
	}
	public void setUsername_2(String username_2) {
		this.username_2 = username_2;
	}
	public Application(int app_id, int user_id, String username_1, int user_id2, String username_2) {
		super();
		this.app_id = app_id;
		this.user_id = user_id;
		this.user_id2 = user_id2;
		this.username_1 = username_1;
		this.username_2 = username_2;
	}
	public Application(int app_id, int user_id, String username_1 ) {
		super();
		this.app_id = app_id;
		this.user_id = user_id;
		this.username_1 = username_1;
	}
	
	public Application() {
	}
	
	
	
	public Application(int user_id) {
		super();
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		
		return "ID: " + app_id + " Holder(s): " + username_1 + (username_2.equals("")?"":", " + username_2);
	}
	
	
	
	
	
}
