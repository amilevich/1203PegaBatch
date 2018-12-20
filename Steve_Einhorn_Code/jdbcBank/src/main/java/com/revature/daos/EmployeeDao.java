package com.revature.daos;

import com.revature.models.Employee;

/**
 * Data Access Object
 * ---
 * That this is the interface through which
 * we intend to interact with our persistency layer
 * for this kind of project.d
 * 
 * @author Steven Einhorn
 */

public interface EmployeeDao {

	public boolean findEmployee(Employee employee);
	public boolean findBusinessAnalyst(Employee employee);
	
}
