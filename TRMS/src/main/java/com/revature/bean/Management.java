package com.revature.bean;

public class Management {
	
	int managementId;
	String firstName;
	String lastName;
	int departmentId;
	Boolean supervisor;
	Boolean departmentHead;
	double avaiReimbursement;
    double awardedReimbursement;
    double pendingReimbursement;
	int reportsto;
	int userId;
	
	public Management() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Management(int managementId, String firstName, String lastName, int departmentId, Boolean supervisor,
			Boolean departmentHead, double avaiReimbursement, double awardedReimbursement, double pendingReimbursement,
			int reportsto, int userId) {
		super();
		this.managementId = managementId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentId = departmentId;
		this.supervisor = supervisor;
		this.departmentHead = departmentHead;
		this.avaiReimbursement = avaiReimbursement;
		this.awardedReimbursement = awardedReimbursement;
		this.pendingReimbursement = pendingReimbursement;
		this.reportsto = reportsto;
		this.userId = userId;
	}

	public int getManagementId() {
		return managementId;
	}

	public void setManagementId(int managementId) {
		this.managementId = managementId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public Boolean getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Boolean supervisor) {
		this.supervisor = supervisor;
	}

	public Boolean getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(Boolean departmentHead) {
		this.departmentHead = departmentHead;
	}

	public double getAvaiReimbursement() {
		return avaiReimbursement;
	}

	public void setAvaiReimbursement(double avaiReimbursement) {
		this.avaiReimbursement = avaiReimbursement;
	}

	public double getAwardedReimbursement() {
		return awardedReimbursement;
	}

	public void setAwardedReimbursement(double awardedReimbursement) {
		this.awardedReimbursement = awardedReimbursement;
	}

	public double getPendingReimbursement() {
		return pendingReimbursement;
	}

	public void setPendingReimbursement(double pendingReimbursement) {
		this.pendingReimbursement = pendingReimbursement;
	}

	public int getReportsto() {
		return reportsto;
	}

	public void setReportsto(int reportsto) {
		this.reportsto = reportsto;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Management [managementId=" + managementId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", departmentId=" + departmentId + ", supervisor=" + supervisor + ", departmentHead=" + departmentHead
				+ ", avaiReimbursement=" + avaiReimbursement + ", awardedReimbursement=" + awardedReimbursement
				+ ", pendingReimbursement=" + pendingReimbursement + ", reportsto=" + reportsto + ", userId=" + userId
				+ "]";
	}
}