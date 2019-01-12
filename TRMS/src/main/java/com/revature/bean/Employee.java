package com.revature.bean;

public class Employee {
	
	int employeeId;
	String firstName;
	String lastName;
	int departmentId;
	double avaiReimbursement;
	double awardedReimbursement;
	double pendingReimbursement;
	int reportsto;
	int userId;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int employeeId, String firstName, String lastName, int departmentId, double avaiReimbursement,
			double awardedReimbursement, double pendingReimbursement, int reportsto, int userId) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentId = departmentId;
		this.avaiReimbursement = avaiReimbursement;
		this.awardedReimbursement = awardedReimbursement;
		this.pendingReimbursement = pendingReimbursement;
		this.reportsto = reportsto;
		this.userId = userId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public double getAvaiReimbursement() {
		return avaiReimbursement;
	}

	public void setAvaiReimbursement(double avaiReimbursement) {
		this.avaiReimbursement = avaiReimbursement;
	}

	public double getAwaredReimbursement() {
		return awardedReimbursement;
	}

	public void setAwaredReimbursement(double awaredReimbursement) {
		this.awardedReimbursement = awaredReimbursement;
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
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", departmentId=" + departmentId + ", avaiReimbursement=" + avaiReimbursement
				+ ", awardedReimbursement=" + awardedReimbursement + ", pendingReimbursement=" + pendingReimbursement
				+ ", reportsto=" + reportsto + ", userId=" + userId + "]";
	}
}
