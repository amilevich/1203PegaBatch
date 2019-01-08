package com.revature.trms.dao;

import com.revature.trms.models.Employee;

public interface EmployeeDAO {
	// CRUD methods:
	// Create
	public boolean insertEmployee(Employee empl);
	
	// Read
	public Employee getEmployeeByID(int id);
	public Employee getEmployeeByUsername(String username); 
	public Employee getDepartmentHead(String username);
	
	// Update
	public boolean updateEmployee(Employee empl);
	public boolean updateEmployeePassword(int id, String newPassword);
	
	// Delete
	
	
}
