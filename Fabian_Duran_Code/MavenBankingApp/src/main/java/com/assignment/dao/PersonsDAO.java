package com.assignment.dao;

import java.sql.SQLException;
import java.util.List;

import com.assignment.persons.Person;

public interface PersonsDAO {
	public abstract void createPerson(String username, String password, int userType ) throws SQLException;//creates new user, will have to do a check for username uniqueness
	//public abstract void readPerson(String username) throws SQLException;//get person info from db
	public abstract void updatePerson(int userID, String username) throws SQLException;
	//and I don't have the functionality for it LOL!!! 
	public abstract void deletePerson(int u_id) throws SQLException;//to be used only by superuser
	public abstract List<Person> getPersonList() throws SQLException;//list of accounts? better than read PERSON
}
