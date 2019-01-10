package com.assignment.dao;

import java.sql.SQLException;

import com.assignment.bean.Employee;

public interface EmployeeDAO {
	//CRUD Operations--not that much creating or deleting here, but definitely reading and updating
	public abstract boolean readPassword (int ID, String password) throws SQLException;
	public abstract Employee readEmployee(int ID) throws SQLException;
	public abstract void checkYear()throws SQLException;
	public abstract void updateAddress (int emp_id, String streetAddress, String city, String state, int zip ) throws SQLException;
	public abstract void updatePhonenum (int emp_id, long phNumber) throws SQLException;
	public abstract void updateAward(int emp_id, double newAmount) throws SQLException;
		
}
