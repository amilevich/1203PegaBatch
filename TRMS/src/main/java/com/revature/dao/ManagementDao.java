package com.revature.dao;

import java.sql.SQLException;

import com.revature.bean.Employee;
import com.revature.bean.Login;
import com.revature.bean.Management;

public interface ManagementDao {
	
	public abstract void selectManagement(Management management, Login login) throws SQLException;
	
	public abstract void employeeManager(Management management, Employee employee) throws SQLException;
	
	public abstract void updateReimbursementAmounts(Management management) throws SQLException;
}