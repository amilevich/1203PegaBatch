package com.revature.daos;

import java.util.List;

import com.revature.models.OpenApplication;

/**
 * Data Access Object
 * ---
 * That this is the interface through which
 * we intend to interact with our persistency layer
 * for this kind of project.d
 * 
 * @author Steven Einhorn
 */

public interface OpenApplicationDao {

	public boolean findOpenApplication(OpenApplication openAppl);
	public void saveOpenApplication(OpenApplication openAppl);
	public List<OpenApplication> getOpenApplications();
	public void deleteOpenApplication(OpenApplication openAppl);
	
}
