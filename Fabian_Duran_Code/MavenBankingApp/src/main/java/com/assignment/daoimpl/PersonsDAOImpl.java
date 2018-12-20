package com.assignment.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.assignment.dao.PersonsDAO;
import com.assignment.persons.Person;
import com.assignment.utilities.ConnFactory;

public class PersonsDAOImpl implements PersonsDAO {
	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void createPerson(String username, String password, int userType) throws SQLException {
		Connection conn = cf.getConnection();//will make a new customer...
		String sql = "{ call new_user(?, ?, ?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, username);//
		call.setString(2, password);
		call.setInt(3, userType);
		call.execute();//just for procedures...		
	}

	//@Override
	//public void readPerson(String username) throws SQLException {}//HAHAHAHAHA
	@Override
	public void deletePerson(int u_id) throws SQLException {
		Connection conn = cf.getConnection();//will make a new customer...
		String sql = "{ call delete_user(?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, u_id);//
		call.execute();//just for procedures...				
	}

	@Override
	public List<Person> getPersonList() throws SQLException {
		List<Person> PersonList = new ArrayList<Person>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM bank_user");//this about getting the information and manipulating it, not reading it
		Person p = null;// temporary object
		while (rs.next()) {// while there is something to return...
			p = new Person(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4));// id is in index 1, username is in index 2
			PersonList.add(p);// adds object to the list
		}
		conn.close();
		return PersonList;//call this and make this list in the person class
	}

	@Override
	public void updatePerson(int userID, String username) throws SQLException {
		Connection conn = cf.getConnection();//will make a new customer...
		String sql = "UPDATE bank_user SET user_name = ? WHERE user_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userID);
		ps.setString(2, username);
		ps.executeUpdate();
		conn.close();
		
	}

}
