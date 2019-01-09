package com.revature.dao;

import com.revature.model.User;

public interface UserDAO {
	public User selectUserById(int userId);
}
