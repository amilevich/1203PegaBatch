package com.revature.daos;

import java.util.List;

import com.revature.pojos.User;

public interface UserDao {
	// CRUD
	
	// Create
	public boolean createUser(User user);
	
	// Read
	public User getUserByUsername(String username);
	
	public List<String> getCustomers();
	
	// Update
	public boolean updateUser(int userid, String username, String password);
	
	// Delete
	
	public boolean deleteUser(int userid);
	
	
}
