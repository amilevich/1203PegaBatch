package com.bank.dao;

import com.bank.main.User;

public interface BankUserDao {
	public int createUser(User uesr); // Look at 1

	public User getUserById(String username, String password, String email); // Look at 2

}
