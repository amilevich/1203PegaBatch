package com.revature.trms.dao;

import java.util.ArrayList;

import com.revature.trms.models.Reimbursement;

public interface ReimbursementDAO {

	//CRUDE methods
	//Create
	public boolean insertReimbursement(Reimbursement reimb);
	
	//Read
	public Reimbursement getReimbursement(int id);
	public ArrayList<Reimbursement> getAllReimbursement();
	public ArrayList<Reimbursement> getAllReimbursementByEmployee(int emp);
	public ArrayList<Reimbursement> getAllReimbursementRequireManagement(int emp);
	
	
	//Update
	public boolean updateReimbursement(Reimbursement reimb);
	
	//Delete
	public boolean deleteReimbursement(int id);
}
