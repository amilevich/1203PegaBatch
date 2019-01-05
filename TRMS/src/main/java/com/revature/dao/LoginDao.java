package com.revature.dao;

import java.sql.SQLException;

import com.revature.bean.Login;

public interface LoginDao {

	public abstract boolean isInLogin(Login login) throws SQLException;
}
