package com.revature.dao;

import java.sql.SQLException;

import com.revature.bean.Employee;

public interface EmployeeDao {
	
	public abstract void insertEmployInfo(Employee employee) throws SQLException;
	
	
	public abstract void selectEmployInfo(Employee employee) throws SQLException;
}
