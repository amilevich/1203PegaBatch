package com.ternary.dao;

import com.ternary.model.Employee;


public interface EmployeeDao {
	
	//CRUD
	
	public Employee selectByEmployeeEmail(String email);

	public Employee selectByEmployeeId(int id);

}
