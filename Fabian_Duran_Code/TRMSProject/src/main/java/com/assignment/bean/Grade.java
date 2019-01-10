package com.assignment.bean;

public class Grade {
	private int gradeID, reiID, evalID, satisfactory;
	private String gradeFormat;
	
//	public Grade(int gradeID, int reiID) {
//		this.gradeID=gradeID;
//		this.reiID=reiID;
//	}

	public Grade(int gradeID, int reiID, int satisfactory, String lettergrade, int evalID) {
		super();
		this.gradeID = gradeID;
		this.reiID = reiID;
		this.satisfactory = satisfactory;
		this.gradeFormat = lettergrade;
		this.evalID = evalID;
	}
	
	public int getGradeID() {
		return gradeID;
	}

	public int getReiID() {
		return reiID;
	}

	public void setReiID(int reiID) {
		this.reiID = reiID;
	}

	public int getSatisfactory() {
		return satisfactory;
	}

	public void setSatisfactory(int satisfactory) {
		this.satisfactory = satisfactory;
	}

	public String getGradeFormat() {
		return gradeFormat;
	}

	public void setGradeFormat(String gradeFormat) {
		this.gradeFormat = gradeFormat;
	}

	public int getEvalID() {
		return evalID;
	}

	public void setEvalID(int evalID) {
		this.evalID = evalID;
	}
	
}
