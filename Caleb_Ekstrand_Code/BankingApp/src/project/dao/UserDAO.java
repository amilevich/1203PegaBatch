package project.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import project.users.User;

public interface UserDAO {
	//CRUD
	//CREATE
	public abstract void createCustomer(String username, String password, String name, String address, String phone) throws SQLException;
	public abstract void createEmployee(String username, String password, String name) throws SQLException;
	public abstract void createAdmin(String username, String password, String name) throws SQLException;
	//READ
	public abstract ArrayList<String> getUsernames() throws SQLException;
	public abstract ArrayList<User> getUsers() throws SQLException;
	//UPDATE
	
	//DELETE
	
}
