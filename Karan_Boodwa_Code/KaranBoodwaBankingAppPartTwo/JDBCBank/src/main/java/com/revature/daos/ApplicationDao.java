package com.revature.daos;

import java.util.List;

import com.revature.pojos.Application;

public interface ApplicationDao {
	// CRUD Operations:
	
	// CREATE
	public boolean createApplication(Application application);
	
	// READ
	public List<Application> getApplications();
	
	public Application getApplicationById(int appid);
	
	// UPDATE
	
	
	// DELETE
	public boolean removeApplication(int appid);
}
