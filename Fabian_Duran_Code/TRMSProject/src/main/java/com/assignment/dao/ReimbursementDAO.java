package com.assignment.dao;

import java.sql.SQLException;
import java.util.List;

import com.assignment.bean.Reimbursement;

public interface ReimbursementDAO {
	// CRUD THIS
	// create
	public abstract void createReimbursement(int emp_id, String reiType, double awardRequest,
			String submitDate, String eventStartDate, int ds_id, int dh_id, String reiState, int urgent,
			String description, String eventTitle)throws SQLException;
	// read
	public abstract Reimbursement readReimbursement(int rei_id)throws SQLException;
	// update
	public abstract void updateAwardGranted(int rei_id, double amount)throws SQLException;
	public abstract void updateEventFinishDate(int rei_id, String date)throws SQLException;//this is going to be subject to change based on how front ended is coded
	public abstract void updateCompletedDate(int rei_id, String date)throws SQLException;

	public abstract void updateDSAppr(int rei_id, int appr)throws SQLException;
	public abstract void updateDHAppr(int rei_id, int appr)throws SQLException;
	public abstract void updateBCAppr(int rei_id, int appr)throws SQLException;
	public abstract void updateFinalAppr(int rei_id, int appr)throws SQLException;
	public abstract void updateReiState(int rei_id, int state)throws SQLException;
	public abstract void updateGradeID(int rei_id, int gradeID)throws SQLException;
	public abstract List<Reimbursement> getPendingEmpList(int empID) throws SQLException;
	public abstract List<Reimbursement> getDHPendingReviewList(int apprID, String empType) throws SQLException;
	public abstract List<Reimbursement> getBCPendingReviewList() throws SQLException;
	public abstract List<Reimbursement> getDSFinalReviewList() throws SQLException;
	public abstract List<Reimbursement> getSubmittedReviewList(int apprID) throws SQLException;
	public abstract List<Reimbursement> getPendingHistoryList(int empID) throws SQLException;
	
	//there really nothing to delete here, system admin should be able to delete the from the db manually
	//and no other user in program should be able delete reimbursements
	
}
