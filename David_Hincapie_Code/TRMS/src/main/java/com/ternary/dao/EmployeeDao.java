package com.ternary.dao;

import java.sql.SQLException;
import java.util.List;

import com.ternary.model.Employee;


public interface EmployeeDao {
	
	//CRUD
	
	public Employee selectByEmployeeEmail(String email);

	public Employee selectByEmployeeId(int id);
	
	public List<Employee> getEmployeeList();

}