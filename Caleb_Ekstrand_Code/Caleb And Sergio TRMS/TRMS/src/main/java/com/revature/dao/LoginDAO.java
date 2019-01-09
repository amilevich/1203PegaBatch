package com.revature.dao;

import com.revature.model.Login;

public interface LoginDAO {
	public Login selectLoginByUsername(String username);

}
