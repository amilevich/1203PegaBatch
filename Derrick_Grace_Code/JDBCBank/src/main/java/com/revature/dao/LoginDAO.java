package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Login;

public interface LoginDAO {
	//CRUD
	
	//CREATE
	public abstract void createLoginProc(Login logIn) throws SQLException;
	
	//READ
	public abstract List<Login> getLoginList() throws SQLException;
	public abstract boolean isInLogin(Login logIn) throws SQLException;
	public abstract int getUserId(Login logIn) throws SQLException;
	
	//UPDATE
	
	
	//DELETE
}
