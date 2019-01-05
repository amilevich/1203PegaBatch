package com.revature.dao;

import java.sql.SQLException;

import com.revature.bean.Department;

public interface DepartmentDao {
	
	public abstract void selectDepartment(Department department) throws SQLException;

}
