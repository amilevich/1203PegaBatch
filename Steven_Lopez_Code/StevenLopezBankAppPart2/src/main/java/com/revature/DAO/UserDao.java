package com.revature.DAO;

import java.util.List;

import com.revature.models.User;

public interface UserDao {

	//CREATE
	public int createUser(User user);
	
	//READ and READ ALL
	public User getUserById(int id);
	public List<User> getAllUsers();
	public User getUserByIdentifier(String username, String password);
	
	//UPDATE
	public boolean updateUser(User user);
	
	//DELETE
	public int deleteUser(int id);
}
