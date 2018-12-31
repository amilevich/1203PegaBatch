package com.revature.trms.dao;

import com.revature.trms.models.Reimbursement;

public interface ReimbursementStatusDAO {
	//CRUDE methods
		//Create
		public boolean insertReimbursementStatus(Reimbursement reimb);
		
		//Read
		//public Reimbursement getReimbursementStatus(Reimbursement reimb);
		
		//Update
		public boolean updateReimbursementStatus(Reimbursement reimb);
		
		//Delete
		//public boolean deleteReimbursement(int id);
}
