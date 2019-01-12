package com.revature.dao;

import java.sql.SQLException;

import com.revature.bean.Employee;
import com.revature.bean.Login;

public interface EmployeeDao {
	
	public abstract void selectEmployee(Employee employ, Login login) throws SQLException;
	
	public abstract void updateReimbursementAmounts(Employee employ) throws SQLException;
}
