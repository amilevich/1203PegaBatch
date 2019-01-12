package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.bean.Application;

public interface ApplicationDao {
	
	public abstract void insertEmployInfo(Application employee) throws SQLException;
	
	
	public abstract List<Application> selectAppInfo() throws SQLException;
}
