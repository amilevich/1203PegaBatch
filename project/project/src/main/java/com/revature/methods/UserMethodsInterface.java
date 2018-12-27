package com.revature.methods;

import com.revature.pojo.User;

public interface UserMethodsInterface {
	
	public boolean createUser(String username, String password);
	//for super user
	public boolean deleteUser(String username);
	public User getUserByName(String username);
	
	public User getUser(String username,String password);	
}
