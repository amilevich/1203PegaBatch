package com.assignment.dao;

import java.sql.SQLException;
import java.util.List;

import com.assignment.bean.AdditionalInformation;

public interface AdditionalInformationDAO {
	//CRUD
	public abstract void createAddInfo(int rei_id, int from_id, int to_id, String request)throws SQLException;
	public abstract AdditionalInformation readAddInfo(int ai_id) throws SQLException;
	public abstract AdditionalInformation readAddInfo(int rei_id, int emp_id, int state) throws SQLException;
	public abstract void updateResponse(int ai_id, String response) throws SQLException;
	public abstract List<AdditionalInformation> getNotificationList(int empID) throws SQLException;
	public abstract void finishAdditionalInformation(int ai_id)throws SQLException;
}
