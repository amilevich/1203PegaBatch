package com.revature.dao;

import java.sql.SQLException;

import com.revature.bean.Department;
import com.revature.bean.Employee;
import com.revature.bean.Management;

public interface DepartmentDao {
	
	public abstract void selectEmployeeDepartment(Department department, Employee employee) throws SQLException;
	
	public abstract void selectManagementDepartment(Department department, Management management) throws SQLException;
}