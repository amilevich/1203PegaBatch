package com.revature.daos;

public interface User_Account_JunctDao {
	// CRUD
	
	// CREATE
	public boolean addUserAccount(int user_id, int acc_id);
	
	// READ (unused)
	public boolean isOwner(int user_id, int acc_id);
	
	// UPDATE
	
	
	// DELETE
	
}
