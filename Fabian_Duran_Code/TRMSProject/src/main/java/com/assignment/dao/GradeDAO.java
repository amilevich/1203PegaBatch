package com.assignment.dao;

import java.sql.SQLException;

import com.assignment.bean.Grade;

public interface GradeDAO {
	//CRUD
	//create
	public abstract void createGrade(int rei_id, String gradeFormat) throws SQLException;//initially made by requesting employee, finished by approver
	//read
	public abstract Grade readGrade(int rei_id)throws SQLException;//pulls grade info from DB
	//update
	public abstract void updateGrade(int rei_id, int satisfactory, int evalID)throws SQLException; //to be completed by approver
}
