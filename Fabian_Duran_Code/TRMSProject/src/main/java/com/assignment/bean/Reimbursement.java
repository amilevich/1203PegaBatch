package com.assignment.bean;


public class Reimbursement {//I'm pulling everything from reimbursements except the BLOBs
	private int rei_id;
	private int emp_id;
	private String rei_type;
	private double awardRequested, awardGranted;
	private String submissionDate, completeDate;
	private String eventStartDate,eventFinishDate;
	private int dsAppr, dsID, dhAppr, dhID, bcAppr, bcID, finalAppr, finalID;
	private String rei_state;
	private int gradeID, urgent;
	private String description;
	//for shorthand reimbursement
	private String fName, lName;
	

//--these might not be necessary after all..
//	public Reimbursement(int rei_id, String fName, String lName, int emp_id, String rei_type) {
//		this.rei_id = rei_id;
//		this.emp_id = emp_id;
//		this.rei_type = rei_type;
//		this.fName = fName;
//		this.lName = lName;
//	}
//	
//	public Reimbursement(int rei_id, int emp_id, String rei_type, double awardRequested, long submissionDate, long eventStartDate, int urgent, String description) {
//		this.rei_id = rei_id;
//		this.emp_id = emp_id;
//		this.rei_type = rei_type;
//		this.awardRequested = awardRequested;
//		this.submissionDate = submissionDate;
//		this.eventStartDate = eventStartDate;
//		this.urgent = urgent;
//		this.description = description;
//	}
	
	public Reimbursement(int rei_id, int emp_id, String rei_type, double awardRequested, double awardGranted,
			String submissionDate, String eventStartDate, String eventFinishDate, String completeDate, int dsAppr, int dsID, int dhAppr, 
			int dhID,int bcAppr, int bcID, int finalAppr, int finalID, String rei_state, int gradeID, int urgent,
			String description) {
		super();
		this.rei_id = rei_id;
		this.emp_id = emp_id;
		this.rei_type = rei_type;
		this.awardRequested = awardRequested;
		this.awardGranted = awardGranted;
		this.submissionDate = submissionDate;
		this.eventStartDate = eventStartDate;
		this.eventFinishDate = eventFinishDate;
		this.completeDate = completeDate;
		this.dsAppr = dsAppr;
		this.dsID = dsID;
		this.dhAppr = dhAppr;
		this.dhID = dhID;
		this.bcAppr = bcAppr;
		this.bcID = bcID;
		this.finalAppr = finalAppr;
		this.finalID = finalID;
		this.rei_state = rei_state;
		this.gradeID = gradeID;
		this.urgent = urgent;
		this.description = description;
	}
	public int getRei_id() {
		return rei_id;
	}
	public void setRei_id(int rei_id) {
		this.rei_id = rei_id;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getRei_type() {
		return rei_type;
	}
	public void setRei_type(String rei_type) {
		this.rei_type = rei_type;
	}
	public double getAwardRequested() {
		return awardRequested;
	}
	public void setAwardRequested(int awardRequested) {
		this.awardRequested = awardRequested;
	}
	public double getAwardGranted() {
		return awardGranted;
	}
	public void setAwardGranted(int awardGranted) {
		this.awardGranted = awardGranted;
	}
	public String getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}
	public String getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public String getEventFinishDate() {
		return eventFinishDate;
	}
	public void setEventFinishDate(String eventFinishDate) {
		this.eventFinishDate = eventFinishDate;
	}
	public int getDsAppr() {
		return dsAppr;
	}
	public void setDsAppr(int dsAppr) {
		this.dsAppr = dsAppr;
	}
	public int getDsID() {
		return dsID;
	}
	public void setDsID(int dsID) {
		this.dsID = dsID;
	}
	public int getDhAppr() {
		return dhAppr;
	}
	public void setDhAppr(int dhAppr) {
		this.dhAppr = dhAppr;
	}
	public int getDhID() {
		return dhID;
	}
	public void setDhID(int dhID) {
		this.dhID = dhID;
	}
	public int getBcAppr() {
		return bcAppr;
	}
	public void setBcAppr(int bcAppr) {
		this.bcAppr = bcAppr;
	}
	public int getBcID() {
		return bcID;
	}
	public void setBcID(int bcID) {
		this.bcID = bcID;
	}
	public int getFinalAppr() {
		return finalAppr;
	}
	public void setFinalAppr(int finalAppr) {
		this.finalAppr = finalAppr;
	}
	public int getFinalID() {
		return finalID;
	}
	public void setFinalID(int finalID) {
		this.finalID = finalID;
	}
	public String getRei_state() {
		return rei_state;
	}
	public void setRei_state(String rei_state) {
		this.rei_state = rei_state;
	}
	public int getGradeID() {
		return gradeID;
	}
	public void setGradeID(int gradeID) {
		this.gradeID = gradeID;
	}
	public int getUrgent() {
		return urgent;
	}
	public void setUrgent(int urgent) {
		this.urgent = urgent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	
	
	
}
