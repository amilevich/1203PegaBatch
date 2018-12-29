package com.ternary.dao;

import com.ternary.model.Employee;


public interface EmployeeDao {
	
	//CRUD
	
	public Employee selectByEmail(String email);

}
