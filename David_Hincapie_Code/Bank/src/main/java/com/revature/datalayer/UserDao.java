package com.revature.datalayer;

import java.sql.SQLException;
import java.util.List;

import com.revature.businesslayer.User;
import com.revature.businesslayer.Usertype;

public interface UserDao {
	// CRUD OPERTAIONS
	public abstract boolean insertUser(User user) throws SQLException;

	public abstract boolean updateUser(User user) throws SQLException;

	public abstract boolean deleteUser(User user) throws SQLException;

	public abstract List<User> getUserList() throws SQLException;

	public abstract User findUserById(int id) throws SQLException;

	public abstract User findUserByUserId(String userId) throws SQLException;

	// public abstract void createUsr(String firstName, String lastName, String
	// userName, String password) throws SQLException;

}
